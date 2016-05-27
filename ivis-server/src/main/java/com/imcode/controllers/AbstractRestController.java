package com.imcode.controllers;

import com.imcode.exceptions.MessageOfException;
import com.imcode.exceptions.MessagingException;
import com.imcode.exceptions.ValidationErrorBuilder;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.misc.errors.ErrorFactory;
import com.imcode.services.GenericService;
import com.imcode.services.NamedService;
import com.imcode.services.PersonalizedService;
import com.imcode.exceptions.ValidationError;
import com.imcode.utils.StaticUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vitaly on 17.02.15.
 */
public abstract class AbstractRestController<T extends JpaEntity<ID>, ID extends Serializable, SERVICE_TYPE extends GenericService<T, ID>> implements CrudController<T, ID>{

    @Autowired
    private SERVICE_TYPE service;

    @Autowired
    private ErrorFactory errorFactory;

    @Autowired
    private ApplicationContext ctx;

    // Getting entity by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") ID id, WebRequest webRequest) {
        return service.find(id);
    }

    //Getting list of entities
    @RequestMapping(method = RequestMethod.GET)
    public Object getAll(WebRequest webRequest, Model model) {
        List<T> result = service.findAll();
        return result;
    }

    //Creating entity
    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Object create(@RequestBody T entity, WebRequest webRequest) throws MessagingException {
//        try {
            return service.save(entity);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
    }

    @RequestMapping(value = "/bulk", method = RequestMethod.POST)
    public @ResponseBody Object sava(@RequestBody Iterable<T> entities, WebRequest webRequest, @RequestParam(required = false) Boolean full) {
        Iterable<T> result = service.save(entities);

        if (Boolean.FALSE.equals(full)) {
            List<ID> ids = StreamSupport.stream(result.spliterator(), false).map(JpaEntity::getId).collect(Collectors.toList());
            return ids;
        }

        return result;
    }


    // Updating entity
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") ID id, @RequestBody(required = false) T entity, WebRequest webRequest) {
        T existsEntity = getService().find(id);

        if (existsEntity != null) {
            try {
                StaticUtils.nullAwareBeanCopy(existsEntity, entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            service.save(existsEntity);
        }

        return existsEntity;
//        return service.save(entity);
    }

    //Deleting entity
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") ID id, WebRequest webRequest) {
        service.delete(id);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public Object getByName(WebRequest webRequest, Model model,
                         @RequestParam("name") String name,
                         @RequestParam(value = "first", required = false) Boolean firstOnly) {

        if (service instanceof NamedService) {
            NamedService<T> namedService = (NamedService<T>) service;

            if (firstOnly == null || !firstOnly) {
                return namedService.findByName(name);
            } else {
                return namedService.findFirstByName(name);
            }
        }

        throw new UnsupportedOperationException("findByName metod not supported!");

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
    public Object getByPersonalId(@RequestParam("personalId") String personId,
                                  @RequestParam(value = "first", required = false) Boolean firstOnly) {

        if (service instanceof PersonalizedService) {
            PersonalizedService<T> personalizedService = (PersonalizedService<T>) service;

            if (firstOnly == null || !firstOnly) {
                return personalizedService.findByPersonalId(personId);
            } else {
                return personalizedService.findFirstByPersonalId(personId);
            }
        }

        throw new UnsupportedOperationException("findByName metod not supported!");
    }

    // Getters & Setters
    //------------------------------------------------------------------------------------------------------------------
    public SERVICE_TYPE getService() {
        return service;
    }

    public void setService(SERVICE_TYPE service) {
        this.service = service;
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return createValidationError(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MessageOfException messagingException(MessagingException exception) {
        return exception.getExceptionMeassage();
    }

    private ValidationError createValidationError(MethodArgumentNotValidException e) {
        return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
    }

//    public ErrorFactory getErrorFactory() {
//        return errorFactory;
//    }
//
//    public void setErrorFactory(ErrorFactory errorFactory) {
//        this.errorFactory = errorFactory;
//    }
}
