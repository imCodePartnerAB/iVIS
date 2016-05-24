package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Person;
import com.imcode.entities.User;
import com.imcode.services.PupilService;
import com.imcode.services.UserService;
import com.imcode.utils.StaticUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.jws.soap.SOAPBinding;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/{format}/users")
public class UserRestControllerImpl extends AbstractRestController<User, Long, UserService>{
    @Autowired
    private UserService userService;

//    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
//    public User getByPersonalId(WebRequest webRequest, @RequestParam("personalId") String personId) {
//        return null;
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/loggedin")
    public Object getLoggedInUser(WebRequest webRequest) {
        User user = StaticUtils.getCurrentUser(webRequest, userService);

        Map<String, Object> loggedInUser = new LinkedHashMap<>();
        loggedInUser.put("id", user.getId());
        Person person = user.getPerson();
        loggedInUser.put("first_name", person.getFirstName());
        loggedInUser.put("last_name", person.getLastName());
        loggedInUser.put("roles", user.getRoles());

        return loggedInUser;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/current")
    public User getCurrentUser(WebRequest webRequest) {
        return StaticUtils.getCurrentUser(webRequest, userService);
    }
}
