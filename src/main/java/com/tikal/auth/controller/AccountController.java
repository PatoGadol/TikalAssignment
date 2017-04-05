package com.tikal.auth.controller;

import com.tikal.auth.service.AccountService;
import com.tikal.auth.service.SecurityService;
import com.tikal.auth.validator.AccountValidator;
import com.tikal.web.entities.WebAccount;
import com.tikal.web.entities.WebRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Sopher on 23/03/2017.
 */

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AccountValidator userValidator;

    @PostMapping(value = "/registration")
    public String registration(@RequestBody WebAccount webAccount, BindingResult bindingResult) {
        userValidator.validate(webAccount, bindingResult);

        if (bindingResult.hasErrors()) {
            return "Please make sure your username is 6-32 chars, and password 8-32 chars.";
        }

        accountService.save(webAccount);

        return "Registration succeeded.";
    }

    @PostMapping(value = "/role")
    @Secured("ROLE_ADMIN")
    public String addRole(@RequestBody WebRole role) {
        accountService.save(role);

        return "Role: ".concat(role.getRole()).concat(" was saved successfully.");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

}
