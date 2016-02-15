package imcode.services.restful;

import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.services.GenericService;
import com.imcode.services.NamedService;
import com.imcode.services.PersonalizedService;
import imcode.services.IvisServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
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
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by vitaly on 28.05.15.
 */
public abstract class AbstractOAuth2Service<T, ID> implements GenericService<T, ID>, NamedService<T>, PersonalizedService<T> {
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
//            result = restTemplate.postForObject(uri, entity, getGeneticType("T"));
            result = (T) restTemplate.postForObject(uri, entity, getEntityClass());
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
    public T findFirstByName(String name) {
        T result = null;
        RestServiseRequest request = getFindAllRequest();
        String uri = request.getAddress() + "?name={id}&first=true";
        Object[] uriVariables = {name};
        HttpMethod method = request.getMethod();
        RestTemplate restTemplate = getRestTemplate();

        ResponseEntity responseEntity = restTemplate.exchange(uri, method, null, getEntityClass(), uriVariables);

        if (responseEntity.getBody() != null) {
            result = (T) responseEntity.getBody();
        }

        return result;
    }

    protected T sendRequest(RestServiseRequest request, Map<String, ?> params) {
        T result = null;

        String uri = buildUrlString(request, params);
        HttpMethod method = request.getMethod();
        RestTemplate restTemplate = getRestTemplate();

        ResponseEntity<T> responseEntity = restTemplate.exchange(uri, method, null, getEntityClass());

        if (responseEntity.getBody() != null) {
            result = responseEntity.getBody();
        }

        return result;
    }

    protected List<T> sendRequestList(RestServiseRequest request, Map<String, ?> params) {
        List<T> result = new LinkedList<>();

        String uri = buildUrlString(request, params);
        HttpMethod method = request.getMethod();
        RestTemplate restTemplate = getRestTemplate();
        ParameterizedTypeReference<List<T>> typeReference = getListTypeReference();
        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(uri, method, null, typeReference);

        if (responseEntity.getBody() != null) {
            return responseEntity.getBody();
        }

        return result;
    }


    @Override
    public List<T> findByName(String name) {
        List<T> result = new LinkedList<>();
        RestServiseRequest request = getFindAllRequest();
        String uri = request.getAddress() + "?name={id}";
        Object[] uriVariables = {name};
        HttpMethod method = request.getMethod();

        RestTemplate restTemplate = getRestTemplate();
        ParameterizedTypeReference typeReference = getListTypeReference();
        ResponseEntity responseEntity = restTemplate.exchange(uri, method, null, typeReference, uriVariables);

        if (responseEntity.getBody() != null) {
            return (List<T>) responseEntity.getBody();
        }

        return result;
    }


    @Override
    public T findFirstByPersonalId(String personalId) {
        T result = null;
        RestServiseRequest request = getFindAllRequest();
        String uri = request.getAddress() + "?personalId={id}&first=true";
        Object[] uriVariables = {personalId};
        HttpMethod method = request.getMethod();
        RestTemplate restTemplate = getRestTemplate();

        ResponseEntity responseEntity = restTemplate.exchange(uri, method, null, getEntityClass(), uriVariables);

        if (responseEntity.getBody() != null) {
            result = (T) responseEntity.getBody();
        }

        return result;
    }

    @Override
    public List<T> findByPersonalId(String personalId) {
        List<T> result = new LinkedList<>();
        RestServiseRequest request = getFindAllRequest();
        String uri = request.getAddress() + "?personalId={id}";
        Object[] uriVariables = {personalId};
        HttpMethod method = request.getMethod();

        RestTemplate restTemplate = getRestTemplate();
        ParameterizedTypeReference typeReference = getListTypeReference();
        ResponseEntity responseEntity = restTemplate.exchange(uri, method, null, typeReference, uriVariables);

        if (responseEntity.getBody() != null) {
            return (List<T>) responseEntity.getBody();
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
    @SuppressWarnings("unchecked")
    private Class<T> getGeneticType(String variableName) {
        Map<TypeVariable, Type> params = GenericTypeResolver.getTypeVariableMap(getClass());
        for (Map.Entry<TypeVariable, Type> entry : params.entrySet()) {
            if (entry.getKey().getName().equals(variableName))
                return (Class<T>) entry.getValue();
        }

        return null;
    }

    protected ParameterizedTypeReference<List<T>> getListTypeReference() {
        Class entityClass = getEntityClass();
        ParameterizedTypeReference<List<T>> typeReference = new ParameterizedTypeReference<List<T>>() {
            @Override
            public Type getType() {
                return ParameterizedTypeImpl.make(List.class, new Class[]{entityClass}, null);
            }
        };

        return typeReference;
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClass() {
        if (entityClass != null) {
            return entityClass;
        }

        Type[] genericArguments = getGenericParameterTypes();
        return (Class<T>) genericArguments[0];
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
    public static void main(String[] args) {
        Map<String, Integer> params = new HashMap<>();
        RestServiseRequest request = new RestServiseRequest("http://www.i.ua");

        params.put("id", 9);
        params.put("mla", 89);
        System.out.println(buildUrlString(request, params));
        params = null;
        System.out.println(buildUrlString(request, params));
        params = new HashMap<>();
        System.out.println(buildUrlString(request, params));
    }

    private static String buildUrlString(RestServiseRequest request, Map<String, ?> params) {
        String paramString = "";
        if (params != null) {
            List<BasicNameValuePair> nameValuePairs = params.entrySet().stream()
                    .map(entry -> new BasicNameValuePair(entry.getKey(), Objects.toString(entry.getValue())))
                    .collect(Collectors.toList());

            paramString = URLEncodedUtils.format(nameValuePairs, Charset.forName("UTF-8"));
        }

        return request.getAddress() + (StringUtils.isEmpty(paramString) ? paramString : "?" + paramString);
    }
}
