package com.imcode.controllers.html;

import com.imcode.controllers.html.exceptions.NotFoundException;
import com.imcode.controllers.html.form.Message;
import com.imcode.controllers.html.form.MessageType;
import com.imcode.entities.Role;
import com.imcode.entities.User;
import com.imcode.services.RoleService;
import com.imcode.services.UserService;
import com.imcode.validators.UserValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    MessageSource messageSource;

    //    Shows the list of users
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model, Authentication authentication) {
        model.setViewName("users/list");
        model.addObject(userService.findAll());

        return model;
    }

    //    Show the UPDATE form
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") Long id,
                                   ModelAndView model,
                                   WebRequest webRequest,
                                   Locale locale) {

        model.setViewName("users/edit");
        User user = userService.find(id);
        model.addObject(user);
        model.addObject(roleService.findAll());

        if (user == null) {
            model.setViewName("clients/list");
            throw new NotFoundException();
//            model.addObject(new Message(MessageType.ERROR, messageSource.getMessage("entity.notFoundById", new Object[]{User.class.getSimpleName(), id}, locale)));
        }

        return model;
    }

    //    Show the CREATE form
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView model) {
        model.setViewName("users/edit");
        model.addObject(roleService.findAll());
        User user = new User();
        Role roleUser = roleService.findByName("ROLE_USER");
        model.addObject(user);

        if (roleUser != null) {
            user.setAuthorities(roleUser);
        }

        return model;
    }

    //    CREATE new user
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResultUser,
                               ModelAndView model,
                               WebRequest webRequest,
                               Locale locale) {

        if (webRequest.isUserInRole("ROLE_ADMIN")) {
            user.setConfirmPassword(user.getPassword());
        }

        ValidationUtils.invokeValidator(userValidator, user, bindingResultUser);

        if (userService.findByUsername(user.getUsername()) != null) {
            bindingResultUser.rejectValue("username", null, "User is alredy exists!");
        }

        if (bindingResultUser.hasErrors()) {
            model.addObject(user);
            model.setViewName("users/edit");

            return model;
        }

        userService.save(user);

        model.setViewName("redirect:/users");

        return model;
    }


//    @ModelAttribute("user")
//    public User bindUser(User user) {
//        Set<Role> roleSet = new HashSet<>();
//        Set<Role> roleNameSet = user.getAuthorities();
//
//        for (Role role :roleNameSet) {
//            Role persistRole = roleService.findByName(role.getName());
//            if (persistRole != null) {
//                roleSet.add(persistRole);
//            }
//        }
//
//        user.setAuthorities(roleSet);
//
//        return user;
//    }

        //    UPDATE exists user
        @RequestMapping(value = "/{id}", method = RequestMethod.POST)
        public ModelAndView update (@PathVariable("id") Long id,
                                    @ModelAttribute("user") @Valid User user,
                                    BindingResult bindingResultUser,
                                    ModelAndView model,
                                    WebRequest webRequest,
                                    Locale locale){

            if (webRequest.isUserInRole("ROLE_ADMIN")) {
                user.setConfirmPassword(user.getPassword());
            }

            ValidationUtils.invokeValidator(userValidator, user, bindingResultUser);

            if (bindingResultUser.hasErrors()) {
                model.addObject(user);
                model.setViewName("users/edit");

                return model;
            }

            User persistUser = userService.find(id);

            if (persistUser == null) {
                throw new NotFoundException();
            }

            String fieldExceptions = user.getPassword().isEmpty() ? "id,password,confirmPassword" : "id";

            BeanUtils.copyProperties(user, persistUser, fieldExceptions);
            userService.save(persistUser);
            model.setViewName("redirect:/users");

            return model;
        }

    //    DELETE exists user
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        if (!userService.exist(id)) {
            throw new NotFoundException();
        }

        userService.delete(id);

//        return "redirect:/users";
    }

    }

