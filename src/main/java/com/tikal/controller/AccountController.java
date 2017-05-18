package com.tikal.controller;

import com.tikal.auth.validator.AccountValidator;
import com.tikal.service.AccountService;
import com.tikal.web.entities.WebAccount;
import com.tikal.web.entities.WebRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sopher on 23/03/2017.
 */

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountValidator userValidator;
/*
    @GetMapping("/account")
      public WebAccount account(@RequestBody String username) {
    }*/

    @PostMapping(value = "/registration")
    public String registration(@RequestBody WebAccount webAccount, BindingResult bindingResult) {
        userValidator.validate(webAccount, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError oe : bindingResult.getAllErrors())
                sb.append(oe.toString() + " ");
            return sb.toString();
        }

        try {
            accountService.saveAccount(webAccount);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to register.";
        }
        return "Registration succeeded.";
    }

    @PostMapping(value = "/role")
    @Secured("ROLE_ADMIN")
    public String addRole(@RequestBody WebRole role) {
        accountService.saveRole(role);

        return "Role: ".concat(role.getRole()).concat(" was saved successfully.");
    }

}
