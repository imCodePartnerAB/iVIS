package com.imcode.imcms.addon.ivisclient.oauth2;

import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

/**
 * Created by vitaly on 02.06.15.
 */
public class IvisAuthorizationCodeResourceDetails extends AuthorizationCodeResourceDetails {
    private boolean clientOnly = true;

    @Override
    public boolean isClientOnly() {
        return clientOnly;
    }

    public void setClientOnly(boolean clientOnly) {
        this.clientOnly = clientOnly;
    }
}
