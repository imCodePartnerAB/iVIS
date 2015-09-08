package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Person;
import com.imcode.entities.User;
import com.imcode.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.jws.soap.SOAPBinding;
import java.security.Principal;

@RestController
@RequestMapping("/v1/{format}/users")
public class UserRestControllerImpl extends AbstractRestController<User, Long, UserService>{
//    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
//    public User getByPersonalId(WebRequest webRequest, @RequestParam("personalId") String personId) {
//        return null;
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/current")
    public User getCurrentUser(WebRequest webRequest) {
        User user = null;

        try {
            Authentication authentication = (Authentication) webRequest.getUserPrincipal();
            user = (User) authentication.getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
