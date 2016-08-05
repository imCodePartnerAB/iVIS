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

        List<User> allUsers = userService.findAll();
        List<JpaClientDetails> allClients = clientDetailsService.findAll();

        methodRestProviderForEntityService.deleteRelations();

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

        List<MethodRestProviderForEntity> allMethods = methodRestProviderForEntityService.findAll();

        for (User user : allUsers) {
            Set<MethodRestProviderForEntity> allowedMethods = user.getAllowedMethods();
            Set<MethodRestProviderForEntity> methodsOfUser = allowedMethods.stream()
                    .mapToInt(allMethods::indexOf)
                    .filter(i -> i != -1)
                    .mapToObj(allMethods::get)
                    .collect(Collectors.toSet());
            if (methodsOfUser != null) {
                if (!methodsOfUser.isEmpty()) {
                    user.setAllowedMethods(methodsOfUser);
                    userService.save(user);
                }
            }
        }

        for (JpaClientDetails client : allClients) {
            Set<MethodRestProviderForEntity> allowedMethods = client.getAllowedMethods();
            Set<MethodRestProviderForEntity> methodsOfClient = allowedMethods.stream()
                    .mapToInt(allMethods::indexOf)
                    .filter(i -> i != -1)
                    .mapToObj(allMethods::get)
                    .collect(Collectors.toSet());
            if (methodsOfClient != null) {
                if (!methodsOfClient.isEmpty()) {
                    client.setAllowedMethods(methodsOfClient);
                    clientDetailsService.updateClientDetails(client);
                }
            }
        }





    }




}
