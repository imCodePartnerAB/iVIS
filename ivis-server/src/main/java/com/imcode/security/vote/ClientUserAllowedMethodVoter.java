package com.imcode.security.vote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.entities.User;
import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.services.MethodRestProviderForEntityService;
import com.imcode.services.jpa.ClientDetailsServiceRepoImpl;
import com.imcode.utils.ControllerReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ClientUserAllowedMethodVoter implements AccessDecisionVoter<Object> {

    private String clientHasScope = "CLIENT_HAS_SCOPE";

    @Autowired
    private MethodRestProviderForEntityService methodRestProviderForEntityService;

    public boolean supports(ConfigAttribute attribute) {
        if (clientHasScope.equals(attribute.getAttribute())) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

        int result = ACCESS_ABSTAIN;

        FilterInvocation fi = (FilterInvocation) object;
        String requestUrl = fi.getRequestUrl();

        if (!(requestUrl.matches("/api/v1/(json|xml)/(.*)"))) {
            return result;
        }

        result = ACCESS_DENIED;

        if (!(authentication instanceof OAuth2Authentication)) {
            return result;
        }

        HttpServletRequest httpRequest = fi.getHttpRequest();
        OAuth2Authentication oauth2Authentication = (OAuth2Authentication) authentication;
        OAuth2Request clientAuthentication = oauth2Authentication.getOAuth2Request();
        String clientId = clientAuthentication.getClientId();
        Long userId = ((User) oauth2Authentication.getUserAuthentication().getPrincipal()).getId();

        MethodRestProviderForEntity methodDetermine = determineMethod(requestUrl, httpRequest, clientId, userId);

        if (methodDetermine == null) return result;

        result = ACCESS_GRANTED;

        return result;
    }

    private MethodRestProviderForEntity determineMethod(String requestUrl,
                                                        HttpServletRequest httpRequest,
                                                        String clientId,
                                                        Long userId) {

        requestUrl = requestUrl.replaceFirst("/api/v1/(json|xml)/", "/api/v1/{format}/");
        requestUrl = requestUrl.replaceFirst("/\\d+", "/{id}");

        RequestMethod requestMethod = RequestMethod.valueOf(httpRequest.getMethod());

        Set<MethodRestProviderForEntity> allowedMethods = methodRestProviderForEntityService.findAllowedMethods(requestUrl, requestMethod, clientId, userId);

        Set parametersKeys = httpRequest.getParameterMap().keySet();

        String copyOfRequestUrl = requestUrl;

        Optional<MethodRestProviderForEntity> method = allowedMethods.stream()
                .filter(methodRestProviderForEntity -> {
                    Map<String, String> inParameters = methodRestProviderForEntity.getInParameters();
                    boolean empty = inParameters.isEmpty();
                    return inParameters
                            .entrySet().stream()
                            .allMatch(entry -> checkInParameter(entry,
                                    httpRequest,
                                    copyOfRequestUrl,
                                    methodRestProviderForEntity.getEntityRestProviderInformation().getEntityClass(),
                                    empty
                            ));
                })
                .findFirst();

        return method.isPresent() ? method.get() : null;

    }

    private boolean checkInParameter(Map.Entry<String, String> entry, HttpServletRequest httpRequest, String requestUrl, String entityClass, boolean empty) {
        String paramName = entry.getKey();
        String paramType = entry.getValue();

        switch (paramType) {

            case "Long" :
                return requestUrl.contains("/{id}");

            case "String" :
                return httpRequest.getAttribute(paramName) != null;

            default:
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.readValue(httpRequest.getReader(), Class.forName(ControllerReflectionUtil.ENTITY_PACKAGE + "." + entityClass));
                    return true;
                } catch (Exception e) {
                    return empty;
                }

        }
    }

}
