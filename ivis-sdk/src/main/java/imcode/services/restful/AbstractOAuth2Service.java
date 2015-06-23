package imcode.services.restful;

import com.imcode.entities.AbstractIdEntity;
import com.imcode.services.GenericService;
import imcode.services.IvisServiceFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by vitaly on 28.05.15.
 */
public abstract class AbstractOAuth2Service<T, ID> implements GenericService<T, ID> {
    private String mainServiceAddres;

    private RestServiseRequest findAllRequest;

    private RestServiseRequest findRequest;

    private RestServiseRequest createRequest;

    private RestServiseRequest updateRequest;

    private RestServiseRequest existsRequest;

    private RestServiseRequest deleteRequest;

    private IvisServiceFactory factory;

    private Class<T> entityClass;

    private Class<ID> entityIdClass;


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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public AbstractOAuth2Service() {
    }

    public AbstractOAuth2Service(IvisServiceFactory factory) {
        this(factory, "", null, null);
    }

    public AbstractOAuth2Service(IvisServiceFactory factory, String mainServiceAddres) {
        this(factory, mainServiceAddres, null, null);
    }

    public AbstractOAuth2Service(IvisServiceFactory factory, String mainServiceAddres, Class<T> entityClass, Class<ID> entityIdClass) {
        this.factory = factory;
        this.mainServiceAddres = mainServiceAddres;
        this.entityClass = entityClass;
        this.entityIdClass = entityIdClass;
        fillServiseAdderess(mainServiceAddres);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private OAuth2ProtectedResourceDetails getClient() {
        return factory.getClient();
    }

    private OAuth2ClientContext getClientContext() {
        return factory.getClientContext();
    }

    protected OAuth2RestTemplate getRestTemplate() {
        return new OAuth2RestTemplate(getClient(), getClientContext());
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public T save(T entity) {
        T result = null;
        AbstractIdEntity idEntity = (AbstractIdEntity) entity;
        RestTemplate restTemplate = getRestTemplate();
        RestServiseRequest request = null;
        Object[] uriVariables = null;

        if (idEntity.getId() == null) {
            request = getCreateRequest();
            String uri = request.getAddress();
            HttpMethod method = request.getMethod();
            result = restTemplate.postForObject(uri, entity, getGeneticType("T"));
        } else {
            request = getUpdateRequest();
            uriVariables = new Object[]{idEntity.getId()};
            String uri = request.getAddress();
            HttpMethod method = request.getMethod();
            restTemplate.put(uri, entity, uriVariables);
        }

        return result;
    }

    @Override
    public T find(ID id) {
        T result = null;
        RestTemplate restTemplate = getRestTemplate();
        RestServiseRequest request = null;

        request = getFindRequest();
        Object[] uriVariables = {id};

        String uri = request.getAddress();
        HttpMethod method = request.getMethod();

        ResponseEntity responseEntity = restTemplate.exchange(uri, method, null, getEntityClass(), uriVariables);

        if (responseEntity.getBody() != null) {
            result = (T) responseEntity.getBody();
        }

        return result;
    }

    @Override
    public boolean exist(ID id) {
        boolean result = false;
        RestTemplate restTemplate = getRestTemplate();
        RestServiseRequest request = getExistsRequest();
        String uri = request.getAddress();
        HttpMethod method = request.getMethod();

        Object[] uriVariables = {id};

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(uri, method, null, Boolean.class, uriVariables);

        if (responseEntity.getBody() != null) {
            result = responseEntity.getBody();
        }
        return result;
    }

    @Override
    public void delete(ID id) {
        RestTemplate restTemplate = getRestTemplate();
        RestServiseRequest request = getDeleteRequest();
        String uri = request.getAddress();
        HttpMethod method = request.getMethod();

        Object[] uriVariables = {id};

        restTemplate.exchange(uri, method, null, void.class, uriVariables);
    }

    @Override
    public List<T> findAll() {
        List<T> result = new LinkedList<>();
        RestServiseRequest request = getFindAllRequest();
        String uri = request.getAddress();
        HttpMethod method = request.getMethod();

        RestTemplate restTemplate = getRestTemplate();
        ParameterizedTypeReference typeReference = getListTypeReference();
        ResponseEntity responseEntity = restTemplate.exchange(uri, method, null, typeReference);

        if (responseEntity.getBody() != null) {
            return (List<T>) responseEntity.getBody();
        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Class<T> getGeneticType(String variableName) {
        Map<TypeVariable, Type> params = GenericTypeResolver.getTypeVariableMap(getClass());
        for (Map.Entry<TypeVariable, Type> entry : params.entrySet()) {
            if (entry.getKey().getName().equals(variableName))
                return (Class<T>) entry.getValue();
        }

        return null;
    }
    protected ParameterizedTypeReference getListTypeReference() {
        Class entityClass = getEntityClass();
        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<List>() {
            @Override
            public Type getType() {
                return ParameterizedTypeImpl.make(List.class, new Class[]{entityClass}, null);
            }
        };

        return typeReference;
    }

    protected Class getEntityClass() {
        if (entityClass != null) {
            return entityClass;
        }

        Type[] genericArguments = getGenericParameterTypes();
        return (Class) genericArguments[0];
    }

    protected Class getIdClass() {
        if (entityIdClass != null) {
            return entityIdClass;
        }

        Type[] genericArguments = getGenericParameterTypes();
        return (Class) genericArguments[1];
    }

    protected Type[] getGenericParameterTypes() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return parameterizedType.getActualTypeArguments();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

//    protected String getDefaultServiseLocation() {
//        this.
//    }
}
