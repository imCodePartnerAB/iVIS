import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import javax.servlet.Filter;
import com.imcode.imcms.addon.ivisclient.oauth2.IvisAuthorizationCodeResourceDetails;
import imcode.services.utils.builders.CollectionBuilder;
import imcode.services.filter.IvisAuthorizedFilter;

@Configuration
public class Configuration {

    @Value("${client-id}")
    private String clientId;

    @Value("${client-secret}")
    private String clientSecret;

    @Value("#{'${api-server-address}' + '${user-authorization-relate-uri}'}")
    private String userAuthorizationUri;

    @Value("#{'${api-server-address}' + '${access-token-relate-uri}'}")
    private String accessTokenUri;

    @Bean
    public OAuth2ProtectedResourceDetails clientBean() {
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

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ivisAuthorizedFilter());
        registration.addUrlPatterns("/persons/*");
        registration.addUrlPatterns("/pupils/*");
        registration.addInitParameter("roles", "ROLE_ADMIN,ROLE_DEVELOPER");
        registration.setName("ivisAuthorizedFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name = "ivisAuthorizedFilter")
    public Filter ivisAuthorizedFilter() {
        return new IvisAuthorizedFilter();
    }

}