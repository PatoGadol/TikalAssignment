package com.tikal.accounts;

import com.tikal.dal.entities.Account;
import com.tikal.web.entities.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Sopher on 22/03/2017.
 */
public class AccountHandler {

    @Autowired
    private AccountsRepository accountsRepository;

    public static Account convertWebAccountToAccount(WebAccount webAccount) {
        Account account = new Account();
        account.setUserName(account.getUserName());
        account.setUserName(account.getPassword());
        account.setUserName(account.getEmail());
        account.setCreatedOn(new Date());
        account.setLastLogin(new Date());

        return account;
    }

    public void createNewAccount(Account account) {
        accountsRepository.save(account);
    }
}
