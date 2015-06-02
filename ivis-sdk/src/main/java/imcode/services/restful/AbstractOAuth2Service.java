package imcode.services.restful;

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
    private RestServiseRequest findRequest;
    private RestServiseRequest createRequest;
    private RestServiseRequest updateRequest;
    private RestServiseRequest existsRequest;
    private RestServiseRequest deleteRequest;
    private IvisServiceFactory factory;

//    private OAuth2AccessToken accessToken;
//
//    private OAuth2ProtectedResourceDetails client;

    public AbstractOAuth2Service() {
    }

    public AbstractOAuth2Service(IvisServiceFactory factory, String mainServiceAddres) {
//        this.client = client;
//        this.accessToken = accessToken;
        this.factory = factory;
        fillServiseAdderess(mainServiceAddres);
    }

    public AbstractOAuth2Service(IvisServiceFactory factory) {
//        this.setClient(ivisServiceFactory.getClient());
//        this.setAccessToken(ivisServiceFactory.getAccessToken());
        this.factory = factory;
    }

    private OAuth2ProtectedResourceDetails getClient() {
        return factory.getClient();
    }
    private OAuth2ClientContext getClientContext() {
        return factory.getClientContext();
    }

    protected OAuth2RestTemplate getRestTemplate() {
//        OAuth2ClientContext clientContext = new DefaultOAuth2ClientContext(accessToken);

        return new OAuth2RestTemplate(getClient(), getClientContext());
    }

//    public void setClient(OAuth2ProtectedResourceDetails client) {
//        this.client = client;
//    }
//
//    public OAuth2AccessToken getAccessToken() {
//        return accessToken;
//    }

//    public void setAccessToken(OAuth2AccessToken accessToken) {
//        this.accessToken = accessToken;
//    }

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

    public RestServiseRequest getFindRequest() {
        return findRequest;
    }

    public void setFindRequest(RestServiseRequest findRequest) {
        this.findRequest = findRequest;
    }

    public RestServiseRequest getCreateRequest() {
        return createRequest;
    }

    public void setCreateRequest(RestServiseRequest createRequest) {
        this.createRequest = createRequest;
    }

    public RestServiseRequest getUpdateRequest() {
        return updateRequest;
    }

    public void setUpdateRequest(RestServiseRequest updateRequest) {
        this.updateRequest = updateRequest;
    }

    public RestServiseRequest getExistsRequest() {
        return existsRequest;
    }

    public void setExistsRequest(RestServiseRequest existsRequest) {
        this.existsRequest = existsRequest;
    }

    public RestServiseRequest getDeleteRequest() {
        return deleteRequest;
    }

    public void setDeleteRequest(RestServiseRequest deleteRequest) {
        this.deleteRequest = deleteRequest;
    }

    public void fillServiseAdderess(String mainServiceAddres) {
        this.mainServiceAddres = mainServiceAddres;
        createRequest = new RestServiseRequest(mainServiceAddres, HttpMethod.POST);
        findAllRequest = new RestServiseRequest(mainServiceAddres);
        findRequest = new RestServiseRequest(mainServiceAddres + "/{id}");
        existsRequest = new RestServiseRequest(findRequest.address);
        updateRequest = new RestServiseRequest(findRequest.address, HttpMethod.PUT);
        deleteRequest = new RestServiseRequest(findRequest.address, HttpMethod.DELETE);

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
