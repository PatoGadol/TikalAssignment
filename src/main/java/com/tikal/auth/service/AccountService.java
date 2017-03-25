package com.tikal.auth.service;

import com.tikal.auth.model.Account;
import com.tikal.web.entities.WebAccount;
import org.springframework.stereotype.Service;

/**
 * Created by Sopher on 23/03/2017.
 */

public interface AccountService{

    void save(WebAccount webAccount);

    Account findByUsername(String userName);
}
