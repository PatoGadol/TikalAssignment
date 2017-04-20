package com.tikal.auth.service;

import com.tikal.auth.cache.CacheImpl;
import com.tikal.auth.model.Account;
import com.tikal.auth.model.Role;
import com.tikal.auth.repository.AccountRepository;
import com.tikal.auth.repository.RoleRepository;
import com.tikal.web.entities.WebAccount;
import com.tikal.web.entities.WebRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private static CacheImpl<String, Set<Role>> accountCache;

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
    @Cacheable
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

    @Override
    public void tryCache(String username) throws NullPointerException {
        if (!accountCache.contains(username)) {
            Account account = findByUsername(username);
            accountCache.put(username, account.getRoles());
        }
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

    public static CacheImpl<String, Set<Role>> accountCache() {
        return accountCache;
    }
}
