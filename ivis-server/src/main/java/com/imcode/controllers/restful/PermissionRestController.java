package com.imcode.controllers.restful;

import com.imcode.entities.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by ruslan on 14.11.16.
 */
@RestController
public class PermissionRestController {

    private static final String API_PREFIX = "/api";

    @Autowired
    @Qualifier("org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#0")
    private RequestMappingHandlerMapping handler;

    @PostConstruct
    public void initPermissions() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handler.getHandlerMethods();
        handlerMethods.forEach(this::process);
    }

    private void process(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
        Permission permission = new Permission();
    }

}
