package com.imcode.saml2.auth.server;

import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.springframework.security.saml.context.SAMLContextProviderLB;
import org.springframework.security.saml.context.SAMLMessageContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

public class ProxiedSAMLContextProviderLB extends SAMLContextProviderLB {

    ProxiedSAMLContextProviderLB(URI uri) {
        super();

        setServerName(uri.getHost());
        setScheme(uri.getScheme());
        setContextPath("");

        if (uri.getPort() > 0) {
            setIncludeServerPortInRequestURL(true);
            setServerPort(uri.getPort());
        }
    }

    @Override
    public void populateGenericContext(final HttpServletRequest request,
                                       final HttpServletResponse response,
                                       final SAMLMessageContext context) throws MetadataProviderException {

        super.populateGenericContext(request, response, context);
    }
}
