package imcode.services.restful;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.search.SearchCriteries;
import com.imcode.services.GenericService;
import com.imcode.services.NamedService;
import com.imcode.services.PersonalizedService;
import imcode.services.IvisServiceFactory;
import imcode.services.exceptionhandling.IvisResponseErrorHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vitaly on 28.05.15.
 */
public abstract class AbstractOAuth2Service<T, ID> implements GenericService<T, ID>, NamedService<T>, PersonalizedService<T> {
    private static final HashMap<String, Object> EMPTY_PARAMS = new HashMap<>();
    private String mainServiceAddres;

    private RestServiceRequest findAllRequest;

    private RestServiceRequest findRequest;

    private RestServiceRequest createRequest;

    private RestServiceRequest updateRequest;

    private RestServiceRequest existsRequest;

    private RestServiceRequest deleteRequest;

    private IvisServiceFactory factory;

    private Class<T> entityClass;

    private Class<ID> entityIdClass;


    public static class RestServiceRequestBuilder {
        private static final HttpMethod DEFAULT_METHOD = HttpMethod.GET;
        private StringBuilder address;
        private HttpMethod method = DEFAULT_METHOD;

        public RestServiceRequestBuilder(RestServiceRequest prototype) {
            method = prototype.method;
            address = new StringBuilder(prototype.address);

        }

        public RestServiceRequestBuilder setAddress(String address) {
            this.address = new StringBuilder(address);
            return this;
        }

        public RestServiceRequestBuilder appendAddress(String address) {
            this.address.append(address);
            return this;
        }

        public RestServiceRequestBuilder setMethod(HttpMethod method) {
            this.method = method;
            return this;
        }

        public RestServiceRequest build() {
            return new RestServiceRequest(address.toString(), HttpMethod.GET);
        }
    }

    public static class RestServiceRequest {
        private final String address;
        private final HttpMethod method;

        public RestServiceRequest(String address, HttpMethod method) {
            this.address = address;
            this.method = method;
        }

        public String getAddress() {
            return address;
        }

//        public void setAddress(String address) {
//            this.address = address;
//        }

        public HttpMethod getMethod() {
            return method;
        }

//        public void setMethod(HttpMethod method) {
//            this.method = method;
//        }
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
        OAuth2ProtectedResourceDetails client = getClient();
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(client, getClientContext());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        MappingJackson2HttpMessageConverter messageConverter = new  MappingJackson2HttpMessageConverter(objectMapper);
        List<HttpMessageConverter<?>> converters = new LinkedList<HttpMessageConverter<?>>();
        converters.add(messageConverter);
        oAuth2RestTemplate.setMessageConverters(converters);
        oAuth2RestTemplate.setErrorHandler(new IvisResponseErrorHandler(client));
        return oAuth2RestTemplate;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public T save(T entity) {
        T result = null;
        AbstractIdEntity idEntity = (AbstractIdEntity) entity;
        RestTemplate restTemplate = getRestTemplate();
        RestServiceRequest request = null;
        Object[] uriVariables = null;

        if (idEntity.getId() == null) {
            request = getCreateRequest();
            String uri = request.getAddress();
            HttpMethod method = request.getMethod();
            result = restTemplate.postForObject(uri, entity, getEntityClass());
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
    public Iterable<T> save(Iterable<T> entities) {
        RestServiceRequest request = getCreateRequest();
        List<T> result = new LinkedList<>();
        String uri = request.getAddress() + "/saveall";
        HttpMethod method = request.getMethod();
        RestTemplate restTemplate = getRestTemplate();
        ParameterizedTypeReference<List<T>> typeReference = getListTypeReference();
        HttpEntity<Iterable<T>> httpEntity = null;
        try {
            httpEntity = new RequestEntity<>(entities, HttpMethod.POST, new URI(uri));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(uri, method, httpEntity,  typeReference);

        if (responseEntity.getBody() != null) {
            return responseEntity.getBody();
        }

        return result;
    }

    @Override
    public T find(ID id) {
        return obtainEntity(getFindRequest(), Collections.emptyMap(), id);
    }

    @Override
    public boolean exist(ID id) {
        boolean result = false;
        RestTemplate restTemplate = getRestTemplate();
        RestServiceRequest request = getExistsRequest();
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
        return obtainEntity(getFindAllRequest(), parameterMap("name,first", name, true));
    }

    protected T obtainEntity(RestServiceRequest request, Map<String, ?> params, Object... uriVariables) {
        T result = null;

        String uri = buildUrlString(request, params);
        HttpMethod method = request.getMethod();
        RestTemplate restTemplate = getRestTemplate();

        ResponseEntity<T> responseEntity = restTemplate.exchange(uri, method, null, getEntityClass(), uriVariables);

        if (responseEntity.getBody() != null) {
            result = responseEntity.getBody();
        }

        return result;
    }

    protected List<T> obtainEntityList(RestServiceRequest request, Map<String, ?> params) {
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
        return obtainEntityList(getFindAllRequest(), parameterMap("name", name));
    }


    @Override
    public T findFirstByPersonalId(String personalId) {
        return obtainEntity(getFindAllRequest(), parameterMap("personalId,first", personalId, true));
    }

    @Override
    public List<T> findByPersonalId(String personalId) {
        return obtainEntityList(getFindAllRequest(), parameterMap("personalId", personalId));
    }

    @Override
    public void delete(ID id) {
        RestTemplate restTemplate = getRestTemplate();
        RestServiceRequest request = getDeleteRequest();
        String uri = request.getAddress();
        HttpMethod method = request.getMethod();

        Object[] uriVariables = {id};

        restTemplate.exchange(uri, method, null, void.class, uriVariables);
    }

    @Override
    public void delete(Iterable<T> entities) {
        RestServiceRequest request = getCreateRequest();
        List<T> result = new LinkedList<>();
        String uri = request.getAddress() + "/deleteall";
        HttpMethod method = request.getMethod();
        RestTemplate restTemplate = getRestTemplate();
        HttpEntity<Iterable<T>> httpEntity = null;
        try {
            httpEntity = new RequestEntity<>(entities, HttpMethod.POST, new URI(uri));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        restTemplate.exchange(uri, method, httpEntity, void.class);
    }

    @Override
    public List<T> findAll() {
        return obtainEntityList(getFindAllRequest(), EMPTY_PARAMS);
    }

    @Override
    public List<T> search(List<SearchCriteries.SearchCriteriaResult> criteries) {
        RestServiceRequest request = getCreateRequest();
        List<T> result = new LinkedList<>();
        String uri = request.getAddress() + "/search";
        HttpMethod method = request.getMethod();
        RestTemplate restTemplate = getRestTemplate();
        ParameterizedTypeReference<List<T>> typeReference = getListTypeReference();
        HttpEntity<List<SearchCriteries.SearchCriteriaResult>> httpEntity = null;
        try {
            httpEntity = new RequestEntity<>(criteries, HttpMethod.POST, new URI(uri));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(uri, method, httpEntity,  typeReference);

        if (responseEntity.getBody() != null) {
            return responseEntity.getBody();
        }

        return result;
    }

    @Override
    public T searchOne(List<SearchCriteries.SearchCriteriaResult> criteries) {
        T result = null;
        RestTemplate restTemplate = getRestTemplate();
        RestServiceRequest request = null;
        Object[] uriVariables = null;
        request = getCreateRequest();
        String uri = request.getAddress() + "/search/first";
        HttpMethod method = request.getMethod();
        result = restTemplate.postForObject(uri, criteries, getEntityClass());
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

    public RestServiceRequest getFindAllRequest() {
        return findAllRequest;
    }

    public void setFindAllRequest(RestServiceRequest findAllRequest) {
        this.findAllRequest = findAllRequest;
    }

    public RestServiceRequest getFindRequest() {
        return findRequest;
    }

    public void setFindRequest(RestServiceRequest findRequest) {
        this.findRequest = findRequest;
    }

    public RestServiceRequest getCreateRequest() {
        return createRequest;
    }

    public void setCreateRequest(RestServiceRequest createRequest) {
        this.createRequest = createRequest;
    }

    public RestServiceRequest getUpdateRequest() {
        return updateRequest;
    }

    public void setUpdateRequest(RestServiceRequest updateRequest) {
        this.updateRequest = updateRequest;
    }

    public RestServiceRequest getExistsRequest() {
        return existsRequest;
    }

    public void setExistsRequest(RestServiceRequest existsRequest) {
        this.existsRequest = existsRequest;
    }

    public RestServiceRequest getDeleteRequest() {
        return deleteRequest;
    }

    public void setDeleteRequest(RestServiceRequest deleteRequest) {
        this.deleteRequest = deleteRequest;
    }

    public void fillServiseAdderess(String mainServiceAddres) {
        this.mainServiceAddres = mainServiceAddres;
        createRequest = new RestServiceRequest(mainServiceAddres, HttpMethod.POST);
        findAllRequest = new RestServiceRequest(mainServiceAddres, HttpMethod.GET);
        findRequest = new RestServiceRequest(mainServiceAddres + "/{id}", HttpMethod.GET);
        existsRequest = new RestServiceRequest(findRequest.address, HttpMethod.GET);
        updateRequest = new RestServiceRequest(findRequest.address, HttpMethod.PUT);
        deleteRequest = new RestServiceRequest(findRequest.address, HttpMethod.DELETE);

    }

    //    protected String getDefaultServiseLocation() {
//        this.
//    }
    public static void main(String[] args) {
        Map<String, Integer> params = new HashMap<>();
        RestServiceRequest request = new RestServiceRequest("http://www.i.ua", HttpMethod.GET);

        params.put("id", 9);
        params.put("mla", 89);
        System.out.println(buildUrlString(request, params));
        params = null;
        System.out.println(buildUrlString(request, params));
        params = new HashMap<>();
        System.out.println(buildUrlString(request, params));
    }

    private static String buildUrlString(RestServiceRequest request, Map<String, ?> params) {
        String paramString = "";
        if (params != null) {
            List<BasicNameValuePair> nameValuePairs = params.entrySet().stream()
                    .map(entry -> new BasicNameValuePair(entry.getKey(), Objects.toString(entry.getValue())))
                    .collect(Collectors.toList());

            paramString = URLEncodedUtils.format(nameValuePairs, Charset.forName("UTF-8"));
        }

        return request.getAddress() + (StringUtils.isEmpty(paramString) ? paramString : "?" + paramString);
    }

    private static Map<String, Object> parameterMap(String keyString, Object... values) {
        Map<String, Object> map = new HashMap<>();
        String[] keys = keyString.split("\\W+");
        for (int i = 0; i < keys.length; i++) {
            final Object o = (i < values.length) ? values[i] : null;
            map.put(keys[i], o);
        }
        return map;
    }

    public static class RequestObject<T> {
        private List<T> entities;

        public RequestObject() {

        }

        @SuppressWarnings("unchecked")
        public RequestObject(Iterable<T> entities) {
            List<T> list = new ArrayList<>();
            if (entities instanceof List) {
                list = (List<T>) entities;
            } else {
                entities.forEach(list::add);
            }
            this.entities = list;
        }

        public List<T> getEntities() {
            return entities;
        }

        public void setEntities(List<T> entities) {
            this.entities = entities;
        }
    }
}
