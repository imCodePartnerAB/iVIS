package imcode.services.restful;

import com.imcode.entities.School;
import com.imcode.services.GenericService;
import com.imcode.services.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.*;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.client.RestClientException;

import java.util.*;

/**
 * Created by vitaly on 27.05.15.
 */
public class IvisServiceFactory {
    private final String DEFAULT_IVIS_ADDRESS = "http://localhost:8080/ivis/api/v1/json/";
//    private String ivisServiceAddress = DEFAULT_IVIS_ADDRESS;
    private OAuth2AccessToken accessToken;

    private OAuth2ProtectedResourceDetails client;

    private Map<Class<? extends GenericService>, GenericService> serviceMap;

    public IvisServiceFactory() {

    }

    public IvisServiceFactory(OAuth2ProtectedResourceDetails client, OAuth2AccessToken accessToken) {
        Map<Class<? extends GenericService>, GenericService> map = new HashMap<Class<? extends GenericService>, GenericService>();

        OAuth2SchoolService schoolService = new OAuth2SchoolService(client, accessToken, DEFAULT_IVIS_ADDRESS + "schools");
        map.put(SchoolService.class, schoolService);
        serviceMap = Collections.unmodifiableMap(map);

        this.client = client;
        this.accessToken = accessToken;
    }

    public <T extends GenericService> T getService(Class<T> serviceClass) {
        return (T) serviceMap.get(serviceClass);
    }

    public SchoolService getSchoolService() {
        return getService(SchoolService.class);
    }

    public OAuth2AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(OAuth2AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public OAuth2ProtectedResourceDetails getClient() {
        return client;
    }

    public void setClient(OAuth2ProtectedResourceDetails client) {
        this.client = client;
    }

//    private static final class SchoolServiceRestful implements SchoolService {
//
//        public School save(School entity) {
//            return null;
//        }
//
//        public School find(Long aLong) {
//            return null;
//        }
//
//        public boolean exist(Long aLong) {
//            return false;
//        }
//
//        public void delete(Long aLong) {
//
//        }
//
//        public List<School> findAll() {
//            return null;
//        }
//    }

    public static void main(String[] args) {
//        IvisServiceFactory serviceFactory = new IvisServiceFactory();
//        SchoolService schoolService = serviceFactory.getService(SchoolService.class);

//        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate();

//        <oauth:resource
//                id="ivis"
//        type=
//
//        client-id=
//        client-secret="secret"
//        access-token-uri="${ivisAccessTokenUri}"
//        user-authorization-uri="${ivisUserAuthorizationUri}"
//        scope= />

//        Client Resource
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setId("ivis");
        resource.setClientId("2e9fa895-e577-4156-844a-2cbb1ebbe06d");
        resource.setGrantType("authorization_code");
        resource.setClientSecret("secret");
        resource.setAccessTokenUri("http://localhost:8080/ivis/oauth/token");
        resource.setScope(Arrays.asList("read"));
        resource.setUserAuthorizationUri("http://localhost:8080/ivis/oauth/authorize");
//        resource.set();

//      Access token
//      Refresh token
        int expired = 599;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, expired);
        Date ecxpirationDate = calendar.getTime();

        Set<String> scopes = new HashSet<String>();
        scopes.add("read");
        DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken("3f772c51-6b3d-4ab6-921e-ca67cc71d795");
        accessToken.setTokenType("bearer");
        accessToken.setScope(scopes);
        accessToken.setRefreshToken(new DefaultExpiringOAuth2RefreshToken("fb99a06d-027d-4550-b969-f9bda5103e6d", ecxpirationDate));
        accessToken.setExpiration(ecxpirationDate);

        //Client context
        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        clientContext.setAccessToken(accessToken);

        //Authorization Access token provider
//        AuthorizationCodeAccessTokenProvider accessTokenProvider = new AuthorizationCodeAccessTokenProvider();


//        Oauth2 rest template
//        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, clientContext);
//        ResponseEntity<School[]> responseEntity = null;
//
//        try {
//            responseEntity = restTemplate.getForEntity("http://localhost:8080/ivis/api/v1/json/schools", School[].class);
//            System.out.println(responseEntity.getBody());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        IvisServiceFactory serviceFactory = IvisFasade.getServiceFactory(resource, accessToken);
        SchoolService schoolService = serviceFactory.getService(SchoolService.class);

        List<School> schools = schoolService.findAll();

        System.out.println(schools);


    }


}
