package com.imcode.controllers.sso;

import com.imcode.services.SsoAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/idp")
public class SsoController {

    private final SsoAuthenticationService ssoAuthenticationService;

    @Autowired
    public SsoController(SsoAuthenticationService ssoAuthenticationService) {
        this.ssoAuthenticationService = ssoAuthenticationService;
    }

    @RequestMapping(value = "/SingleSignOnService", method = RequestMethod.GET)
    public void singleSignOnServiceGet(final HttpServletRequest request, final HttpServletResponse response) {
        ssoAuthenticationService.authenticate(request, response);
    }
}
