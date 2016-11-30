package imcode.services.restful;

import com.imcode.entities.Application;
import com.imcode.entities.School;
import com.imcode.entities.SchoolClass;
import com.imcode.entities.SchoolTransport;
import com.imcode.entities.embed.SchoolTransportSchema;
import com.imcode.entities.enums.ServiceTypeEnum;
import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.services.*;
import imcode.services.GenericServiceProxy;
import imcode.services.IvisServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by vitaly on 10.06.15.
 */
public class ProxyIvisServiceFactory implements IvisServiceFactory {

    private static final Logger logger = LoggerFactory.getLogger(ProxyIvisServiceFactory.class);
    public static final String DEFAULT_ENTITY_PACKEDGE = "com.imcode.entities";
    public static final String DEFAULT_SERVICE_PACKEDGE = "imcode.services.restful";
    public static final Class<? extends GenericService> DEFAULT_SERVICE_IMPLEMENTATION = OAuth2Service.class;

    public Class<? extends GenericService> abstractOAuth2ServiceClass = DEFAULT_SERVICE_IMPLEMENTATION;
    private final String apiUrl;
    private final OAuth2ClientContext clientContext;
    private final OAuth2ProtectedResourceDetails client;
    private Set<Class<? extends AbstractIdEntity>> entityClassSet = new HashSet<>();
    private Set<AbstractOAuth2Service> serviceSet = new HashSet<>();
    private Map<Class<? extends GenericService>, ServiceInfo> serviceMap = new HashMap<>();
    private String entityPackage;
    private String servicePackage;

    private static class OAuth2Service extends AbstractOAuth2Service {
        public OAuth2Service(IvisServiceFactory factory, String mainServiceAddres, Class entityClass, Class entityIdClass) {
            super(factory, mainServiceAddres, entityClass, entityIdClass);
        }
    }

    public ProxyIvisServiceFactory(String apiUrl,
                                   OAuth2ClientContext clientContext,
                                   OAuth2ProtectedResourceDetails client) {
        this(apiUrl, clientContext, client, DEFAULT_ENTITY_PACKEDGE);
    }



    public ProxyIvisServiceFactory(String apiUrl,
                                   OAuth2ClientContext clientContext,
                                   OAuth2ProtectedResourceDetails client,
                                   String entityPackedge) {

        this(apiUrl, clientContext, client, entityPackedge, DEFAULT_SERVICE_PACKEDGE);
    }

    public ProxyIvisServiceFactory(String apiUrl,
                                   OAuth2ClientContext clientContext,
                                   OAuth2ProtectedResourceDetails client,
                                   List<Class<? extends AbstractIdEntity>> entityClassSet) {

        this(apiUrl, clientContext, client, entityClassSet, Collections.emptyList());
    }

    public ProxyIvisServiceFactory(String apiUrl,
                                   OAuth2ClientContext clientContext,
                                   OAuth2ProtectedResourceDetails client,
                                   List<Class<? extends AbstractIdEntity>> entityClassSet,
                                   List<AbstractOAuth2Service> serviceSet) {
        this.apiUrl = apiUrl;
        this.clientContext = clientContext;
        this.client = client;
        this.entityClassSet = new HashSet<>(entityClassSet);
        this.serviceSet = new HashSet<>(serviceSet);
//        fillServiceMap();
    }

    public ProxyIvisServiceFactory(String apiUrl,
                                   OAuth2ClientContext clientContext,
                                   OAuth2ProtectedResourceDetails client,
                                   String entityPackage,
                                   String servicePackage) {
        this(apiUrl, clientContext, client, Collections.emptyList(), Collections.emptyList());
        this.entityPackage = entityPackage;
        this.servicePackage = servicePackage;
    }


    @PostConstruct
    public void initialize() {
        if ((entityClassSet == null || entityClassSet.isEmpty()) && StringUtils.isNoneEmpty(entityPackage)) {
            entityClassSet = new HashSet<>(loadEntities(entityPackage));
        }

        if ((serviceSet == null || serviceSet.isEmpty()) && StringUtils.isNoneEmpty(servicePackage)) {
            serviceSet = new HashSet<>(loadServices(servicePackage));
        }

        fillServiceMap();
    }

    public Set<Class<? extends AbstractIdEntity>> getEntityClassSet() {
        return entityClassSet;
    }

    public void setEntityClassSet(Set<Class<? extends AbstractIdEntity>> entityClassSet) {
        this.entityClassSet = entityClassSet;
    }

    protected void fillServiceMap() {
        serviceMap = new HashMap<>();

        for (Class entityClass : entityClassSet) {
            try {
                GenericService implementation = getServiceImplementation(entityClass);
                Class<? extends GenericService> serviceInterface = getServiceInterface(entityClass);

                serviceMap.put(serviceInterface, new ServiceInfo(entityClass, getEntityIdClass(entityClass), implementation));
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
    }

    protected Class<GenericService> getServiceInterface(Class entityClass) {
        Class serviceClass = null;
        try {
            serviceClass = Class.forName("com.imcode.services." + entityClass.getSimpleName() + "Service");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Service interface for entity " + entityClass + " not found");
        }
        return serviceClass;
    }

    protected <T extends AbstractIdEntity<ID>, ID extends Serializable> GenericService<T, ID> getServiceImplementation(Class<T> entityClass) {
        for (GenericService<T, ID> service : serviceSet) {
            Class<? extends GenericService> serviceClass = service.getClass();
            Class serviceEntityClass = getClassOfTypeArgument(serviceClass, 0);

            if (serviceEntityClass.equals(entityClass)) {
                logger.info("find favorite implementation " + serviceClass + " for entity " + entityClass);
                return service;
            }
        }

        Class<? extends Serializable> entityIdClass = getEntityIdClass(entityClass);

        return getAbstractOAuth2ServiceImplementation(entityClass, entityIdClass);
    }

    private static ClassPathScanningCandidateComponentProvider getEntityScaner() {
        ClassPathScanningCandidateComponentProvider componentScaner = new ClassPathScanningCandidateComponentProvider(false);
//        componentScaner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        componentScaner.addIncludeFilter(new AssignableTypeFilter(AbstractIdEntity.class));

        return componentScaner;
    }

    private static ClassPathScanningCandidateComponentProvider getServiceScaner() {
        ClassPathScanningCandidateComponentProvider componentScaner = new ClassPathScanningCandidateComponentProvider(false);
        componentScaner.addIncludeFilter(new AssignableTypeFilter(AbstractOAuth2Service.class));

        return componentScaner;
    }

    public  List<Class<? extends AbstractIdEntity>> loadEntities(String packedge) {
        List<Class<? extends AbstractIdEntity>> entityClassList = new ArrayList<>();

        if (StringUtils.isNoneEmpty(packedge)) {
            ClassPathScanningCandidateComponentProvider entityScaner = getEntityScaner();
            Set<BeanDefinition> entitySet = entityScaner.findCandidateComponents(packedge);

            for (BeanDefinition beanDefinition : entitySet) {
                String className = "";

                try {
                    className = beanDefinition.getBeanClassName();
                    entityClassList.add((Class<? extends AbstractIdEntity>) Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Unable to load class \"" + className + "\"", e);
                }
            }

        }
        return entityClassList;
    }

    public  List<AbstractOAuth2Service> loadServices(String packedge) {
        List<AbstractOAuth2Service> serviceList = new ArrayList<>();

        if (StringUtils.isNoneEmpty(packedge)) {
            ClassPathScanningCandidateComponentProvider serviceScaner = getServiceScaner();
            Set<BeanDefinition> serviceSet = serviceScaner.findCandidateComponents(packedge);

            for (BeanDefinition beanDefinition : serviceSet) {
                String className = "";

                try {
                    className = beanDefinition.getBeanClassName();
                    Class<GenericService> serviceClass = (Class<GenericService>) Class.forName(className);
                    Class entityClass = getClassOfTypeArgument(serviceClass, 0);
                    Constructor constructor = serviceClass.getConstructor(IvisServiceFactory.class, String.class);
                    AbstractOAuth2Service instance = (AbstractOAuth2Service) constructor.newInstance(this, getDefaultServiceAlias(entityClass));
                    serviceList.add(instance);
                } catch (ClassNotFoundException e) {
                    logger.error("Unable to load class \"" + className + "\"");
                } catch (InstantiationException e) {
                    logger.error("Unable to create instance of class \"" + className + "\"");
                } catch (NoSuchMethodException e) {
                    logger.error("Constructor not fuond \"" + className + "\"");
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }

        return serviceList;
    }

    protected void checkEntityClassList(List<Class> entityClassList) {
        for (Class entityClass : entityClassList) {
            Assert.notNull(entityClass.getAnnotation(Entity.class), entityClass.getName() + " is not an entity class");
        }
    }

//    protected void checkServiceClassList(List<GenericService> serviceClassList) {
//        for (GenericService serviceClass : serviceClassList) {
//            Assert.isAssignable(GenericService.class, serviceClass, serviceClass.getName() + " is not an service class");
//        }
//    }

//    @Override
//    public <S extends GenericService<T, ID>, T, ID> S getService(Class<S> serviceClass) {
//        Type[] genericArguments = getGenericParameterTypes(serviceClass);
//        Class entityClass = (Class) genericArguments[0];
//        Class idClass = (Class) genericArguments[1];
//        AbstractOAuth2Service basicServiceImplementation = getAbstractOAuth2ServiceImplementation(entityClass, idClass);
//
//        S service = (S) GenericServiceProxy.newInstance(basicServiceImplementation, serviceClass);
//
//        return service;
//    }

    @Override
    public <S extends GenericService<T, ID>, T, ID> S getService(Class<S> serviceClass) {
        ServiceInfo serviceInfo = serviceMap.get(serviceClass);
        if (serviceInfo != null) {
            return (S) serviceInfo.getService();
        }

        throw new RuntimeException("No such service!");
    }

    @Override
    public <S extends GenericService<T, ID>, T, ID> S getServiceFor(Class<T> entityClass) {
        Class serviceInterface = getServiceInterface(entityClass);
        return (S) getService(serviceInterface);
    }

    private <T, ID> GenericService<T, ID> getAbstractOAuth2ServiceImplementation(final Class entityClass, final Class idClass) {
        try {
            Constructor constructor = abstractOAuth2ServiceClass.getConstructor(IvisServiceFactory.class, String.class, Class.class, Class.class);
            OAuth2Service instance = (OAuth2Service) constructor.newInstance(this, getDefaultServiceAlias(entityClass), entityClass, idClass);
            GenericService<T, ID> proxy = GenericServiceProxy.newInstance(instance, getServiceInterface(entityClass));
            return proxy;
        } catch (Exception e) {
            throw new RuntimeException("Unable to create instanse of abstract class");
        }
//        return new AbstractOAuth2Service<T, ID>(this, getDefaultServiceAlias(entityClass)){
//            @Override
//            protected Class getEntityClass() {
//                return entityClass;
//            }
//
//            @Override
//            protected Class getIdClass() {
//                return idClass;
//            }
//        };
    }

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

    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public OAuth2ClientContext getClientContext() {
        return clientContext;
    }

    @Override
    public boolean hasServiceFor(Class entityClass) {
        for (ServiceInfo serviceInfo : serviceMap.values()) {
            if (entityClass.equals(serviceInfo.entityClass)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasService(Class<? extends GenericService> service) {
        return serviceMap.get(service) != null;
    }

    @Override
    public ServiceInfo getServiceInfo(Class<? extends GenericService> serviceClass) {
        return serviceMap.get(serviceClass);
    }

    @Override
    public ServiceInfo getServiceInfoFor(Class entity) {
        for (ServiceInfo serviceInfo : serviceMap.values()) {
            if (entity.equals(serviceInfo.entityClass)) {
                return serviceInfo;
            }
        }

        return null;
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

    private static Class getClassOfTypeArgument(Class aClass, int index) {
        Type[] genericArguments = getGenericParameterTypes(aClass);
        Type argument = genericArguments[index];

        if (argument instanceof Class<?>) {
            return (Class) argument;
        } else {
            throw new IllegalStateException("Type argument(" + index + ") is not a Class instance");
        }
    }

    public static <ID extends Serializable> Class<ID> getEntityIdClass(Class<? extends AbstractIdEntity> entityClass) {
        Type parameter = ((ParameterizedType) entityClass.getGenericSuperclass()).getActualTypeArguments()[0];
        return (Class<ID>) parameter;
    }

    public List<Class<? extends AbstractIdEntity>> getEntityClassList() {
        return new ArrayList<>(entityClassSet);
    }

    public void setEntityClassList(Set<Class<? extends AbstractIdEntity>> entityClassList) {
        this.entityClassSet = entityClassList;
    }

    @Deprecated
    public Set<AbstractOAuth2Service> getServiceSet() {
        return serviceSet;
    }

    @Deprecated
    public void setServiceSet(Set<AbstractOAuth2Service> serviceSet) {
        this.serviceSet = serviceSet;
    }

    void addService(AbstractOAuth2Service service) {
        serviceSet.add(service);
    }

    void addServices(Collection<AbstractOAuth2Service> services) {
        serviceSet.addAll(services);
    }

//    public String getEntityPackage() {
//        return entityPackage;
//    }
//
//    public void setEntityPackage(String entityPackage) {
//        this.entityPackage = entityPackage;
//    }
//
//    public String getServicePackage() {
//        return servicePackage;
//    }
//
//    public void setServicePackage(String servicePackage) {
//        this.servicePackage = servicePackage;
//    }

    public static void main(String[] args) {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setId("ivis");
        resource.setClientId("ff11397c-3e3b-4398-80a9-feba203f1928"); //admin
//        resource.setClientId("08d32c33-91cf-4452-8be8-4d120fbc504e"); //ivis
//        resource.setGrantType("authorization_code");
        resource.setGrantType("password");
        resource.setClientSecret("secret");
        resource.setAccessTokenUri("http://localhost:8080/ivis/oauth/token");
//        resource.setAccessTokenUri("http://ivis.dev.imcode.com/oauth/token");
        resource.setScope(Arrays.asList("read"));
        resource.setUsername("admin");
        resource.setPassword("pass");
//        resource.setUsername("ivis");
//        resource.setPassword("111");
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
        DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken("acf95060-9303-4fbd-992a-c54157f16eab");
        accessToken.setTokenType("bearer");
        accessToken.setScope(scopes);
        accessToken.setRefreshToken(new DefaultExpiringOAuth2RefreshToken("fb99a06d-027d-4550-b969-f9bda5103e6d", ecxpirationDate));
        accessToken.setExpiration(ecxpirationDate);

        //Client context
        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        clientContext.setAccessToken(accessToken);

        try {
            IvisServiceFactory serviceFactory = new ProxyIvisServiceFactoryBuilder()
//                    .setApiUrl("http://ivis.dev.imcode.com/api/v1/json")
                    .setApiUrl("http://localhost:8080/ivis/api/v1/json")
                    .setClientContext(clientContext)
                    .setClient(resource)
                    .build();

//            ProxyIvisServiceFactory serviceFactory =
//                    new ProxyIvisServiceFactory("http://localhost:8080/ivis/api/v1/json",
//                            clientContext,
//                            resource,
////                    Arrays.asList(SchoolClass.class));
//                            "com.imcode.entities", "imcode.services.restful");
//            serviceFactory.initialize();
//            serviceFactory.check EntityClassList(serviceFactory.entityClassSet);
            GenericService service = serviceFactory.getService(ApplicationService.class);
////            UserService service = serviceFactory.getService(UserService.class);
////            User entityList = service.getCurrentUser();
//////            SchoolClass schoolClass = (SchoolClass) service1.find(1L);
////            Pupil entity = (Pupil) service.find(3);
////            entity.setClassPlacementFrom(LocalDate.now());
//
//            Random r = new Random();
            Application entity = (Application) service.find(5L);
//            String entityString = ToStringBuilder.reflectionToString(entity, ToStringStyle.JSON_STYLE);
////            entity = new Application();
//            entity.setDecision(new Decision(Decision.Status.values()[r.nextInt(3)]));
//            service.save(entity);
//            SchoolService service = serviceFactory.getService(SchoolService.class);
//            School entity = service.find(10l);
//            service.save(entity);

//            System.out.println(entityString);

//            LogEventService service = serviceFactory.getService(LogEventService.class);
//            final Application entity = new Application();
//            entity.setId(14L);
//            List<LogEvent> entities  = service.findByEntity(entity);
//            System.out.println(entities);

//            Object entity = service.find(0);
            System.out.println(entity);

//            System.out.println(entityList);

//            ApplicationContext ctx = StaticUtils.getApplicationContext();
//            PupilService service = ctx.getBean(PupilService.class);
//            Pupil entity = service.find(3L);
//            entity.setClassPlacementFrom(LocalDate.now());
//            service.save(entity);
//            SchoolService schoolService = serviceFactory.getService(SchoolService.class);
//            SchoolClassService schoolClassService = serviceFactory.getService(SchoolClassService.class);
            SchoolTransportService schoolTransportService = serviceFactory.getService(SchoolTransportService.class);
//            schoolClassService.findAll();
//            School school = new School();
//            school.setName("School #1");
//            school.setSchoolId("15uy");
//            school.setServices(ServiceTypeEnum.AFTER_SCHOOL_CENTER, ServiceTypeEnum.ELEMENTARY_SCHOOL);
//            school = schoolService.save(school);
////            school.setSchoolClasses(new HashSet<>(Arrays.asList(schoolClass)));
//            SchoolClass schoolClass = new SchoolClass("School class #1", school, new Date(), new Date());
//            schoolClass = schoolClassService.save(schoolClass);
//            school = schoolService.find(school.getId());
//            List<SchoolTransport> entities = schoolTransportService.findAll();
//            List<SchoolTransport> outList = new ArrayList<>(5);
//            outList.add(new SchoolTransport("Bus #1"));
//            outList.add(new SchoolTransport("Bus #2"));
//            outList.add(new SchoolTransport("Bus #3"));
//            outList.add(new SchoolTransport("Bus #4"));
//            outList.add(new SchoolTransport("Bus #5"));
//            SchoolTransport st = schoolTransportService.save(outList.get(0));
//            Iterable<SchoolTransport> inList = schoolTransportService.save(outList);
//            System.out.println(inList);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}



