package com.imcode.controllers.html;

import com.imcode.controllers.restful.ActivityRestControllerImpl;
import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.entities.OnceTimeAccessToken;
import com.imcode.services.OnceTimeAccessTokenService;
import com.imcode.utils.ControllerReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by ruslan on 01.08.16.
 */
@Controller
public class OnStartupController {


    @Autowired
    public OnceTimeAccessTokenService onceTimeAccessTokenService;

    @PostConstruct
    public void deleteExpiredOrUsedOnceTimeAccessTokens() {
        List<OnceTimeAccessToken> onceTimeAccessTokens = onceTimeAccessTokenService.selectExpiredOrUsedTokens();
        onceTimeAccessTokenService.delete(onceTimeAccessTokens);
    }

    @PostConstruct
    public void persistAllEntityServicesAndMethods() {

    }

    @RequestMapping(value = "/reflect", method = RequestMethod.GET)
    public void reflectionTest(WebRequest webRequest) {
        ControllerReflectionUtil.getAllClassesFromPackage("com.imcode.controllers.restful");
        ControllerReflectionUtil util = new ControllerReflectionUtil(ActivityRestControllerImpl.class);
        Set<Method> methodsWithRequestMappingAnnotation = util.getMethodsWithRequestMappingAnnotation();
        MethodRestProviderForEntity methodRestProviderForEntity = util.buildPersistenceMethodOf(methodsWithRequestMappingAnnotation.stream().findFirst().get());
        System.out.println();
    }




}
