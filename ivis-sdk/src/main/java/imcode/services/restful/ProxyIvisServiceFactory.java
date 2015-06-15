package imcode.services.restful;

import com.imcode.entities.AbstractIdEntity;
import com.imcode.entities.User;
import com.imcode.services.GenericService;
import imcode.services.GenericServiceProxy;
import imcode.services.IvisServiceFactory;
import imcode.services.restful.AbstractOAuth2Service;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * Created by vitaly on 10.06.15.
 */
public class ProxyIvisServiceFactory implements IvisServiceFactory {
    private final String apiUrl;
    private final OAuth2ClientContext clientContext;
    private final OAuth2ProtectedResourceDetails client;

    public ProxyIvisServiceFactory(String apiUrl, OAuth2ClientContext clientContext, OAuth2ProtectedResourceDetails client) {
        this.apiUrl = apiUrl;
        this.clientContext = clientContext;
        this.client = client;
    }

    @Override
    public <S extends GenericService<T, ID>, T, ID> S getService(Class<S> serviceClass) {
        Type[] genericArguments = getGenericParameterTypes(serviceClass);
        Class entityClass = (Class) genericArguments[0];
        Class idClass = (Class) genericArguments[1];
        AbstractOAuth2Service basicServiceImplementation = getAbstractOAuth2ServiceImplementation(entityClass, idClass);

        S service = (S) GenericServiceProxy.newInstance(basicServiceImplementation, serviceClass);

        return service;
    }

    private <T, ID> AbstractOAuth2Service<T, ID> getAbstractOAuth2ServiceImplementation(final Class entityClass, final Class idClass) {
        return new AbstractOAuth2Service<T, ID>(this, getDefaultServiceAlias(entityClass)){
            @Override
            protected Class getEntityClass() {
                return entityClass;
            }

            @Override
            protected Class getIdClass() {
                return idClass;
            }
        };
    }

    private static Type[] getGenericParameterTypes(Class<?> clazz) {
        Type[] types = clazz.getGenericInterfaces();

        for (Type type :types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                return parameterizedType.getActualTypeArguments();
            }
        }

        return null;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public OAuth2ClientContext getClientContext() {
        return clientContext;
    }


    @Override
    public OAuth2ProtectedResourceDetails getClient() {
        return client;
    }

    private String getDefaultServiceAlias(Class<? extends AbstractIdEntity> serviseClass) {
        StringBuilder sb = new StringBuilder(apiUrl)
                .append('/')
                .append(serviseClass.getSimpleName().toLowerCase());

        char lastLater = sb.charAt(sb.length() - 1);

        switch (lastLater) {
            case 'y': {
                sb.deleteCharAt(sb.length() - 1).append("ies");
                break;
            }

            case 's': {
                sb.append("es");
                break;
            }

            default:
                sb.append('s');
        }

        return sb.toString();
    }



    public static void main(String[] args) {
//        Class<User> serviseClass = User.class;
//        String result = getDefaultServiceAlias(serviseClass);
//        System.out.println(result);
    }
}
