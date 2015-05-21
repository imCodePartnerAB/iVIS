package com.imcode.validators;


import com.imcode.entities.Role;
import com.imcode.entities.User;
import com.imcode.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vitaly on 20.05.15.
 */
@Component
public class UserValidator implements Validator {
    @Autowired
    private RoleService roleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
//                "user.requiredPassword", "Field name is required.");
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
//                "user.requiredConfirmPassword", "Field name is required.");
        User user = (User)target;

        Set<Role> roleSet = new HashSet<>();
        Set<Role> roleNameSet = user.getAuthorities();
        boolean hasErrors = false;
        for (Role role :roleNameSet) {
            Role persistRole = roleService.findByName(role.getName());

            if (persistRole != null) {
                roleSet.add(persistRole);
            } else {
                hasErrors = true;
                errors.rejectValue("authorities", null, new String[]{role.getName()}, "Role {1} not found.");
            }
        }

        if (!hasErrors) {
            user.setAuthorities(roleSet);
        }

        if(!(user.getPassword().equals(user.getConfirmPassword()))){
            errors.rejectValue("confirmPassword", "user.notmatchConfirmPassword");
        }
    }
}
