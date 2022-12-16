package com.example.risk.validator;

import com.example.risk.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //parola si userul nu pot fi goale sau spatii
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.not_empty");

        // Useru tre sa aiba minim 4 si maxim 4 carac
        if (user.getUsername().length() < 4) {
            errors.rejectValue("username", "register.error.username.less_4");
        }
        if(user.getUsername().length() > 32){
            errors.rejectValue("username","register.error.username.over_32");
        }
        //user diferit pt fiecare
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "register.error.duplicated.username");
        }

        //parola sa aiba intre 8 si 32
        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "register.error.password.less_8");
        }
        if (user.getPassword().length() > 32){
            errors.rejectValue("password", "register.error.password.over_32");
        }

    }
}
