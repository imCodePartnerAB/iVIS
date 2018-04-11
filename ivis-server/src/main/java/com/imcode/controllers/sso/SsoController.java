package com.imcode.controllers.sso;

import com.imcode.services.SsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/idp")
public class SsoController {

    private final SsoService ssoService;

    @Autowired
    public SsoController(SsoService ssoService) {
        this.ssoService = ssoService;
    }

    @RequestMapping(value = "/SingleSignOnService", method = RequestMethod.GET)
    public void singleSignOnService(final HttpServletRequest request, final HttpServletResponse response) {
        ssoService.logIn(request, response);
    }

    @RequestMapping(value = "/SingleLogoutService", method = RequestMethod.GET)
    public void singleLogoutService(final HttpServletRequest request, final HttpServletResponse response) {
        ssoService.logOut(request, response);
    }
}
