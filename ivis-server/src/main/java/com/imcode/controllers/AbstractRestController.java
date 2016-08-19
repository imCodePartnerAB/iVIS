package com.imcode.controllers;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.exceptions.factories.ErrorBuilder;
import com.imcode.exceptions.wrappers.GeneralError;
import com.imcode.services.GenericService;
import com.imcode.services.NamedService;
import com.imcode.services.PersonalizedService;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
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

    // Getting entity by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") ID id, HttpServletResponse response, WebRequest webRequest) throws Exception {
        T entity = service.find(id);
        StaticUtls.checkNullAndSetNoContent(entity, response);
        return entity;
    }

    //Getting list of entities
    @RequestMapping(method = RequestMethod.GET)
    public Object getAll(WebRequest webRequest, HttpServletResponse response, Model model) throws Exception {
        List<T> result = service.findAll();
        StaticUtls.checkNullAndSetNoContent(result, response);
        return result;
    }

    //Creating entity
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Object create(@RequestBody @Valid T entity,
                                       HttpServletResponse response,
                                       BindingResult bindingResult, WebRequest webRequest) throws Exception {

        new GenericValidator(true, "id").invoke(entity, bindingResult);

        return service.save(entity);

    }

    @RequestMapping(value = "/saveall", method = RequestMethod.POST)
    public @ResponseBody Object saveAll(@RequestBody Iterable<T> entities,
                                        HttpServletResponse response,
                                        BindingResult bindingResult,
                                        WebRequest webRequest, @RequestParam(required = false) Boolean full) throws Exception {
        Iterable<T> result = service.save(entities);

        Iterator<T> iterator = result.iterator();

        while (iterator.hasNext()) {
            T next = iterator.next();
            new GenericValidator(getFieldsConstraints()).invoke(next, bindingResult);
            new GenericValidator(true, "id").invoke(next, bindingResult);
        }

        if (Boolean.FALSE.equals(full)) {
            List<ID> ids = StreamSupport.stream(result.spliterator(), false).map(JpaEntity::getId).collect(Collectors.toList());
            return ids;
        }

        return result;
    }


    // Updating entity
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") ID id, HttpServletResponse response, @RequestBody(required = false) @Valid T entity, BindingResult bindingResult, WebRequest webRequest) throws Exception {
        T existsEntity = getService().find(id);

        if (existsEntity == null) {
            bindingResult.reject(null, "Try update non exist entity");
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        new GenericValidator(true, "id").invoke(entity, bindingResult);

        boolean isCopied = false;

        try {
            isCopied = StaticUtls.nullAwareBeanCopy(existsEntity, entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (isCopied) {
            existsEntity = service.save(existsEntity);
        } else {
            StaticUtls.checkNullAndSetNoContent(null, response);
        }

        return existsEntity;

    }

    //Deleting entity
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") ID id, HttpServletResponse response, WebRequest webRequest) throws Exception {
        T entity = service.find(id);
        if (entity == null) {
            BindingResult bindingResult = new BeanPropertyBindingResult(entity, "entity");
            bindingResult.reject(null, "Try delete non exist entity");
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        service.delete(id);
        return entity;
    }

    @SuppressWarnings("unchecked")
//    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public Object getByName(WebRequest webRequest, Model model,
                            HttpServletResponse response,
                            @RequestParam("name") String name,
                            @RequestParam(value = "first", required = false) Boolean firstOnly) {

        if (service instanceof NamedService) {
            NamedService<T> namedService = (NamedService<T>) service;

            if (firstOnly == null || !firstOnly) {
                List<T> byName = namedService.findByName(name);
                StaticUtls.checkNullAndSetNoContent(byName, response);
                return byName;
            } else {
                T firstByName = namedService.findFirstByName(name);
                StaticUtls.checkNullAndSetNoContent(firstByName, response);
                return firstByName;
            }
        }

        throw new UnsupportedOperationException("findByName method not supported!");

    }

    @SuppressWarnings("unchecked")
//    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
    public Object getByPersonalId(@RequestParam("personalId") String personId,
                                  @RequestParam(value = "first", required = false) Boolean firstOnly,
                                  HttpServletResponse response
    ) {

        if (service instanceof PersonalizedService) {
            PersonalizedService<T> personalizedService = (PersonalizedService<T>) service;

            if (firstOnly == null || !firstOnly) {
                List<T> byPersonalId = personalizedService.findByPersonalId(personId);
                StaticUtls.checkNullAndSetNoContent(byPersonalId, response);
                return byPersonalId;
            } else {
                T firstByPersonalId = personalizedService.findFirstByPersonalId(personId);
                StaticUtls.checkNullAndSetNoContent(firstByPersonalId, response);
                return firstByPersonalId;
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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GeneralError handleException(Exception exception) {

        if (exception instanceof MethodArgumentNotValidException) {

            BindingResult bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
            return ErrorBuilder.buildValidationError(bindingResult);

        } else if (exception instanceof DataAccessException) {

            return ErrorBuilder.buildDatabasePersistenceError(exception);

        } else if (exception instanceof HttpMessageConversionException) {

            return ErrorBuilder.buildJsonMappingException(exception);

        }

        return ErrorBuilder.buildUncaughtException(exception);
    }


}
