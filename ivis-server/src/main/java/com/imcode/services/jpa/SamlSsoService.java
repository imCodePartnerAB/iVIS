package com.imcode.services.jpa;

import com.imcode.saml2.auth.server.SAMLAttribute;
import com.imcode.saml2.auth.server.SAMLPrincipal;
import com.imcode.saml2.auth.server.SamlMessageHandler;
import com.imcode.services.SsoService;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.saml2.core.impl.LogoutRequestImpl;
import org.opensaml.saml2.ecp.RelayState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Service
public class SamlSsoService implements SsoService {

    private static final Logger logger = LoggerFactory.getLogger(SamlSsoService.class);
    private static final String USERNAME_ATTRIBUTE = "username";

    private final SamlMessageHandler samlMessageHandler;

    @Autowired
    public SamlSsoService(SamlMessageHandler samlMessageHandler) {
        this.samlMessageHandler = samlMessageHandler;
    }

    @Override
    public void logIn(final HttpServletRequest request, final HttpServletResponse response) {

        final SAMLMessageContext messageContext = samlMessageHandler.extractSamlRequestMessage(request, response);

        final AuthnRequest authnRequest = (AuthnRequest) messageContext.getInboundSAMLMessage();

        final String loggedUserName = request.getUserPrincipal().getName();

        final SAMLPrincipal principal = new SAMLPrincipal(
                loggedUserName,
                NameIDType.UNSPECIFIED,
                Collections.singletonList(new SAMLAttribute(USERNAME_ATTRIBUTE, loggedUserName)),
                authnRequest,
                messageContext.getRelayState());

        samlMessageHandler.sendSamlResponse(principal, response);
    }

    @Override
    public void logOut(final HttpServletRequest request, final HttpServletResponse response) {
        checkLogoutRequest(request, response);
        invalidateUserSession();
        redirectUser(request, response);
    }

    private void checkLogoutRequest(final HttpServletRequest request, final HttpServletResponse response) {
        final SAMLMessageContext messageContext = samlMessageHandler.extractSamlRequestMessage(request, response);

        if (!(messageContext.getInboundSAMLMessage() instanceof LogoutRequestImpl)) {
            final String errorMessage = "Logout request from Service Provider is not correct.";

            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private void invalidateUserSession() {
        SecurityContextHolder.clearContext();
    }

    private void redirectUser(final HttpServletRequest request, final HttpServletResponse response) {
        final String redirectURL = request.getParameter(RelayState.DEFAULT_ELEMENT_LOCAL_NAME);

        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            logger.error(String.format("Error during redirection to URL: %s", redirectURL), e);
            throw new RuntimeException(e);
        }
    }
}
