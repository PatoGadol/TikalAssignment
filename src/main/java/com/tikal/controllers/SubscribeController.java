package com.tikal.controllers;

import com.tikal.accounts.AccountHandler;
import com.tikal.web.entities.WebAccount;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Sopher on 22/03/2017.
 */

@RestController
@EnableAutoConfiguration
public class SubscribeController {

    @PostMapping(value = "/add_account")
    public String addAccount(@RequestParam("account") WebAccount account) {
        AccountHandler ac = new AccountHandler();
        ac.createNewAccount(AccountHandler.convertWebAccountToAccount(account));
        return "user_id:" + 0;
    }

    @DeleteMapping(value = "/delete_user")
    @ResponseBody
    public String deleteUser(String user) {
        return "";
    }
}
