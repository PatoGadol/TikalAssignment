package com.tikal.controllers;

import com.tikal.accounts.AccountService;
import com.tikal.web.entities.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Sopher on 22/03/2017.
 */

@RestController
@EnableAutoConfiguration
public class SubscribeController {

    @Autowired
    AccountService accountServiceImpl;
    @PostMapping(value = "/add_account")
    public String addAccount(@RequestBody WebAccount account) {
        accountServiceImpl.createNewAccount(accountServiceImpl.convertWebAccountToAccount(account));
        return "user_id:" + 0;
    }

    @DeleteMapping(value = "/delete_user")
    public String deleteUser(String user) {
        return "";
    }
}
