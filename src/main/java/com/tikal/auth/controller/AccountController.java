package com.tikal.auth.controller;

import com.tikal.auth.service.AccountService;
import com.tikal.auth.service.SecurityService;
import com.tikal.auth.validator.AccountValidator;
import com.tikal.web.entities.WebAccount;
import com.tikal.web.entities.WebRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Sopher on 23/03/2017.
 */

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AccountValidator userValidator;

    /*@Autowired
    private WebAccount webAccount;*/

   /* @PostMapping(value = "/login" , proxyMode = ScopedProxyMode.INTERFACES)
    public String login(@RequestHeader String username,@RequestHeader String password) {
        if (webAccount == null) {
            webAccount = accountService.login(username, password);
            return "login success";
        }

        return "user already logged in.";
    }*/

    @GetMapping(value = "/registration")
    public WebAccount registrationFields() {
        return new WebAccount();
    }

    @PostMapping(value = "/registration")
    public String registration(@RequestBody WebAccount webAccount, BindingResult bindingResult) {
        userValidator.validate(webAccount, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        accountService.save(webAccount);

        securityService.autologin(webAccount.getUserName(), webAccount.getPassword());

        return "Registration succeeded.";
    }

    @PostMapping(value = "/role")
    public String addRole(@RequestBody WebRole role) {
        accountService.save(role);

        return "Role: ".concat(role.getRole()).concat(" was save successfully.");

    }

}
