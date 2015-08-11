package imcode.services.restful;

import com.imcode.entities.Pupil;
import com.imcode.entities.School;
import com.imcode.entities.SchoolClass;
import com.imcode.services.*;
import imcode.services.IvisServiceFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;

import java.util.*;

/**
 * Created by vitaly on 27.05.15.
 */
@Deprecated
public class DefaultIvisServiceFactory{}
//        implements imcode.services.IvisServiceFactory {
//    private final String apiUrl; //= "http://ivis.dev.imcode.com/api/v1/json/";
//    //    private String ivisServiceAddress = apiUrl;
////    private OAuth2AccessToken accessToken;
//    private OAuth2ClientContext clientContext;
//
//    private OAuth2ProtectedResourceDetails client;
//
//    private Map<Class<? extends GenericService>, GenericService> serviceMap;
//
//
//    public DefaultIvisServiceFactory(String apiUrl, OAuth2ProtectedResourceDetails client, OAuth2ClientContext clientContext) {
//        this.client = client;
//        this.clientContext = clientContext;
//        this.apiUrl = apiUrl;
//        Map<Class<? extends GenericService>, GenericService> map = new HashMap<>();
//
//        OAuth2SchoolService schoolService = new OAuth2SchoolService(this);
//        schoolService.fillServiseAdderess(apiUrl + "schools");
//        map.put(SchoolService.class, schoolService);
//
//        OAuth2ApplicationService statementService = new OAuth2ApplicationService(this);
//        statementService.fillServiseAdderess(apiUrl + "statements");
//        map.put(ApplicationService.class, statementService);
//
//        OAuth2PupilService pupisService = new OAuth2PupilService(this);
//        pupisService.fillServiseAdderess(apiUrl + "pupils");
//        map.put(PupilService.class, pupisService);
//
//        OAuth2SchoolClassService schoolClassService = new OAuth2SchoolClassService(this);
//        schoolClassService.fillServiseAdderess(apiUrl + "classes");
//        map.put(SchoolClassService.class, schoolClassService);
//
//        serviceMap = Collections.unmodifiableMap(map);
//    }
//
//    public <S extends GenericService<T, ID>, T, ID> S getService(Class<S> serviceClass) {
//        S definedService = (S) serviceMap.get(serviceClass);
//
////        if (definedService != null) {
//        return definedService;
////        } else {
////            GenericService abstractServise =
////            return
////        }
//    }
//
//    public SchoolService getSchoolService() {
//        return getService(SchoolService.class);
//    }
//
//    public ApplicationService getStatementService() {
//        return getService(ApplicationService.class);
//    }
//
//    public PupilService getPupilService() {
//        return getService(PupilService.class);
//    }
//
//    public SchoolClassService getSchoolClassService() {
//        return getService(SchoolClassService.class);
//    }
//
////    public OAuth2AccessToken getAccessToken() {
////        return accessToken;
////    }
////
////    public void setAccessToken(OAuth2AccessToken accessToken) {
////        this.accessToken = accessToken;
////    }
//
//    public OAuth2ProtectedResourceDetails getClient() {
//        return client;
//    }
//
//    public void setClient(OAuth2ProtectedResourceDetails client) {
//        this.client = client;
//    }
//
//    public String getApiUrl() {
//        return apiUrl;
//    }
//
//    public OAuth2ClientContext getClientContext() {
//        return clientContext;
//    }
//
////    @Override
////    public <T> boolean hasServiceFor(Class<T> entity) {
////        return false;
////    }
//
//    public void setClientContext(OAuth2ClientContext clientContext) {
//        this.clientContext = clientContext;
//    }
//
//    //    private static final class SchoolServiceRestful implements SchoolService {
////
////        public School save(School entity) {
////            return null;
////        }
////
////        public School find(Long aLong) {
////            return null;
////        }
////
////        public boolean exist(Long aLong) {
////            return false;
////        }
////
////        public void delete(Long aLong) {
////
////        }
////
////        public List<School> findAll() {
////            return null;
////        }
////    }
//
//    public static void main(String[] args) {
////        DefaultIvisServiceFactory serviceFactory = new DefaultIvisServiceFactory();
////        SchoolService schoolService = serviceFactory.getService(SchoolService.class);
//
////        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate();
//
////        <oauth:resource
////                id="ivis"
////        type=
////
////        client-id=
////        client-secret="secret"
////        access-token-uri="${ivisAccessTokenUri}"
////        user-authorization-uri="${ivisUserAuthorizationUri}"
////        scope= />
//
////        Client Resource
////        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
//        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//        resource.setId("ivis");
//        resource.setClientId("b4251265-409d-43b3-928d-a290228a2b59");
////        resource.setGrantType("authorization_code");
//        resource.setGrantType("password");
//        resource.setClientSecret("secret");
//        resource.setAccessTokenUri("http://localhost:8080/ivis/oauth/token");
//        resource.setScope(Arrays.asList("read"));
//        resource.setUsername("admin");
//        resource.setPassword("pass");
////        resource.setUserAuthorizationUri("http://localhost:8080/ivis/oauth/authorize");
////        resource.set();
//
////      Access token
////      Refresh token
//        int expired = 599;
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.SECOND, expired);
//        Date ecxpirationDate = calendar.getTime();
//
//        Set<String> scopes = new HashSet<String>();
//        scopes.add("read");
//        DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken("acf95060-9303-4fbd-992a-c54157f16eab");
//        accessToken.setTokenType("bearer");
//        accessToken.setScope(scopes);
//        accessToken.setRefreshToken(new DefaultExpiringOAuth2RefreshToken("fb99a06d-027d-4550-b969-f9bda5103e6d", ecxpirationDate));
//        accessToken.setExpiration(ecxpirationDate);
//
//        //Client context
//        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
//        clientContext.setAccessToken(accessToken);
//
//        //Authorization Access token provider
////        AuthorizationCodeAccessTokenProvider accessTokenProvider = new AuthorizationCodeAccessTokenProvider();
//
//
////        Oauth2 rest template
////        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, clientContext);
////        ResponseEntity<Application[]> responseEntity = null;
////        ResponseEntity<String> responseEntity = null;
//
////        try {
////            IvisFacade.Configuration config = new IvisFacade.Configuration.Builder().endPointUrl("http://localhost:8080/ivis").build();
//////            config.endPointUrl
////            IvisFacade facade = IvisFacade.instance(config);
//////            DefaultIvisServiceFactory serviceFactory = facade.getServiceFactory(resource, clientContext);
////            IvisServiceFactory serviceFactory = new ProxyIvisServiceFactory("http://localhost:8080/ivis/api/v1/json", clientContext, resource);
//////            SchoolClassService service = serviceFactory.getSchoolClassService();
//////            PupilService service = serviceFactory.getService(PupilService.class);
//////            System.out.println(service.findByPersonalId("850717-5019"));
////            GenericService service = serviceFactory.getService(SchoolService.class);
////            GenericService service1 = serviceFactory.getService(SchoolClassService.class);
//////            List<Pupil> pupilList = service.findAll();
////            School school = (School) service.find(1L);
////            SchoolClass schoolClass = (SchoolClass) service1.find(1L);
////            System.out.println(service.findAll());
//////            ApplicationService statementService = serviceFactory.getStatementService();
//////            Application statement = new Application();
//////            statement.setStatus(StatementStatus.created);
//////            statement = statementService.save(statement);
//////            statement.setPupil(new Pupil());
//////            statementService.save(statement);
////
//////            System.out.println(statementService.findAll());
//////            System.out.println("save");
//////            Application statement = new Application();
//////            statement.setStatus(StatementStatus.created);
//////            statement = statementService.save(statement);
//////            System.out.println(statement);
//////            System.out.println("find one");
//////            System.out.println(statementService.find(statement.getId()));
//////            System.out.println("delete:" + statement.getId());
//////            statementService.delete(statement.getId());
//////            System.out.println(statementService.findAll());
////
//////            System.out.println(IvisOAuth2Utils.getOAuth2AuthirizationUrl(resource, "http://localhost"));
//////            AuthorizationCodeAccessTokenProvider tokenProvider = new AuthorizationCodeAccessTokenProvider();
//////            tokenProvider.obtainAuthorizationCode(resource, new DefaultAccessTokenRequest());
//////            responseEntity = restTemplate.getForEntity("http://localhost:8080/ivis/api/v1/json/statements", String.class);
//////            responseEntity = restTemplate.getForEntity("http://localhost:8080/ivis/api/v1/json/statements", Application[].class);
//////            System.out.println(responseEntity.getBody());
////        } catch (Exception e) {
////            e.printStackTrace();
////
////        }
////
//////        DefaultIvisServiceFactory serviceFactory = IvisFacade.getServiceFactory(resource, accessToken);
////////        SchoolService schoolService = serviceFactory.getService(SchoolService.class);
////////        List<School> schools = schoolService.findAll();
////////        System.out.println(schools);
//////        ApplicationService statementService = serviceFactory.getService(ApplicationService.class);
//////        List<Application> statements = statementService.findAll();
//////        System.out.println(statements);
////
//
//    }
//
//
//}
