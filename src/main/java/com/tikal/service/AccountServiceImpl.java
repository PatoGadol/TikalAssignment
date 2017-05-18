package com.tikal.service;

import com.tikal.dao.model.Account;
import com.tikal.dao.model.Role;
import com.tikal.dao.repository.AccountRepository;
import com.tikal.dao.repository.RoleRepository;
import com.tikal.web.entities.WebAccount;
import com.tikal.web.entities.WebRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    @CachePut(value = "cachePOC", key = "#result.username")
    public Account saveAccount(WebAccount webAccount) {
        Account account = convertWebAccountToAccountWithRoles(webAccount);
        accountRepository.save(account);
        return account;
    }

    @Override
    @Cacheable(value = "cachePOC", key = "#username" )
    public Account findAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    @CachePut(value = "cachePOC", key = "#webAccount.username" )
    public void updateAccount(WebAccount webAccount) {
        Account account = accountRepository.findByUsername(webAccount.getUsername());
        if (account != null) {
            account = convertWebAccountToAccountWithRoles(webAccount);
        }

        accountRepository.save(account);
    }

    @Override
    @CacheEvict(value = "cachePOC", key = "username")
    public void deleteAccount(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account != null)
            accountRepository.delete(account);
    }

    @Override
    @CacheEvict(value = "cachePOC", allEntries = true)
    public void evictCache() {

    }

    @Override
    public void saveRole(WebRole role) {
        roleRepository.save(new Role(role.getRole().toLowerCase()));
    }


    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRoleName(role.toLowerCase());
    }

    @Override
    public void updateRole(WebRole webRole) {
        Role persistentRole = findByRole(webRole.getRole());
        if (persistentRole != null) {
            persistentRole.setRoleName(webRole.getRole());
            roleRepository.save(persistentRole);
        }
    }

    @Override
    public void deleteRole(String role) {
        Role persistentRole = findByRole(role);
        roleRepository.delete(persistentRole);
    }

    private Account convertWebAccountToAccount(WebAccount webAccount) {
        Account account = new Account();
        account.setUsername(webAccount.getUsername());
        account.setPassword(webAccount.getPassword());
        account.setEmail(webAccount.getEmail());
        account.setCreatedOn(new Date());
        account.setLastLogin(new Date());

        return account;
    }

    private Account convertWebAccountToAccountWithRoles(WebAccount webAccount) {
        Account account = convertWebAccountToAccount(webAccount);
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        List<Role> allRoles = roleRepository.findAll();
        Set<Role> roles = new HashSet<>();

        for (Role role : allRoles)
            for (Role webRole : webAccount.getRoles())
                if (role.getRoleName().equals(webRole.getRoleName().toLowerCase()))
                    roles.add(role);
        account.setRoles(roles);
        return account;
    }

}
