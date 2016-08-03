package com.imcode.controllers.html;

import com.imcode.entities.EntityRestProviderInformation;
import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.entities.OnceTimeAccessToken;
import com.imcode.services.EntityRestProviderInformationService;
import com.imcode.services.MethodRestProviderForEntityService;
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
import java.util.stream.Collectors;

/**
 * Created by ruslan on 01.08.16.
 */
@Controller
public class OnStartupController {


    @Autowired
    public OnceTimeAccessTokenService onceTimeAccessTokenService;

    @Autowired
    private MethodRestProviderForEntityService methodRestProviderForEntityService;

    @Autowired
    private EntityRestProviderInformationService entityRestProviderInformationService;

    @PostConstruct
    public void deleteExpiredOrUsedOnceTimeAccessTokens() {
        List<OnceTimeAccessToken> onceTimeAccessTokens = onceTimeAccessTokenService.selectExpiredOrUsedTokens();
        onceTimeAccessTokenService.delete(onceTimeAccessTokens);
    }

    @PostConstruct
    public void updateInformationAboutServices() {

        methodRestProviderForEntityService.deleteAll();
        entityRestProviderInformationService.deleteAll();

        Set<Class<?>> restControllerClasses = ControllerReflectionUtil
                .getAllClassesFromPackage(ControllerReflectionUtil.REST_CONTROLLERS_PACKAGE);

        for (Class<?> clazz : restControllerClasses) {
            ControllerReflectionUtil util = new ControllerReflectionUtil(clazz);

            EntityRestProviderInformation entityRestProviderInformation = new EntityRestProviderInformation();
            entityRestProviderInformation.setRestControllerClass(clazz);
            entityRestProviderInformation.setEntityClass(util.getEntityClass());
            EntityRestProviderInformation savedInfo =
                    entityRestProviderInformationService.save(entityRestProviderInformation);

            Set<Method> methods = util.getMethodsWithRequestMappingAnnotation();
            Set<MethodRestProviderForEntity> methodsForPersist = methods.stream()
                    .map(method -> util.buildPersistenceMethodOf(method, savedInfo))
                    .collect(Collectors.toSet());
            methodRestProviderForEntityService.save(methodsForPersist);

        }
    }




}
