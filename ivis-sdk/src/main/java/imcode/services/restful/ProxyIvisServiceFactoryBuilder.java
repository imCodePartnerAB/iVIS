package imcode.services.restful;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;
import imcode.services.IvisServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by vitaly on 14.03.16.
 */
public class ProxyIvisServiceFactoryBuilder {
    private static final Logger logger = LoggerFactory.getLogger(ProxyIvisServiceFactoryBuilder.class);
//    private static final ClassPathScanningCandidateComponentProvider ENTITY_SCANNER;
//    private static final ClassPathScanningCandidateComponentProvider SERVICE_SCANNER;
    public static final String DEFAULT_ENTITY_PACKEDGE = "com.imcode.entities";
    public static final String DEFAULT_SERVICE_PACKEDGE = "imcode.services.restful";

//    static {
//        ENTITY_SCANNER = new ClassPathScanningCandidateComponentProvider(false);
//        ENTITY_SCANNER.addIncludeFilter(new AssignableTypeFilter(JpaEntity.class));
//
//        SERVICE_SCANNER = new ClassPathScanningCandidateComponentProvider(false);
//        SERVICE_SCANNER.addIncludeFilter(new AssignableTypeFilter(AbstractOAuth2Service.class));
//    }

    private String apiUrl;
    private OAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
    private OAuth2ProtectedResourceDetails client;
    private Set<Class<? extends AbstractIdEntity>> entityClassSet = new HashSet<>();
    private Set<AbstractOAuth2Service> serviceInstanceSet = new HashSet<>();
    private Set<Class<AbstractOAuth2Service>> serviceClassSet = new HashSet<>();
    //    private Map<Class<? extends GenericService>, ServiceInfo> serviceMap = new HashMap<>();
    private String entityPackage = DEFAULT_ENTITY_PACKEDGE;
    private String servicePackage = DEFAULT_SERVICE_PACKEDGE;


    public ProxyIvisServiceFactoryBuilder setApiUrl(String apiUrl) {
        Objects.requireNonNull(entityClassSet);
        this.apiUrl = apiUrl;
        return this;
    }

    public ProxyIvisServiceFactoryBuilder setClientContext(OAuth2ClientContext clientContext) {
        Objects.requireNonNull(entityClassSet);
        this.clientContext = clientContext;
        return this;
    }

    public ProxyIvisServiceFactoryBuilder setClient(OAuth2ProtectedResourceDetails client) {
        Objects.requireNonNull(entityClassSet);
        this.client = client;
        return this;
    }

    public ProxyIvisServiceFactoryBuilder setEntityClassSet(Set<Class<? extends AbstractIdEntity>> entityClassSet) {
        Objects.requireNonNull(entityClassSet);
        this.entityClassSet = entityClassSet;
        return this;
    }

    public ProxyIvisServiceFactoryBuilder setServiceClassSet(Set<Class<AbstractOAuth2Service>> serviceClassSet) {
        Objects.requireNonNull(entityClassSet);
        this.serviceClassSet = serviceClassSet;
        return this;
    }

//    public ProxyIvisServiceFactoryBuilder setServiceMap(Map<Class<? extends GenericService>, ServiceInfo> serviceMap) {
//        this.serviceMap = serviceMap;
//        return this;
//    }

    public ProxyIvisServiceFactoryBuilder setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
        return this;
    }

    public ProxyIvisServiceFactoryBuilder setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
        return this;
    }

    private void checkMandatoryFields() {
        if (apiUrl == null) {
            throw new IllegalStateException();
        }
        if (clientContext == null) {
            throw new IllegalStateException();
        }
        if (client == null) {
            throw new IllegalStateException();
        }
    }

//    private Set<Class<JpaEntity>> addEntities(String packadges) {

    @SuppressWarnings("unchecked")
    private <T> Set<Class<T>> getClasses(String packages, Class<T> baseClass) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(baseClass));

        Set<Class<T>> result = new HashSet<>();
        if (StringUtils.isNoneEmpty(packages)) {
            //split packages by split symbol except dot(.)
            String[] packageArray = packages.split("[^\\w.]+");
            for (String pack : packageArray) {
                Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(packages);

                for (BeanDefinition beanDefinition : beanDefinitions) {
                    String className = "";
                    try {
                        className = beanDefinition.getBeanClassName();
                        Class<T> clazz = (Class<T>) Class.forName(className);
                        result.add(clazz);
                    } catch (ClassNotFoundException e) {
                        logger.error("Unable to load class \"" + className + "\"");
                    }
                }
            }
        }

        return result;
    }

    //    private Set<AbstractOAuth2Service> addServiceInstances(ProxyIvisServiceFactory factory, String packages) {
    private void addServiceInstances(ProxyIvisServiceFactory factory, String packages) {
        if (StringUtils.isNoneEmpty(packages)) {
            Set<Class<AbstractOAuth2Service>> serviceClasses = getClasses(packages, AbstractOAuth2Service.class);

            for (Class<AbstractOAuth2Service> serviceClass : serviceClasses) {
                addServiceInstance(factory, serviceClass);
            }
        }
    }

    private void addServiceInstance(ProxyIvisServiceFactory factory, Class<AbstractOAuth2Service> serviceClass) {
        try {
            Class entityClass = getClassOfTypeArgument(serviceClass, 0);
            Constructor constructor = serviceClass.getConstructor(IvisServiceFactory.class, String.class);
            AbstractOAuth2Service instance = (AbstractOAuth2Service) constructor.newInstance(factory, getDefaultServiceAlias(entityClass));
            factory.addService(instance);
//                    serviceList.add(instance);
        } catch (InstantiationException e) {
            logger.error("Unable to create instance of class \"" + serviceClass + "\"");
//            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            logger.error("Constructor not fuond \"" + serviceClass + "\"");
//            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error(e.getMessage());
//            throw new RuntimeException(e);
        }
//        return null;
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

//    private static ClassPathScanningCandidateComponentProvider getServiceScaner() {


    private static Type[] getGenericParameterTypes(Class<?> clazz) {
        Type[] types = clazz.getGenericInterfaces();

        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                return parameterizedType.getActualTypeArguments();
            }
        }

        return ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
    }

    private static Class getClassOfTypeArgument(Class aClass, int index) {
        Type[] genericArguments = getGenericParameterTypes(aClass);
        Type argument = genericArguments[index];

        if (argument instanceof Class<?>) {
            return (Class) argument;
        } else {
            throw new IllegalStateException("Type argument(" + index + ") is not a Class instance");
        }
    }


//    @SuppressWarnings("unchecked")

    public Set<AbstractOAuth2Service> getServiceInstanceSet() {
        return serviceInstanceSet;
    }

    public ProxyIvisServiceFactoryBuilder setServiceInstanceSet(Set<AbstractOAuth2Service> serviceInstanceSet) {
        this.serviceInstanceSet = serviceInstanceSet;
        return this;
    }

//    private static ClassPathScanningCandidateComponentProvider getEntityScanner() {


    //    }


    public IvisServiceFactory build() {
        checkMandatoryFields();
        ProxyIvisServiceFactory factory = new ProxyIvisServiceFactory(apiUrl, clientContext, client);
//set entity classes
        if (StringUtils.isNotEmpty(entityPackage)) {
            Set<Class<AbstractIdEntity>> entityClasses = getClasses(entityPackage, AbstractIdEntity.class);
            entityClassSet.addAll(entityClasses);
            factory.setEntityClassSet(entityClassSet);
        }

//add service instances
        if (StringUtils.isNotEmpty(servicePackage)) {
            addServiceInstances(factory, servicePackage);
            factory.addServices(serviceInstanceSet);
        }

        for (Class<AbstractOAuth2Service> serviceClass : serviceClassSet) {
            addServiceInstance(factory, serviceClass);
        }


        factory.fillServiceMap();

        return factory;
    }

    public static void main(String[] args) {
        ProxyIvisServiceFactoryBuilder builder = new ProxyIvisServiceFactoryBuilder();
    }
}
