package com.imcode.saml2;

//import com.imcode.saml2.utils.*;
//import com.imcode.saml2.store.SAMLSessionInfo;
//import com.imcode.saml2.store.SAMLSessionManager;

import com.imcode.entities.User;
import com.imcode.saml2.store.SAMLSessionInfo;
import com.imcode.saml2.store.SAMLSessionManager;
import com.imcode.saml2.utils.OpenSamlBootstrap;
import com.imcode.saml2.utils.SAMLUtils;
import org.opensaml.common.binding.SAMLMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
//import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class provide filtering all request, which reference to CGI-IDP
 */
public class SAMLSPFilter implements Filter {
    private static final String SAML_AUTHN_RESPONSE_PARAMETER_NAME = "SAMLResponse";
    private static Logger log = LoggerFactory.getLogger(SAMLSPFilter.class);
    private FilterConfig filterConfig;
    private SAMLResponseVerifier checkSAMLResponse;
    private SAMLRequestSender samlRequestSender;
    private ApplicationContext context;

    @Override
    public void init(javax.servlet.FilterConfig config) {
        OpenSamlBootstrap.init();
        filterConfig = new FilterConfig(config);
        checkSAMLResponse = new SAMLResponseVerifier();
        samlRequestSender = new SAMLRequestSender();
        context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
//        System.out.println("dsfasdf");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
      /*
       * Check if request is not refer to CGI-IDP - ignore it;
      */
        if (!isFilteredRequest(request) || !filterConfig.isEnabled()) {
            log.debug("According to {} configuration parameter request is ignored + {}",
                    new Object[]{FilterConfig.EXCLUDED_URL_PATTERN_PARAMETER, request.getRequestURI()});
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        //Check is user alredy authorized
        if (getCurrentUser() != null) {
            return;
        }

        log.debug("Attempt to secure resource  is intercepted : {}", ((HttpServletRequest) servletRequest).getRequestURL().toString());
        /*
          Check if response message is received from identity provider;
          In case of successful response system redirects user to relayState (initial) request
        */
        String responseMessage = servletRequest.getParameter(SAML_AUTHN_RESPONSE_PARAMETER_NAME);
        if (responseMessage != null) {
            log.debug("Response from Identity Provider is received");
            try {
                log.debug("Decoding of SAML message");
                SAMLMessageContext samlMessageContext =
                        SAMLUtils.decodeSamlMessage((HttpServletRequest) servletRequest,
                                (HttpServletResponse) servletResponse);
                log.debug("SAML message has been decoded successfully");
                samlMessageContext.setLocalEntityId(filterConfig.getSpProviderId());
                //String relayState = getInitialRequestedResource(samlMessageContext);
                checkSAMLResponse.verify(samlMessageContext);
                log.debug("Starting and store SAML session..");
                SAMLSessionManager.getInstance().createSAMLSession(request, response,
                        samlMessageContext);
                // log.debug("User has been successfully authenticated in idP. Redirect to initial requested resource {}", relayState);
                //response.sendRedirect(relayState);

                SAMLSessionInfo samlSessionInfo = SAMLSessionManager.getInstance().getSAMLSession(request.getSession());
                SAMLSessionManager.getInstance().loginUser(samlSessionInfo, request, response);

                return;
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        /*
        * Check if request is logout request
        */
        if (getCorrectURL(request).equals(filterConfig.getLogoutUrl())) {
            log.debug("Logout action: destroying SAML session.");
            SAMLSessionManager.getInstance().destroySAMLSession(request.getSession());
            chain.doFilter(request, response);
            return;
        }
        /*
        *   Check if user has already authorised
        */
        if (SAMLSessionManager.getInstance().isSAMLSessionValid(request.getSession())) {
            SAMLSessionInfo samlSessionInfo = SAMLSessionManager.getInstance().getSAMLSession(request.getSession());
            SAMLSessionManager.getInstance().loginUser(samlSessionInfo, request, response);
            log.debug("SAML session exists and valid: grant access to secure resource");
            //chain.doFilter(request, response);
            return;
        }
        /*
        * Create SAML request and redirect user to CGI service for authentication
         */
        log.debug("Sending authentication request to idP");
        try {
            samlRequestSender.sendSAMLAuthRequest(request, response,
                    filterConfig.getSpProviderId(), filterConfig.getAcsUrl(),
                    filterConfig.getIdpSSOUrl());
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private boolean isFilteredRequest(HttpServletRequest request) {
        return !(filterConfig.getExcludedUrlPattern() != null &&
                getCorrectURL(request).matches(filterConfig.getExcludedUrlPattern()));
    }

    // Check if URL is correct
    private String getCorrectURL(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String requestUri = request.getRequestURI();
        int contextBeg = requestUri.indexOf(contextPath);
        int contextEnd = contextBeg + contextPath.length();
        String slash = "/";
        String url = (contextBeg < 0 || contextEnd == (requestUri.length() - 1))
                ? requestUri : requestUri.substring(contextEnd);
        if (!url.startsWith(slash)) {
            url = slash + url;
        }

        return url;
    }

    @Override
    public void destroy() {
        log = null;
    }

    public static User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            return principal instanceof User ? (User) principal : null;
        }

        return null;
    }
}
