package com.imcode.controllers.restful;

import com.imcode.entities.Permission;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.services.PermissionService;
import com.imcode.utils.DocumentationUtil;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ruslan on 14.11.16.
 */
@RestController
public class PermissionRestController {

    private static final String API_PREFIX = "/api";
    private static final String REST_CONTROLLER_SUFIX = "RestControllerImpl";

    private final RequestMappingHandlerMapping handler;
    private final PermissionService permissionService;
    private final ServletContext servletContext;

    @Autowired
    public PermissionRestController(
            @Qualifier("org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#0")
                    RequestMappingHandlerMapping handler,
            PermissionService permissionService,
            ServletContext servletContext) {
        this.handler = handler;
        this.permissionService = permissionService;
        this.servletContext = servletContext;
    }

    @PostConstruct
    public void initPermissions() {
        permissionService.makeAllUnUpdated();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handler.getHandlerMethods();
        handlerMethods.forEach(this::process);
        List<Permission> unUpdated = permissionService.getUnUpdated();
        permissionService.deleteAssociation(unUpdated);
        permissionService.delete(unUpdated);
        new DocumentationUtil()
                .generate(permissionService, servletContext);
    }

    private void process(RequestMappingInfo info, HandlerMethod handlerMethod) {
        Permission permission = new Permission();
        setFrom(info, permission);
        setFrom(handlerMethod, permission);
        save(permission);
    }

    private void save(Permission permission) {
        try {
            permissionService.save(permission);
        } catch (DataAccessException ignored) {
            permissionService.setUpdated(permission.getHash());
        }
    }

    private void setFrom(RequestMappingInfo info, Permission permission) {
        permission.setUrl(API_PREFIX
                + info.getPatternsCondition().getPatterns().stream().collect(Collectors.joining(", ")));
        permission.setHttpMethod(info.getMethodsCondition().getMethods().stream()
                .map(Enum::toString).collect(Collectors.joining(", ")));
        Set<NameValueExpression<String>> params = info.getParamsCondition().getExpressions();
        if (!params.isEmpty()) {
            permission.setParameters("Url parameters: " + params.stream()
                    .map(NameValueExpression::getName)
                    .collect(Collectors.joining(", "))
            );
        }
    }

    private void setFrom(HandlerMethod handlerMethod, Permission permission) {
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        String controllerClassName = handlerMethod.getBeanType().getSimpleName();
        permission.setEntityName(controllerClassName.substring(0, controllerClassName.lastIndexOf(REST_CONTROLLER_SUFIX)));
        permission.setHash(StaticUtls.getHashFrom(handlerMethod));
        permission.setMethodName(handlerMethod.getMethod().getName());
        permission.setReturnValue(
                determineJsonType(handlerMethod.getReturnType())
        );
        Arrays.stream(methodParameters)
                .filter(methodParameter -> methodParameter.hasParameterAnnotation(RequestBody.class))
                .map(this::determineJsonType)
                .forEach(permission::addParameter);
    }

    private String determineJsonType(MethodParameter parameter) {

        String type = parameter.getGenericParameterType().getTypeName();

        Class<?> controllerClass = parameter.getContainingClass();

        StringBuilder result = new StringBuilder();
        if (type.contains("Iterable") || type.contains("Set") || type.contains("List")) {
            result.append("Array");
        } else {
            result.append("Object");
        }

        if (type.contains("<ID>")) {
            result.append("<")
                    .append(getGenericParameterType(controllerClass, 1))
                    .append(">");
        } else if (type.contains("<T>")) {
            result.append("<")
                    .append(getGenericParameterType(controllerClass, 0))
                    .append(">");
        } else if (type.contains("<")) {
            result.append(type.substring(type.indexOf("<")));
        } else {
            Class<?> parameterType = parameter.getParameterType();
            if (parameterType.equals(JpaEntity.class)) {
                result.append("<")
                        .append(getGenericParameterType(controllerClass, 0))
                        .append(">");
            } else {
                result.append("<")
                        .append(parameterType.getTypeName())
                        .append(">");
            }

        }

        return result.toString();
    }

    private String getGenericParameterType(Class<?> clazz, int index) {
        return ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[index].getTypeName();
    }




}
