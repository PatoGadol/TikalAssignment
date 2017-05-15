package com.tikal.controller;

import com.tikal.service.AccountService;
import com.tikal.auth.validator.AccountValidator;
import com.tikal.utils.Mail;
import com.tikal.web.entities.WebAccount;
import com.tikal.web.entities.WebRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    private AccountValidator userValidator;

    @PostMapping(value = "/registration")
    public String registration(@RequestBody WebAccount webAccount, BindingResult bindingResult) {
        userValidator.validate(webAccount, bindingResult);

        if (bindingResult.hasErrors()) {
            return "Please make sure your username is 6-32 chars, and password 8-32 chars.";
        }

        try {
            accountService.save(webAccount);
            Mail.sendMail(webAccount.getEmail(), "User: " + webAccount.getUsername() +
                    ", is registered to share a dream. Thank you for using our program.");

        }
        catch (Exception e) {
            e.printStackTrace();
            return "Failed to register.";
        }
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

    @PostMapping(value = "/login")
    public void login(@RequestBody WebAccount webAccount) {
        accountService.tryCache(webAccount.getUsername());
    }

//    @GetMapping
//    public String cacheTest(@RequestHeader String username) {
//        Set<Role> roles = AccountServiceImpl.accountCache().get(username);
//        roles.stream().forEach(System.out::println);
//        StringBuilder rolesStr = new StringBuilder();
//        roles.stream().forEach(role -> rolesStr.append(role + " "));
//        return rolesStr.toString();
//    }

}
