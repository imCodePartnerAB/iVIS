package com.imcode.utils;

import com.imcode.entities.EntityRestProviderInformation;
import com.imcode.entities.MethodRestProviderForEntity;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ruslan on 02.08.16.
 */
public class ControllerReflectionUtil {

    private static final String API_PATH = "/api/v1/{format}";
    public static final String ENTITY_PACKAGE = "com.imcode.entities";
    public static final String REST_CONTROLLERS_PACKAGE = "com.imcode.controllers.restful";

    private Class<?> controllerClass;
    private String controllerPath;
    private String entityName;

    private Method method;

    public ControllerReflectionUtil(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
        genAndSetControllerPathAndEntityName();
    }

    public MethodRestProviderForEntity buildPersistenceMethodOf(Method method, EntityRestProviderInformation savedInfo) {
        MethodRestProviderForEntity methodForPersist = new MethodRestProviderForEntity();
        methodForPersist.setEntityRestProviderInformation(savedInfo);
        methodForPersist.setName(getNameOf(method));
        methodForPersist.setUrl(controllerPath + getUrlOf(method));
        methodForPersist.setRequestMethod(getRequestMethodOf(method));
        methodForPersist.setInParameters(getInParametersOf(method));
        methodForPersist.setOutParameter(getOutParameterOf(method));
        return methodForPersist;
    }

    public Set<Method> getMethodsWithRequestMappingAnnotation() {
        Set<Method> declaredMethods = getAllMethodFromContrllerAndSuperClass();
        Set<Method> requestMapping = declaredMethods.stream()
                .filter(method -> AnnotationUtils.findAnnotation(method, RequestMapping.class) != null)
                .filter(distinctByKey(Method::getName))
                .collect(Collectors.toSet());
        return requestMapping;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public String getEntityClass() {
        return ENTITY_PACKAGE + "." + entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    private void genAndSetControllerPathAndEntityName() {
        String simpleName = controllerClass.getSimpleName();
        if (!simpleName.contains("RestControllerImpl")) {
            System.out.println();
        }
        entityName = simpleName.substring(0, simpleName.indexOf("RestControllerImpl"));

        controllerPath = API_PATH + "/" + entityNameToLowerCaseInPluralForm(entityName);
    }

    private Set<Method> getAllMethodFromContrllerAndSuperClass() {
        Set<Method> declaredMethods = new LinkedHashSet<>();
        Collections.addAll(declaredMethods, controllerClass.getMethods());
        return declaredMethods;
    }

    public static Set<Class<? extends Object>> getAllClassesFromPackage(String packageName) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        Set<BeanDefinition> classes = provider.findCandidateComponents(packageName);
        Set<Class<?>> allClasses = classes.stream()
                .map(bean -> {
                    try {
                        return Class.forName(bean.getBeanClassName());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toSet());
        return allClasses;
    }

    private String getNameOf(Method method) {
        return method.getName();
    }

    private String getUrlOf(Method method) {
        Set<String> values = new HashSet<>();
        Collections.addAll(values, AnnotationUtils.findAnnotation(method, RequestMapping.class).value());
        Optional<String> url = values.stream()
                .filter(value -> value.contains("/"))
                .findFirst();
        return url.isPresent() ? url.get() : "";
    }

    private RequestMethod getRequestMethodOf(Method method) {
        return AnnotationUtils.findAnnotation(method, RequestMapping.class).method()[0];
    }

    private String getOutParameterOf(Method method) {
        return genReturnTypeByMethodName(method.getName());
    }

    private Map<String, String> getInParametersOf(Method method) {
        String[] allParameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
        return IntStream
                .range(0, method.getParameterCount() - 1)
                .mapToObj(i -> new MethodParameter(method, i))
                .filter(methodParameter -> methodParameter.hasParameterAnnotation(PathVariable.class)
                        || methodParameter.hasParameterAnnotation(RequestParam.class)
                        || methodParameter.hasParameterAnnotation(RequestBody.class))
                .collect(Collectors.toMap(methodParameter -> resolveMethodParameterName(methodParameter, allParameterNames),
                        methodParameter -> resolveMethodParameterType(methodParameter)));

    }

    private String resolveMethodParameterName(MethodParameter methodParameter, String[] allParameterNames) {

        if (methodParameter.hasParameterAnnotation(PathVariable.class)) {
            String value = methodParameter.getParameterAnnotation(PathVariable.class).value();
            return value.isEmpty() ? allParameterNames[methodParameter.getParameterIndex()] : value;
        } else if (methodParameter.hasParameterAnnotation(RequestParam.class)) {
            String value = methodParameter.getParameterAnnotation(RequestParam.class).value();
            return value.isEmpty() ? allParameterNames[methodParameter.getParameterIndex()] : value;
        }

        if (resolveMethodParameterType(methodParameter).equals(entityName)) {
            return StringUtils.uncapitalize(entityName);
        }

        return inPluralForm(StringUtils.uncapitalize(entityName));

    }

    private String resolveMethodParameterType(MethodParameter methodParameter) {
        String methodTypeSimpleName = methodParameter.getParameterType().getSimpleName();
        switch (methodTypeSimpleName) {

            case "Serializable":
                return "Long";

//            case "Iterable":
//                return "List<" + entityName + ">";

            case "Object":
                return entityName;

            case "JpaEntity":
                return entityName;

            default:
                return methodTypeSimpleName;

        }
    }

    private String entityNameToLowerCaseInPluralForm(String entityName) {
        return inPluralForm(entityName.toLowerCase());
    }

    private String inPluralForm(String word) {
        StringBuilder modifiedEntityName = new StringBuilder(word);
        char lastLater = modifiedEntityName.charAt(modifiedEntityName.length() - 1);
        switch (lastLater) {
            case 'y': {
                modifiedEntityName.deleteCharAt(modifiedEntityName.length() - 1).append("ies");
                break;
            }

            case 's': {
                modifiedEntityName.append("es");
                break;
            }

            default:
                modifiedEntityName.append('s');
        }
        return modifiedEntityName.toString();
    }


    private String genReturnTypeByMethodName(String methodName) {
        boolean isMatch = methodName.matches("(?i)(.*)" + entityNameToLowerCaseInPluralForm(entityName)
                + "(.*)" + "|((?i).*)all(.*)");
        return isMatch ? "List<" + entityName + ">" : entityName;
    }

    private <T> Predicate<T> distinctByKey(Function<? super T,Object> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}


