package imcode.services;

import com.imcode.services.*;
import imcode.services.restful.*;
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
public interface IvisServiceFactory {

    OAuth2ProtectedResourceDetails getClient();

    <S extends GenericService<T, ID>, T, ID> S getService(Class<S> serviceClass);

    <S extends GenericService<T, ID>, T, ID> S getServiceFor(Class<T> entityClass);

    OAuth2ClientContext getClientContext();

    boolean hasServiceFor(Class entity);

    boolean hasService(Class<? extends GenericService> service);

    ServiceInfo getServiceInfo(Class<? extends GenericService> service);

    ServiceInfo getServiceInfoFor(Class entity);

}
