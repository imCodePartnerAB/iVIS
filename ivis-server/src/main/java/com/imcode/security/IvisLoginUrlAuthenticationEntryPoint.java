package com.imcode.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Created by vitaly on 08.06.15.
 */
public class IvisLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    private List<String> forwardedParams = Collections.emptyList();

    //todo make up forwarding the specified request parameters
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String loginUrl = getLoginFormUrl();
        return loginUrl;
    }

    public List<String> getForwardedParams() {
        return forwardedParams;
    }

    public void setForwardedParams(List<String> forwardedParams) {
        this.forwardedParams = forwardedParams;
    }
}
