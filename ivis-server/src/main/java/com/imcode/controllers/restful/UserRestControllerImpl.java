package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.User;
import com.imcode.services.UserService;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
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
        User user = StaticUtls.getCurrentUser(webRequest, userService);

        Map<String, Object> loggedInUser = new LinkedHashMap<>();
        loggedInUser.put("id", user.getId());
        loggedInUser.put("person", user.getPerson());
        loggedInUser.put("roles", user.getRoles());

        return loggedInUser;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/current")
    public User getCurrentUser(WebRequest webRequest) {
        return StaticUtls.getCurrentUser(webRequest, userService);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
    public List<User> getByPersonalId(@RequestParam("personalId") String personId,
                                       HttpServletResponse response
    ) {
        return super.getByPersonalId(personId, response);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"personalId", "first"})
    public User getFirstByPersonalId(@RequestParam("personalId") String personId,
                                      HttpServletResponse response
    ) {
        return super.getFirstByPersonalId(personId, response);
    }
}
