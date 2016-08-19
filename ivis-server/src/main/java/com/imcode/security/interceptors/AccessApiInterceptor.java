package com.imcode.security.interceptors;

import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.entities.User;
import com.imcode.services.MethodRestProviderForEntityService;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ruslan on 15.08.16.
 */
public class AccessApiInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MethodRestProviderForEntityService methodRestProviderForEntityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof OAuth2Authentication)) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        OAuth2Authentication oauth2Authentication = (OAuth2Authentication) authentication;
        OAuth2Request clientAuthentication = oauth2Authentication.getOAuth2Request();
        String clientId = clientAuthentication.getClientId();
        Long userId = ((User) oauth2Authentication.getUserAuthentication().getPrincipal()).getId();

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        String controllerSimpleName = handlerMethod.getBean().getClass().getSimpleName();
        String entityName = controllerSimpleName.substring(0, controllerSimpleName.indexOf("RestControllerImpl"));
        String methodName = handlerMethod.getMethod().getName();

        MethodRestProviderForEntity allowedMethod = methodRestProviderForEntityService.findAllowedMethod(methodName, entityName, clientId, userId);

        if (allowedMethod == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        return true;
    }
}
