package com.imcode.saml2.auth.server;

import org.joda.time.DateTime;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.decoding.SAMLMessageDecoder;
import org.opensaml.common.binding.encoding.SAMLMessageEncoder;
import org.opensaml.saml2.core.*;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.ws.security.SecurityPolicyResolver;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.security.CriteriaSet;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.criteria.EntityIDCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.saml.context.SAMLMessageContext;
import org.springframework.security.saml.key.KeyManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

import static com.imcode.saml2.auth.server.SAMLBuilder.*;

public class SamlMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(SamlMessageHandler.class);

    private final String entityId;
    private final KeyManager keyManager;
    private final SAMLMessageDecoder decoder;
    private final SAMLMessageEncoder encoder;
    private final SecurityPolicyResolver resolver;
    private final ProxiedSAMLContextProviderLB proxiedSAMLContextProviderLB;

    public SamlMessageHandler(String entityId, KeyManager keyManager, SAMLMessageDecoder decoder,
                              SAMLMessageEncoder encoder, SecurityPolicyResolver securityPolicyResolver,
                              String idpBaseUrl) throws URISyntaxException {

        this.entityId = entityId;
        this.keyManager = keyManager;
        this.encoder = encoder;
        this.decoder = decoder;
        this.resolver = securityPolicyResolver;
        this.proxiedSAMLContextProviderLB = new ProxiedSAMLContextProviderLB(new URI(idpBaseUrl));
    }

    public SAMLMessageContext extractSamlRequestMessage(final HttpServletRequest request,
                                                        final HttpServletResponse response) {
        try {
            final SAMLMessageContext messageContext = new SAMLMessageContext();

            proxiedSAMLContextProviderLB.populateGenericContext(request, response, messageContext);
            messageContext.setSecurityPolicyResolver(resolver);
            decoder.decode(messageContext);

            return messageContext;

        } catch (Exception e) {
            logger.error("Error is occurred during extracting message from SAML request.", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public void sendSamlResponse(final SAMLPrincipal principal, final HttpServletResponse response) {
        try {
            final Response authResponse = buildSAMLObject(Response.class, Response.DEFAULT_ELEMENT_NAME);
            final Issuer issuer = buildIssuer(entityId);

            authResponse.setIssuer(issuer);
            authResponse.setID(SAMLBuilder.randomSAMLId());
            authResponse.setIssueInstant(new DateTime());
            authResponse.setInResponseTo(principal.getRequestID());

            final Status status = buildStatus(StatusCode.SUCCESS_URI);
            final Assertion assertion = buildAssertion(principal, status, entityId);

            final Credential signingCredential = keyManager
                    .resolveSingle(new CriteriaSet(new EntityIDCriteria(entityId)));

            signAssertion(assertion, signingCredential);

            authResponse.getAssertions().add(assertion);
            authResponse.setDestination(principal.getAssertionConsumerServiceURL());
            authResponse.setStatus(status);

            final Endpoint endpoint = buildSAMLObject(Endpoint.class, SingleSignOnService.DEFAULT_ELEMENT_NAME);
            endpoint.setLocation(principal.getAssertionConsumerServiceURL());

            final HttpServletResponseAdapter outTransport = new HttpServletResponseAdapter(response, false);

            final BasicSAMLMessageContext messageContext = new BasicSAMLMessageContext();

            messageContext.setOutboundMessageTransport(outTransport);
            messageContext.setPeerEntityEndpoint(endpoint);
            messageContext.setOutboundSAMLMessage(authResponse);
            messageContext.setOutboundSAMLMessageSigningCredential(signingCredential);
            messageContext.setOutboundMessageIssuer(entityId);
            messageContext.setRelayState(principal.getRelayState());

            encoder.encode(messageContext);
        } catch (Exception e) {
            logger.error("Error is occurred during sending SAML response.", e);
            throw new RuntimeException(e);
        }
    }
}
