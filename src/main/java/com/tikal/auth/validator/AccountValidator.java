package com.tikal.auth.validator;

import com.tikal.service.AccountService;
import com.tikal.web.entities.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Sopher on 23/03/2017.
 */

@Component
public class AccountValidator implements Validator {
    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> aClass) {
        return WebAccount.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        WebAccount account = (WebAccount) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (account.getUsername().length() < 6 || account.getUsername().length() > 32) {
            errors.rejectValue("username", /*"Size.userForm.username"*/"Choose username between 6 and 32 chars.");
        }
        if (accountService.findAccountByUsername(account.getUsername()) != null) {
            errors.rejectValue("username", /*"Duplicate.userForm.username"*/"Username exists, please choose a different one.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (account.getPassword().length() < 8 || account.getPassword().length() > 32) {
            errors.rejectValue("password", /*"Size.userForm.password"*/"Choose password between 8 and 32 chars.");
        }
    }
}
