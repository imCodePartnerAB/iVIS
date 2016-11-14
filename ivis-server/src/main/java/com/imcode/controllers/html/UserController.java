package com.imcode.controllers.html;

import com.imcode.entities.Role;
import com.imcode.entities.User;
import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.services.RoleService;
import com.imcode.services.UserService;
import com.imcode.utils.MailSenderUtil;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GeneralValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.smtp.from.address}")
    private String fromAddress;

    @Value("${mail.smtp.from.username}")
    private String fromUsername;

    //    Shows the list of users
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.setViewName("users/list");
        model.addObject(userService.findAll());

        return model;
    }

    //    Show the UPDATE form
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") User user,
                                   ModelAndView model,
                                   WebRequest webRequest) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(user, "Try invoke update form for non exist user");

        User currentUser = StaticUtls.getCurrentUser(webRequest, userService);
        if(!currentUser.hasRoles("ROLE_ADMIN")) {
            if (!user.getId().equals(currentUser.getId())) {
                model.setViewName("redirect:/");
                return model;
            }
        }

        model.setViewName("users/edit");
        model.addObject(user);
        model.addObject(roleService.findAll());

        return model;
    }

    //    Show the CREATE form
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView model) {
        model.setViewName("users/edit");
        model.addObject(roleService.findAll());
        User user = new User();
        Role roleUser = roleService.findFirstByName("ROLE_USER");
        model.addObject(user);

        if (roleUser != null) {
            user.setAuthorities(roleUser);
        }

        return model;
    }

    //    CREATE new user
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult,
                               ModelAndView model) throws MethodArgumentNotValidException {

        Map<String, Map<GeneralValidator.Constraint, String>> constraints = new HashMap<>();

        if (userService.findByUsername(user.getUsername()) != null) {
            bindingResult.reject(null, "username not unique");
        }

        new GeneralValidator(constraints).invoke(user, bindingResult);

        StaticUtls.encodeUserPassword(user);

        userService.save(user);

        model.setViewName("redirect:/users");

        return model;
    }

    //    UPDATE exists user
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView update (@PathVariable("id") User persistUser,
                                @ModelAttribute("user") @Valid User user,
                                ModelAndView model) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(user, "Try update non exist user");

        StaticUtls.encodeUserPassword(user);

        try {
            StaticUtls.nullAwareBeanCopy(persistUser, user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        userService.save(persistUser);
        model.setViewName("redirect:/users");

        return model;
    }

    //    DELETE exists user
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws MethodArgumentNotValidException {

        User user = userService.find(id);

        StaticUtls.rejectNullValue(user, "Try delete non exist user");

        userService.delete(id);

//        return "redirect:/users";
    }

    @RequestMapping(value = "/{id}", params = "passwordchange", method = RequestMethod.POST)
    public ModelAndView passwordChange(@PathVariable("id") User user,
                               @RequestParam("password") String password,
                               ModelAndView model) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(user, "Try change password for non exist user");

        user.setPassword(password);
        StaticUtls.encodeUserPassword(user);

        userService.save(user);

        String to = user.getPerson().getEmails().get(CommunicationTypeEnum.HOME).getValue();
        String subject = "Change password in iVIS";
        String text = "Hello, " + user.getUsername() + ". Your password has bean changed.";

        MailSenderUtil mailSenderUtil = new MailSenderUtil(mailSender, false, false, fromAddress, fromUsername);
        mailSenderUtil.createMessage(to, subject, text);
        mailSenderUtil.sendMessage();

        model.setViewName("redirect:/logout.do");

        return model;
    }


    @RequestMapping(value = "/{id}", params = "checkpassword", method = RequestMethod.GET)
    public @ResponseBody Boolean checkPassword(@PathVariable("id") User user,
                                               @RequestParam("checkpassword") String password) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(user, "Try check password for non exist user");

        String userEncodedPassword = user.getPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(password, userEncodedPassword);

    }

}

