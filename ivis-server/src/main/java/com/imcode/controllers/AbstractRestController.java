package com.imcode.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.exceptions.factories.ErrorBuilder;
import com.imcode.exceptions.wrappers.GeneralError;
import com.imcode.misc.errors.ErrorFactory;
import com.imcode.services.GenericService;
import com.imcode.services.NamedService;
import com.imcode.services.PersonalizedService;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Object get(@PathVariable("id") ID id, HttpServletResponse response, WebRequest webRequest) {
        T entity = service.find(id);
        return entity;
    }

    //Getting list of entities
    @RequestMapping(method = RequestMethod.GET)
    public Object getAll(WebRequest webRequest, HttpServletResponse response, Model model) {
        List<T> result = service.findAll();
        return result;
    }

    //Creating entity
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Object create(@RequestBody @Valid T entity,
                                       HttpServletResponse response,
                                       BindingResult bindingResult, WebRequest webRequest) throws MethodArgumentNotValidException {

        new GenericValidator(true, "id").invoke(entity, bindingResult);

        return service.save(entity);

    }

    @RequestMapping(value = "/saveall", method = RequestMethod.POST)
    public @ResponseBody Object saveAll(@RequestBody Iterable<T> entities,
                                        HttpServletResponse response,
                                        WebRequest webRequest, @RequestParam(required = false) Boolean full) {
        Iterable<T> result = service.save(entities);

        if (Boolean.FALSE.equals(full)) {
            List<ID> ids = StreamSupport.stream(result.spliterator(), false).map(JpaEntity::getId).collect(Collectors.toList());
            return ids;
        }

        return result;
    }


    // Updating entity
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") ID id, HttpServletResponse response, @RequestBody(required = false) T entity, WebRequest webRequest) {
        T existsEntity = getService().find(id);

        if (existsEntity != null) {
            try {
                StaticUtls.nullAwareBeanCopy(existsEntity, entity);
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
    public Object delete(@PathVariable("id") ID id, HttpServletResponse response, WebRequest webRequest) {
        T entity = service.find(id);
        service.delete(id);
        return entity;
    }

    @SuppressWarnings("unchecked")
//    @RequestMapping(method = RequestMethod.GET, params = {"name"})
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

        throw new UnsupportedOperationException("findByName method not supported!");

    }

    @SuppressWarnings("unchecked")
//    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
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

        throw new UnsupportedOperationException("findByName method not supported!");
    }

    // Getters & Setters
    //------------------------------------------------------------------------------------------------------------------
    public SERVICE_TYPE getService() {
        return service;
    }

    public void setService(SERVICE_TYPE service) {
        this.service = service;
    }

    //Validation

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new GenericValidator(getFieldsConstraints()));
    }

    //must be overriding
    protected Map<String, Map<GenericValidator.Constraint, String>>  getFieldsConstraints() {
        Map<String, Map<GenericValidator.Constraint, String>> constraints = new HashMap<>();
        return constraints;
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GeneralError handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ErrorBuilder.buildValidationError(exception.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GeneralError handleJsonMappingException(JsonMappingException exception) {
        return ErrorBuilder.buildJsonMappingException(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GeneralError handlePersistenceException(PersistenceException exception) {
        return ErrorBuilder.buildDatabasePersistenceError(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public GeneralError handleException(Exception exception) {
        return ErrorBuilder.buildUncaughtException(exception);
    }



//    private ValidationError createValidationError(MethodArgumentNotValidException e) {
//        return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
//    }


//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public MessageOfException messagingException(MessagingException exception) {
//        return exception.getExceptionMeassage();
//    }

//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public MessageOfException messagingException(Exception exception) {
//        MessageOfException messagingException = new MessageOfException(exception);
//        return messagingException;
//    }

//    public ErrorFactory getErrorFactory() {
//        return errorFactory;
//    }
//
//    public void setErrorFactory(ErrorFactory errorFactory) {
//        this.errorFactory = errorFactory;
//    }
}
