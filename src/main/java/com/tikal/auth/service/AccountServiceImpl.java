package com.tikal.auth.service;

import com.tikal.auth.model.Account;
import com.tikal.auth.repository.AccountRepository;
import com.tikal.auth.repository.RoleRepository;
import com.tikal.web.entities.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

/**
 * Created by Sopher on 23/03/2017.
 */
@Service
public class AccountServiceImpl implements AccountService{

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
        account.setRoles(new HashSet<>(roleRepository.findAll()));
        accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String userName) {
        return accountRepository.findByUserName(userName);
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
}
