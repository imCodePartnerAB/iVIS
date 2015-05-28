package com.imcode.imcms.addon.ivisclient.utils;

import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by vitaly on 28.05.15.
 */
public class IvisOAuth2Utils {
    public static final String CLIENT_CONTEXT_PARAMETER_NAME = "oauth2ClientContext";

    public static OAuth2ClientContext getClientContext(HttpServletRequest request) {
        return getClientContext(request.getSession());
    }

    public static OAuth2ClientContext getClientContext(HttpSession session) {
        OAuth2ClientContext clientContext = null;
        clientContext = (OAuth2ClientContext) session.getAttribute(CLIENT_CONTEXT_PARAMETER_NAME);
        
        return clientContext;
    }

    public static void setClientContext(HttpServletRequest request, OAuth2ClientContext clientContext) {
        setClientContext(request.getSession(), clientContext);
    }

    public static void setClientContext(HttpSession session, OAuth2ClientContext clientContext) {
        session.setAttribute(CLIENT_CONTEXT_PARAMETER_NAME, clientContext);
    }

    public static void setAccessToken(HttpSession session, OAuth2AccessToken accessToken) {
        OAuth2ClientContext clientContext = getClientContext(session);

        if (clientContext == null) {
            clientContext = new DefaultOAuth2ClientContext();
            setClientContext(session, clientContext);
        }
        
        clientContext.setAccessToken(accessToken);
    }

    public static void setAccessToken(HttpServletRequest request, OAuth2AccessToken accessToken) {
        setAccessToken(request.getSession(), accessToken);
    }

    public static OAuth2AccessToken getAccessToken(HttpServletRequest request) {
        return getAccessToken(request.getSession());
    }

    public static OAuth2AccessToken getAccessToken(HttpSession session) {
        OAuth2AccessToken accessToken = null;
        OAuth2ClientContext clientContext = (OAuth2ClientContext) session.getAttribute(CLIENT_CONTEXT_PARAMETER_NAME);
        
        if (clientContext != null) {
            accessToken = clientContext.getAccessToken();
        }

        return accessToken;
    }
    
}
