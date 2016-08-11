package com.imcode.controllers.html;

import com.imcode.entities.EntityRestProviderInformation;
import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.entities.OnceTimeAccessToken;
import com.imcode.entities.User;
import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.services.EntityRestProviderInformationService;
import com.imcode.services.MethodRestProviderForEntityService;
import com.imcode.services.OnceTimeAccessTokenService;
import com.imcode.services.UserService;
import com.imcode.utils.ControllerReflectionUtil;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private IvisClientDetailsService clientDetailsService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void deleteExpiredOrUsedOnceTimeAccessTokens() {
        List<OnceTimeAccessToken> onceTimeAccessTokens = onceTimeAccessTokenService.selectExpiredOrUsedTokens();
        onceTimeAccessTokenService.delete(onceTimeAccessTokens);
    }

    @PostConstruct
    public void updateInformationAboutServices() {

        List<EntityRestProviderInformation> allInfo = entityRestProviderInformationService.findAll();

        methodRestProviderForEntityService.deleteAll();
        entityRestProviderInformationService.deleteAll();

        Set<Class<?>> restControllerClasses = ControllerReflectionUtil
                .getAllClassesFromPackage(ControllerReflectionUtil.REST_CONTROLLERS_PACKAGE);

        for (Class<?> clazz : restControllerClasses) {
            ControllerReflectionUtil util = new ControllerReflectionUtil(clazz);

            EntityRestProviderInformation entityRestProviderInformation = new EntityRestProviderInformation();
            entityRestProviderInformation.setRestControllerClass(clazz);
            entityRestProviderInformation.setEntityClass(util.getEntityName());
            EntityRestProviderInformation savedInfo =
                    entityRestProviderInformationService.save(entityRestProviderInformation);

            Set<Method> methods = util.getMethodsWithRequestMappingAnnotation();
            Set<MethodRestProviderForEntity> methodsForPersist = methods.stream()
                    .map(method -> util.buildPersistenceMethodOf(method, savedInfo))
                    .collect(Collectors.toSet());
            methodRestProviderForEntityService.save(methodsForPersist);

        }

        for (EntityRestProviderInformation info : allInfo) {

            EntityRestProviderInformation persistInfoByEntityClass = entityRestProviderInformationService.findByEntityClass(info.getEntityClass());
            Set<MethodRestProviderForEntity> entityProviderMethods = info.getEntityProviderMethods();

            for (MethodRestProviderForEntity entityProviderMethod : entityProviderMethods) {
                Optional<MethodRestProviderForEntity> methodForCheckOptional = persistInfoByEntityClass
                        .getEntityProviderMethods().stream()
                        .filter(method -> entityProviderMethod.getName().equals(method.getName()))
                        .findFirst();
                if (methodForCheckOptional.isPresent()) {
                    MethodRestProviderForEntity methodForCheck = methodForCheckOptional.get();
                    if (methodForCheck.equals(entityProviderMethod)) {
                        entityProviderMethod.setId(null);
                        try {
                            StaticUtls.nullAwareBeanCopy(methodForCheck, entityProviderMethod);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        methodRestProviderForEntityService.save(methodForCheck);
                    }
                }
            }

        }



    }




}
