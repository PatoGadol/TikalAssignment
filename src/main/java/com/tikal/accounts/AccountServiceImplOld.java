package com.tikal.accounts;

import com.tikal.auth.model.Account;
import com.tikal.web.entities.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Sopher on 22/03/2017.
 */

public class AccountServiceImplOld implements AccountService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public Account convertWebAccountToAccount(WebAccount webAccount) {
        Account account = new Account();
        account.setUserName(webAccount.getUserName());
        account.setPassword(webAccount.getPassword());
        account.setEmail(webAccount.getEmail());
        account.setCreatedOn(new Date());
        account.setLastLogin(new Date());

        return account;
    }

    @Override
    public void createNewAccount(Account account) {
        accountsRepository.save(account);
    }
}
