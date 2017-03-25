package com.tikal.accounts;

import com.tikal.auth.model.Account;
import com.tikal.web.entities.WebAccount;

/**
 * Created by Sopher on 23/03/2017.
 */
public interface AccountService {
    public Account convertWebAccountToAccount(WebAccount webAccount);
    public void createNewAccount(Account account);
}
