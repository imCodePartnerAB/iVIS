package com.imcode.saml2.auth.server;

import org.opensaml.common.binding.security.IssueInstantRule;
import org.opensaml.common.binding.security.MessageReplayRule;
import org.opensaml.saml2.binding.decoding.HTTPRedirectDeflateDecoder;
import org.opensaml.saml2.binding.encoding.HTTPPostSimpleSignEncoder;
import org.opensaml.util.storage.MapBasedStorageService;
import org.opensaml.util.storage.ReplayCache;
import org.opensaml.ws.security.SecurityPolicyRule;
import org.opensaml.ws.security.provider.BasicSecurityPolicy;
import org.opensaml.ws.security.provider.StaticSecurityPolicyResolver;
import org.opensaml.xml.parse.StaticBasicParserPool;
import org.springframework.security.saml.key.JKSKeyManager;
import org.springframework.security.saml.key.KeyManager;
import org.springframework.security.saml.util.VelocityFactory;

import java.security.KeyStore;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SamlMessageHandlerFactory {

    private static final String DEFAULT_TEMPLATE_ID = "/templates/saml2-post-simplesign-binding.vm";
    private static final int DEFAULT_DURATION = 14400000;
    private static final int DEFAULT_CLOCK_SKEW = 300;
    private static final int DEFAULT_MESSAGE_EXPIRES = 300;

    public static SamlMessageHandler getSamlMessageHandler(final String idpBaseUrl, final String entityId,
                                                           final String certificate, final String privateKey,
                                                           final String idpPassword) throws Exception {

        final StaticBasicParserPool parserPool = new StaticBasicParserPool();
        parserPool.initialize();

        final BasicSecurityPolicy securityPolicy = new BasicSecurityPolicy();

        final List<SecurityPolicyRule> policyRules = Arrays.asList(
                new IssueInstantRule(DEFAULT_CLOCK_SKEW, DEFAULT_MESSAGE_EXPIRES),
                new MessageReplayRule(new ReplayCache(new MapBasedStorageService<>(), DEFAULT_DURATION))
        );

        securityPolicy.getPolicyRules().addAll(policyRules);

        final KeyStore keyStore = KeyStoreFactory.createKeyStore(entityId, idpPassword, certificate, privateKey);

        final KeyManager keyManager = new JKSKeyManager(
                keyStore, Collections.singletonMap(entityId, idpPassword), entityId
        );

        final HTTPRedirectDeflateDecoder httpRedirectDeflateDecoder = new HTTPRedirectDeflateDecoder(parserPool);

        final HTTPPostSimpleSignEncoder httpPostSimpleSignEncoder = new HTTPPostSimpleSignEncoder(
                VelocityFactory.getEngine(), DEFAULT_TEMPLATE_ID, true);

        final StaticSecurityPolicyResolver securityPolicyResolver = new StaticSecurityPolicyResolver(securityPolicy);

        return new SamlMessageHandler(entityId, keyManager, httpRedirectDeflateDecoder, httpPostSimpleSignEncoder,
                securityPolicyResolver, idpBaseUrl);
    }
}
