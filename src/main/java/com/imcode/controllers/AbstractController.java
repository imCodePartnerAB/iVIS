package com.imcode.controllers;

import com.imcode.misc.errors.ErrorFactory;
import com.imcode.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vitaly on 17.02.15.
 */
public abstract class AbstractController<T, ID extends Serializable, SERVICE_TYPE extends GenericService<T, ID>> {

    @Autowired
    private SERVICE_TYPE service;

    @Autowired
    private ErrorFactory errorFactory;

    @Autowired
    private ApplicationContext ctx;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody Object get(@PathVariable("id") ID id, WebRequest webRequest) {
        return service.find(id);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public @ResponseBody Object getAll(WebRequest webRequest) {
        List<T> result = service.findAll();
        return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody Object createSchool(@RequestBody T entity, WebRequest webRequest) {
        return service.save(entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public @ResponseBody Object delete(@PathVariable("id") ID id, WebRequest webRequest) {
        service.delete(id);
        
        return null;
    }
    
    // Getters & Setters
    //------------------------------------------------------------------------------------------------------------------
    public SERVICE_TYPE getService() {
        return service;
    }

    public void setService(SERVICE_TYPE service) {
        this.service = service;
    }

    public ErrorFactory getErrorFactory() {
        return errorFactory;
    }

    public void setErrorFactory(ErrorFactory errorFactory) {
        this.errorFactory = errorFactory;
    }

    //    public ApplicationContext getCtx() {
//        return ctx;
//    }
    

//    public void setCtx(ApplicationContext ctx) {
//        this.ctx = ctx;
//    }
}
