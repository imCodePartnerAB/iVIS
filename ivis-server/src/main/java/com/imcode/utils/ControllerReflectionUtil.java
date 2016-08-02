package com.imcode.utils;

import com.imcode.entities.MethodRestProviderForEntity;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by ruslan on 02.08.16.
 */
public class ControllerReflectionUtil {

    private static final String API_PATH = "/api/v1/{format}";
    private static final String ENTITY_PACKAGE = "com.imcode.entities";
    private static final String REST_CONTROLLER_PACKAGE = "com.imcode.controllers.restful";

    private Class<?> controllerClass;
    private String controllerPath;
    private String entityName;

    private Method method;

    public ControllerReflectionUtil(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
        genAndSetControllerPathAndEntityName();
    }

    public Set<Method> getMethodsWithRequestMappingAnnotation() {
        Set<Method> declaredMethods = getAllMethodFromContrllerAndSuperClass();
        Set<Method> requestMapping = declaredMethods.stream()
                .filter(method -> {
                    List<Annotation> annotations = Arrays.asList(method.getDeclaredAnnotations());
                    return annotations.stream()
                            .anyMatch(annotation -> annotation.annotationType().getSimpleName().equals("RequestMapping"));
                })
                .collect(Collectors.toSet());
        return requestMapping;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public String getEntityClass() {
        return ENTITY_PACKAGE + "." + entityName;
    }

    private void genAndSetControllerPathAndEntityName() {
        String simpleName = controllerClass.getSimpleName();
        entityName = simpleName.substring(0, simpleName.indexOf("RestControllerImpl"));
        StringBuilder entityPath = new StringBuilder(entityName.toLowerCase());
        char lastLater = entityPath.charAt(entityPath.length() - 1);
        switch (lastLater) {
            case 'y': {
                entityPath.deleteCharAt(entityPath.length() - 1).append("ies");
                break;
            }

            case 's': {
                entityPath.append("es");
                break;
            }

            default:
                entityPath.append('s');
        }
        controllerPath = API_PATH + "/" + entityPath;
    }

    private Set<Method> getAllMethodFromContrllerAndSuperClass() {
        Set<Method> declaredMethods = new LinkedHashSet<>();
        Collections.addAll(declaredMethods, controllerClass.getGenericSuperclass().getClass().getDeclaredMethods());
        Collections.addAll(declaredMethods, controllerClass.getDeclaredMethods());
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

    public MethodRestProviderForEntity buildPersistenceMethodOf(Method method) {
        MethodRestProviderForEntity methodForPersist = new MethodRestProviderForEntity();
        methodForPersist.setName(getNameOf(method));
        methodForPersist.setUrl(controllerPath + getUrlOf(method));
        methodForPersist.setRequestMethod(getRequestMethodOf(method));
        methodForPersist.setInParameters(getInParametersOf(method));
        methodForPersist.setOutParameter(getOutParameterOf(method));
        return methodForPersist;
    }

    private String getNameOf(Method method) {
        return method.getName();
    }

    private String getUrlOf(Method method) {
        Set<String> values = new HashSet<>();
        Collections.addAll(values, method.getAnnotation(RequestMapping.class).value());
        return values.stream()
                .filter(value -> value.indexOf("/") != -1)
                .findFirst()
                .get();
    }

    private RequestMethod getRequestMethodOf(Method method) {
        return method.getAnnotation(RequestMapping.class).method()[0];
    }

    private String getOutParameterOf(Method method) {
        return method.getReturnType().getSimpleName();
    }

    private Map<String, String> getInParametersOf(Method method) {
        Parameter[] parameters = method.getParameters();
        Set<Parameter> parametersSet = new LinkedHashSet<>();
        Collections.addAll(parametersSet, parameters);
        String[] allParameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
        return parametersSet.stream()
                .filter(parameter -> parameter.getAnnotation(PathVariable.class) != null
                        || parameter.getAnnotation(RequestParam.class) != null)
                .collect(Collectors.toMap(parameter -> allParameterNames[getNumberFrom(parameter.getName())], parameter -> parameter.getType().getSimpleName()));
    }

    private int getNumberFrom(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        return Integer.parseInt(matcher.group());
    }




}
