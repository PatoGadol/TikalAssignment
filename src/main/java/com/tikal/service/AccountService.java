package com.tikal.service;

import com.tikal.dao.model.Account;
import com.tikal.dao.model.Role;
import com.tikal.web.entities.WebAccount;
import com.tikal.web.entities.WebRole;

/**
 * Created by Sopher on 23/03/2017.
 */

public interface AccountService{

    Account saveAccount(WebAccount webAccount);

    Account findAccountByUsername(String username);

    void updateAccount(WebAccount webAccount);

    void deleteAccount(String username);

    public void evictCache();


    void saveRole(WebRole role);

    Role findByRole(String role);

    void updateRole(WebRole role);

    void deleteRole(String role);

}
