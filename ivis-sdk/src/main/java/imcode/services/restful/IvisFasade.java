package imcode.services.restful;

import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * Created by vitaly on 28.05.15.
 */
public class IvisFasade {
    public static IvisServiceFactory getServiceFactory(OAuth2ProtectedResourceDetails client, OAuth2AccessToken accessToken) {
        return new IvisServiceFactory(client, accessToken);
    }
}
