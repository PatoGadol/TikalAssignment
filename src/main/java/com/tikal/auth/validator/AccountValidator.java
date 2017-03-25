package com.tikal.auth.validator;

import com.tikal.auth.model.Account;
import com.tikal.auth.service.AccountService;
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
        if (account.getUserName().length() < 6 || account.getUserName().length() > 32) {
            errors.rejectValue("userName", "Size.userForm.username");
        }
        if (accountService.findByUsername(account.getUserName()) != null) {
            errors.rejectValue("userName", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (account.getPassword().length() < 8 || account.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}
