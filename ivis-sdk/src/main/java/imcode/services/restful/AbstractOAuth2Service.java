package imcode.services.restful;

import com.sun.xml.internal.ws.api.policy.PolicyResolver;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * Created by vitaly on 28.05.15.
 */
public class AbstractOAuth2Service {
    private String mainServiceAddres;
    private RestServiseRequest findAllRequest;
//    private String findServiceAddres;
//    private String saveServiceAddres;
//    private String existsServiceAddres;
//    private String deleteServiceAddres;

    private OAuth2AccessToken accessToken;

    private OAuth2ProtectedResourceDetails client;

    public AbstractOAuth2Service() {
    }

    public AbstractOAuth2Service(OAuth2ProtectedResourceDetails client, OAuth2AccessToken accessToken, String mainServiceAddres) {
        this.client = client;
        this.accessToken = accessToken;
        fillServiseAdderess(mainServiceAddres);
    }

    public AbstractOAuth2Service(IvisServiceFactory ivisServiceFactory) {
        this.setClient(ivisServiceFactory.getClient());
        this.setAccessToken(ivisServiceFactory.getAccessToken());
    }

    public OAuth2ProtectedResourceDetails getClient() {
        return client;
    }

    protected OAuth2RestTemplate getRestTemplate() {
        OAuth2ClientContext clientContext = new DefaultOAuth2ClientContext(accessToken);

        return new OAuth2RestTemplate(client, clientContext);
    }


    public void setClient(OAuth2ProtectedResourceDetails client) {
        this.client = client;
    }

    public OAuth2AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(OAuth2AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public String getMainServiceAddres() {
        return mainServiceAddres;
    }

    public void setMainServiceAddres(String mainServiceAddres) {
        this.mainServiceAddres = mainServiceAddres;
    }

    public RestServiseRequest getFindAllRequest() {
        return findAllRequest;
    }

    public void setFindAllRequest(RestServiseRequest findAllRequest) {
        this.findAllRequest = findAllRequest;
    }

    protected void fillServiseAdderess(String mainServiceAddres) {
        this.mainServiceAddres = mainServiceAddres;
        findAllRequest = new RestServiseRequest(mainServiceAddres);
    }

    public static class RestServiseRequest {
        private static final HttpMethod DEFAULT_METHOD = HttpMethod.GET;
        private String address;
        private HttpMethod method = DEFAULT_METHOD;

        public RestServiseRequest() {
        }

        public RestServiseRequest(String address) {
            this.address = address;
        }

        public RestServiseRequest(String address, HttpMethod method) {
            this.address = address;
            this.method = method;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public HttpMethod getMethod() {
            return method;
        }

        public void setMethod(HttpMethod method) {
            this.method = method;
        }
    }
}
