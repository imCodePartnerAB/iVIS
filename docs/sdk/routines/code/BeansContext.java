import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import com.imcode.imcms.addon.ivisclient.oauth2.IvisAuthorizationCodeResourceDetails;

@Configuration
public class BeansContext {

    @Value("${client-id}")
    private String clientId;

    @Value("${client-secret}")
    private String clientSecret;

    @Value("${user-authorization-uri}")
    private String userAuthorizationUri;

    @Value("${access-token-uri}")
    private String accessTokenUri;

    @Bean
    public OAuth2ProtectedResourceDetails transferService() {
        IvisAuthorizationCodeResourceDetails client = new IvisAuthorizationCodeResourceDetails();


        return client;
    }
}