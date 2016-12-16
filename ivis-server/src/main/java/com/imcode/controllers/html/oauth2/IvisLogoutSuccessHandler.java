package com.imcode.controllers.html.oauth2;

import com.imcode.validators.GeneralValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ruslan on 01.11.16.
 */
public class IvisLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private static final String STANDARD_TARGET_URL = "/login";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = request.getParameter("redirect_url");
        if (redirectUrl == null) {
            redirectUrl = STANDARD_TARGET_URL;
        } else {
            if (!redirectUrl.matches(GeneralValidator.URL_PATTERN)) {
                redirectUrl = STANDARD_TARGET_URL;
            }
        }
        setDefaultTargetUrl(redirectUrl);
        super.onLogoutSuccess(request, response, authentication);
    }
}
