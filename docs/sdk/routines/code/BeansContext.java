import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import com.imcode.imcms.addon.ivisclient.oauth2.IvisAuthorizationCodeResourceDetails;
import imcode.services.utils.builders.CollectionBuilder;

@Configuration
public class BeansContext {

    @Value("${client-id}")
    private String clientId;

    @Value("${client-secret}")
    private String clientSecret;

    @Value("#{'${api-server-address}' + '${user-authorization-relate-uri}'}")
    private String userAuthorizationUri;

    @Value("#{'${api-server-address}' + '${access-token-relate-uri}'}")
    private String accessTokenUri;

    @Bean
    public OAuth2ProtectedResourceDetails cleintBean() {
        IvisAuthorizationCodeResourceDetails client = new IvisAuthorizationCodeResourceDetails();
        client.setClientOnly(true);
        client.setGrantType("authorization_code");
        client.setClientId(clientId);
        client.setClientSecret(clientSecret);
        client.setAccessTokenUri(accessTokenUri);
        client.setUserAuthorizationUri(userAuthorizationUri);
        client.setScope(CollectionBuilder.asLinkedList("read", "write"));
        return client;
    }
}