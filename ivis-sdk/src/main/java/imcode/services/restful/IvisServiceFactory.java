package imcode.services.restful;

import com.imcode.services.GenericService;
import com.imcode.services.SchoolService;
import com.imcode.services.StatementService;
import imcode.services.utils.IvisOAuth2Utils;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.*;

/**
 * Created by vitaly on 27.05.15.
 */
public class IvisServiceFactory {
    private final String apiUrl; //= "http://ivis.dev.imcode.com/api/v1/json/";
    //    private String ivisServiceAddress = apiUrl;
    private OAuth2AccessToken accessToken;

    private OAuth2ProtectedResourceDetails client;

    private Map<Class<? extends GenericService>, GenericService> serviceMap;


    public IvisServiceFactory(String apiUrl, OAuth2ProtectedResourceDetails client, OAuth2AccessToken accessToken) {
        this.client = client;
        this.accessToken = accessToken;
        this.apiUrl = apiUrl;
        Map<Class<? extends GenericService>, GenericService> map = new HashMap<>();

        OAuth2SchoolService schoolService = new OAuth2SchoolService(this);
        schoolService.fillServiseAdderess(apiUrl + "schools");
        map.put(SchoolService.class, schoolService);

        OAuth2StatementService statementService = new OAuth2StatementService(this);
        statementService.fillServiseAdderess(apiUrl + "statements");
        map.put(StatementService.class, statementService);


        serviceMap = Collections.unmodifiableMap(map);
    }

    public <T extends GenericService> T getService(Class<T> serviceClass) {
        return (T) serviceMap.get(serviceClass);
    }

    public SchoolService getSchoolService() {
        return getService(SchoolService.class);
    }

    public StatementService getStatementService() {
        return getService(StatementService.class);
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
//        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setId("ivis");
        resource.setClientId("b4251265-409d-43b3-928d-a290228a2b59");
        resource.setGrantType("authorization_code");
        resource.setClientSecret("secret");
        resource.setAccessTokenUri("http://localhost:8080/ivis/oauth/token");
        resource.setScope(Arrays.asList("read"));
        resource.setUsername("admin");
        resource.setPassword("pass");
//        resource.setUserAuthorizationUri("http://localhost:8080/ivis/oauth/authorize");
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
        DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken("37a96f4f-3311-4055-b07d-1fb7e4dac9a7");
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
//        ResponseEntity<Statement[]> responseEntity = null;
//        ResponseEntity<String> responseEntity = null;

        try {
            IvisFacade.Configuration config = new IvisFacade.Configuration.Builder().endPointUrl("http://localhost:8080").build();
//            config.endPointUrl
            IvisFacade facade = IvisFacade.instance(config);
            IvisServiceFactory serviceFactory = facade.getServiceFactory(resource, accessToken);
            StatementService statementService = serviceFactory.getStatementService();
            System.out.println(statementService.findAll());
//            System.out.println(IvisOAuth2Utils.getOAuth2AuthirizationUrl(resource, "http://localhost"));
//            AuthorizationCodeAccessTokenProvider tokenProvider = new AuthorizationCodeAccessTokenProvider();
//            tokenProvider.obtainAuthorizationCode(resource, new DefaultAccessTokenRequest());
//            responseEntity = restTemplate.getForEntity("http://localhost:8080/ivis/api/v1/json/statements", String.class);
//            responseEntity = restTemplate.getForEntity("http://localhost:8080/ivis/api/v1/json/statements", Statement[].class);
//            System.out.println(responseEntity.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        IvisServiceFactory serviceFactory = IvisFacade.getServiceFactory(resource, accessToken);
////        SchoolService schoolService = serviceFactory.getService(SchoolService.class);
////        List<School> schools = schoolService.findAll();
////        System.out.println(schools);
//        StatementService statementService = serviceFactory.getService(StatementService.class);
//        List<Statement> statements = statementService.findAll();
//        System.out.println(statements);


    }


}
