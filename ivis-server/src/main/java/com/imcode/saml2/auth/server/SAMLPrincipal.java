package com.imcode.saml2.auth.server;

import lombok.Data;
import org.opensaml.saml2.core.AuthnRequest;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Data
public class SAMLPrincipal implements Principal {

    private final String nameID;
    private final String nameIDType;
    private final List<SAMLAttribute> attributes;
    private final String serviceProviderEntityID;
    private final String requestID;
    private final String assertionConsumerServiceURL;
    private final String relayState;

    public SAMLPrincipal(String nameID,
                         String nameIDType,
                         List<SAMLAttribute> attributes,
                         AuthnRequest authnRequest,
                         String relayState) {

        this.nameID = nameID;
        this.nameIDType = nameIDType;
        this.attributes = Collections.unmodifiableList(attributes);
        this.serviceProviderEntityID = authnRequest.getIssuer().getValue();
        this.requestID = authnRequest.getID();
        this.assertionConsumerServiceURL = authnRequest.getAssertionConsumerServiceURL();
        this.relayState = relayState;
    }

    @Override
    public String getName() {
        return nameID;
    }
}
