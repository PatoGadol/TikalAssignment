package com.tikal.service;

import com.tikal.dao.model.Account;
import com.tikal.dao.model.Role;
import com.tikal.dao.repository.AccountRepository;
import com.tikal.dao.repository.RoleRepository;
import com.tikal.web.entities.WebAccount;
import com.tikal.web.entities.WebRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @CachePut(value = "CachePOC", key = "#account.userId")
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
    @Cacheable(value = "CachePOC", key = "#username" )
    public Account findByUsername(String userName) {
        return accountRepository.findByUserName(userName);
    }

    @Override
    public void save(WebRole role) {
        roleRepository.save(new Role(role.getRole().toLowerCase()));
    }

    @Override
    @Cacheable
    public Role findByRole(String role) {
        return roleRepository.findByRoleName(role.toLowerCase());
    }

    private Account convertWebAccountToAccount(WebAccount webAccount) {
        Account account = new Account();
        account.setUserName(webAccount.getUsername());
        account.setPassword(webAccount.getPassword());
        account.setEmail(webAccount.getEmail());
        account.setCreatedOn(new Date());
        account.setLastLogin(new Date());

        return account;
    }

}