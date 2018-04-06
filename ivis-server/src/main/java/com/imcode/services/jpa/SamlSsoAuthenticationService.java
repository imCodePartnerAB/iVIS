package com.imcode.services.jpa;

import com.imcode.saml2.auth.server.SAMLAttribute;
import com.imcode.saml2.auth.server.SAMLPrincipal;
import com.imcode.saml2.auth.server.SamlMessageHandler;
import com.imcode.services.SsoAuthenticationService;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.NameIDType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Service
public class SamlSsoAuthenticationService implements SsoAuthenticationService {

    private static final String USERNAME_ATTRIBUTE = "username";

    private final SamlMessageHandler samlMessageHandler;

    @Autowired
    public SamlSsoAuthenticationService(SamlMessageHandler samlMessageHandler) {
        this.samlMessageHandler = samlMessageHandler;
    }

    @Override
    public void authenticate(final HttpServletRequest request, final HttpServletResponse response) {

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
}
