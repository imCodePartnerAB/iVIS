package com.imcode.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by vitaly on 08.06.15.
 */
public class IvisLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    private List<String> redirectParameterNames = Collections.emptyList();


//    /**
//     * @param loginFormUrl URL where the login page can be found. Should either be
//     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
//     *                     URL.
//     */
//    public IvisLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
//        super(loginFormUrl);
//    }

    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(getLoginFormUrl());
        List<String> redirectParams = getRedirectParameterNames();
        Map<String, String[]> params = request.getParameterMap();

        for (String paramName: redirectParams) {

            if (params.containsKey(paramName)) {
                uriBuilder.queryParam(paramName, params.get(paramName));
            }
        }

        return uriBuilder.build().toUriString();
    }

    public List<String> getRedirectParameterNames() {
        return redirectParameterNames;
    }

    public void setRedirectParameterNames(List<String> redirectParameterNames) {
        this.redirectParameterNames = redirectParameterNames;
    }
}
