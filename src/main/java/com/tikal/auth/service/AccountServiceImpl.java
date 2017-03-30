package com.tikal.auth.service;

import com.tikal.auth.model.Account;
import com.tikal.auth.model.Role;
import com.tikal.auth.repository.AccountRepository;
import com.tikal.auth.repository.RoleRepository;
import com.tikal.web.entities.WebAccount;
import com.tikal.web.entities.WebRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Sopher on 23/03/2017.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(WebAccount webAccount) {
        Account account = convertWebAccountToAccount(webAccount);
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        List<Role> allRoles = roleRepository.findAll();
        Set<Role> roles = new HashSet<>();

        for (Role role : allRoles)
            for (Role webRole : webAccount.getRoles())
                if (role.getRoleName().equals(webRole.getRoleName().toLowerCase()))
                    roles.add(role);
        account.setRoles(roles);
        accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String userName) {
        return accountRepository.findByUserName(userName);
    }

    @Override
    public void save(WebRole role) {
        roleRepository.save(new Role(role.getRole().toLowerCase()));
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRoleName(role.toLowerCase());
    }

    private Account convertWebAccountToAccount(WebAccount webAccount) {
        Account account = new Account();
        account.setUserName(webAccount.getUserName());
        account.setPassword(webAccount.getPassword());
        account.setEmail(webAccount.getEmail());
        account.setCreatedOn(new Date());
        account.setLastLogin(new Date());

        return account;
    }

    @Override
    public WebAccount login(String username, String password) {
        return new WebAccount();
    }
}
