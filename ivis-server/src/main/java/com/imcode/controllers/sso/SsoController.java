package com.imcode.controllers.sso;

import com.imcode.entities.User;
import com.imcode.exceptions.SchoolCloudAccessException;
import com.imcode.services.SsoService;
import com.imcode.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/idp")
public class SsoController {

    private static final String ACCESS_ERROR_MESSAGE =
            "You are not entitled to access iVIS School Cloud – please check with your administrator.";

    private final SsoService ssoService;
    private final UserService userService;

    @Autowired
    public SsoController(SsoService ssoService, UserService userService) {
        this.ssoService = ssoService;
        this.userService = userService;
    }

    @RequestMapping(value = "/SingleSignOnService", method = RequestMethod.GET)
    public void singleSignOnService(final HttpServletRequest request, final HttpServletResponse response) {
        final User loggedUser = userService.findByUsername(request.getUserPrincipal().getName());

        if (!loggedUser.getNextCloudEnabled()) {
            throw new SchoolCloudAccessException(ACCESS_ERROR_MESSAGE);
        }

        ssoService.logIn(request, response);
    }

    @RequestMapping(value = "/SingleLogoutService", method = RequestMethod.GET)
    public void singleLogoutService(final HttpServletRequest request, final HttpServletResponse response) {
        ssoService.logOut(request, response);
    }
}
