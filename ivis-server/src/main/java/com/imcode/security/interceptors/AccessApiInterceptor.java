package com.imcode.security.interceptors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.imcode.entities.User;
import com.imcode.exceptions.factories.ErrorBuilder;
import com.imcode.exceptions.wrappers.GeneralError;
import com.imcode.services.PermissionService;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by ruslan on 15.08.16.
 */
public class AccessApiInterceptor extends HandlerInterceptorAdapter {

    private final PermissionService permissionService;

    @Autowired
    public AccessApiInterceptor(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof OAuth2Authentication)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            setErrorInResponse("Authorization without OAuth2 protocol.", response);
            return false;
        }

        OAuth2Authentication oauth2Authentication = (OAuth2Authentication) authentication;
        OAuth2Request clientAuthentication = oauth2Authentication.getOAuth2Request();
        String clientId = clientAuthentication.getClientId();
        Long userId = ((User) oauth2Authentication.getUserAuthentication().getPrincipal()).getId();

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Integer hash = StaticUtls.getHashFrom(handlerMethod);

        Boolean permitted = permissionService.isPermitted(clientId, userId, hash);
        if (Objects.isNull(permitted)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            setErrorInResponse(
                    "Client and/or user haven't permission to access "
                    + handlerMethod.getBeanType().getSimpleName().replace("RestControllerImpl", "")
                    + "["
                    + handlerMethod.getMethod().getName()
                    + "]"
                    + " method.", response);
            return false;
        }

        return permitted;
    }

    private void setErrorInResponse(String message, HttpServletResponse response) throws IOException {
        GeneralError generalError = ErrorBuilder.buildSecurityException(message);
        String errorAsJson = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
                .writeValueAsString(generalError);
        response.getOutputStream().print(errorAsJson);
    }
}
