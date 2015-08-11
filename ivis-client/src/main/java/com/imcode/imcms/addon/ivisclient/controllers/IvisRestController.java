package com.imcode.imcms.addon.ivisclient.controllers;

import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.services.GenericService;
import imcode.services.IvisServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.resource.UserApprovalRequiredException;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vitaly on 15.06.15.
 */
@RestController
@RequestMapping("rest")
public class IvisRestController {
    @Autowired
    public IvisServiceFactory ivisServiceFactory;

    private <T extends AbstractIdEntity> List<T> getListOfEntity(Class<T> entityClass) throws ClassNotFoundException {
        Class serviceClass = Class.forName("com.imcode.services." + entityClass.getSimpleName() + "Service");
        GenericService<T, ?> service = ivisServiceFactory.getService(serviceClass);
        List<T> entityList = service.findAll();

        return entityList;
    }

    private <T extends AbstractIdEntity> T getEntity(Class<T> entityClass, Object id) throws ClassNotFoundException {
        Class serviceClass = Class.forName("com.imcode.services." + entityClass.getSimpleName() + "Service");
        GenericService<T, Object> service = ivisServiceFactory.getService(serviceClass);
        T entity = service.find(id);

        return entity;
    }

    @RequestMapping(value = "/{entityClassName}", method = RequestMethod.GET)
    public Object getList(@PathVariable("entityClassName") String entityClassName) throws ClassNotFoundException {
        Class entityClass = Class.forName("com.imcode.entities." + entityClassName);
        List entityList = getListOfEntity(entityClass);

        return entityList;
    }

    @RequestMapping(value = "/{entityClassName}/{id}", method = RequestMethod.GET)
    public Object getOne(@PathVariable("entityClassName") String entityClassName, @PathVariable("id") Long id) throws ClassNotFoundException {
        Class entityClass = Class.forName("com.imcode.entities." + entityClassName);
        Object entity = getEntity(entityClass, id);

        return entity;
    }

    @ExceptionHandler({UserRedirectRequiredException.class,
            UserApprovalRequiredException.class,
            OAuth2AccessDeniedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "OAuth authorization error")
    public void error() {

    }

}
