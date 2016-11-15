package com.imcode.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.exceptions.factories.ErrorBuilder;
import com.imcode.exceptions.wrappers.GeneralError;
import com.imcode.search.SearchCriteria;
import com.imcode.search.SearchCriteries;
import com.imcode.services.AbstractService;
import com.imcode.services.GenericService;
import com.imcode.services.NamedService;
import com.imcode.services.PersonalizedService;
import com.imcode.specifications.JpaEntitySpecification;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
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

    // Getting entity by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public T get(@PathVariable("id") ID id, HttpServletResponse response, WebRequest webRequest) throws Exception {
        T entity = service.find(id);
        StaticUtls.checkNullAndSetNoContent(entity, response);
        return entity;
    }

    //Getting list of entities
    @RequestMapping(method = RequestMethod.GET)
    public List<T> getAll(WebRequest webRequest, HttpServletResponse response, Model model) throws Exception {
        List<T> result = service.findAll();
        StaticUtls.checkNullAndSetNoContent(result, response);
        return result;
    }

    //Creating entity
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody T create(@RequestBody @Valid T entity,
                                       HttpServletResponse response,
                                       BindingResult bindingResult, WebRequest webRequest) throws Exception {

        entity.setId(null);

        return service.save(entity);

    }

    @RequestMapping(value = "/saveall", method = RequestMethod.POST)
    public @ResponseBody Iterable<T> saveAll(@RequestBody Iterable<T> entities,
                                        HttpServletResponse response,
                                        WebRequest webRequest) throws Exception {

        Iterable<T> result = service.save(entities);

        return result;
    }

    @RequestMapping(value = "/saveall", method = RequestMethod.POST, params = {"full"})
    public @ResponseBody List<ID> saveAllAndReturnIds(@RequestBody Iterable<T> entities,
                                                      HttpServletResponse response,
                                                      WebRequest webRequest) throws Exception {

        Iterable<T> saved = service.save(entities);
        List<ID> ids = StreamSupport.stream(saved.spliterator(), false).map(JpaEntity::getId).collect(Collectors.toList());
        return ids;

    }


    // Updating entity
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public T update(@PathVariable("id") ID id, HttpServletResponse response, @RequestBody(required = false) @Valid T entity, BindingResult bindingResult, WebRequest webRequest) throws Exception {
        T existsEntity = getService().find(id);

        if (existsEntity == null) {
            bindingResult.reject(null, "Try update non exist entity");
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        entity.setId(null);

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
    public T delete(@PathVariable("id") ID id, HttpServletResponse response, WebRequest webRequest) throws Exception {
        T entity = service.find(id);
        if (entity == null) {
            BindingResult bindingResult = new BeanPropertyBindingResult(entity, "entity");
            bindingResult.reject(null, "Try delete non exist entity");
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        service.delete(id);
        return entity;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody List<T> search(@RequestBody List<SearchCriteries.SearchCriteriaResult> criteries) throws Exception {

        if (criteries == null || criteries.isEmpty()) {
            return null;
        }

        SearchCriteries.SearchCriteriaResult first = criteries.get(0);

        Sort sort = null;
        if (first.getOrderBy() != null && !first.getOrderBy().isEmpty() && first.getOrder() != null ) {
            sort = new Sort(new Sort.Order(Sort.Direction.fromString(first.getOrder().toString()), first.getOrderBy()));
        }

        Specification<T> result = createSpec(criteries, 0);
        Boolean nextAnd = first.getNextAnd();
        for (int i = 1; i < criteries.size(); i++) {
            if (nextAnd) {
                result = Specifications.where(result).and(createSpec(criteries, i));
            } else {
                result = Specifications.where(result).or(createSpec(criteries, i));
            }
            nextAnd = criteries.get(i).getNextAnd();
        }

        AbstractService abstractService = (AbstractService) service;

        return  sort == null ? abstractService.findAll(result) : abstractService.findAll(result, sort);
    }


    @RequestMapping(value = "/search/first", method = RequestMethod.POST)
    public @ResponseBody T searchFirst(@RequestBody List<SearchCriteries.SearchCriteriaResult> criteries) throws Exception {

        if (criteries == null || criteries.isEmpty()) {
            return null;
        }

        SearchCriteries.SearchCriteriaResult searchCriteriaResult = criteries.get(0);

        Specification<T> result = createSpec(criteries, 0);
        Boolean nextAnd = searchCriteriaResult.getNextAnd();
        for (int i = 1; i < criteries.size(); i++) {
            if (nextAnd) {
                result = Specifications.where(result).and(createSpec(criteries, i));
            } else {
                result = Specifications.where(result).or(createSpec(criteries, i));
            }
        }

        AbstractService abstractService = (AbstractService) service;

        return (T) abstractService.findOne(result);
    }

    @RequestMapping(method = RequestMethod.DELETE, params = {"ids"})
    public @ResponseBody List<T> deleteByIds(@RequestParam("ids") List<ID> ids,
                                        HttpServletResponse response,
                                        WebRequest webRequest) throws Exception {
        List<T> collect = ids.stream().map(service::find).collect(Collectors.toList());
        service.delete(collect);
        return collect;
    }

    // if need override
    public List<T> getByName(WebRequest webRequest, Model model,
                            HttpServletResponse response,
                            @RequestParam("name") String name) {

        if (service instanceof NamedService) {
            NamedService<T> namedService = (NamedService<T>) service;
            List<T> byName = namedService.findByName(name);
            StaticUtls.checkNullAndSetNoContent(byName, response);
            return byName;
        }

        throw new UnsupportedOperationException("findByName method not supported!");

    }

    // if need override
    public T getFirstByName(WebRequest webRequest, Model model,
                             HttpServletResponse response,
                             @RequestParam("name") String name) {

        if (service instanceof NamedService) {
            NamedService<T> namedService = (NamedService<T>) service;
            T firstByName = namedService.findFirstByName(name);
            StaticUtls.checkNullAndSetNoContent(firstByName, response);
            return firstByName;
        }

        throw new UnsupportedOperationException("findByName method not supported!");

    }

    public List<T> getByPersonalId(@RequestParam("personalId") String personId,
                                  HttpServletResponse response
    ) {

        if (service instanceof PersonalizedService) {
            PersonalizedService<T> personalizedService = (PersonalizedService<T>) service;

            List<T> byPersonalId = personalizedService.findByPersonalId(personId);
            StaticUtls.checkNullAndSetNoContent(byPersonalId, response);
            return byPersonalId;
        }

        throw new UnsupportedOperationException("findByName method not supported!");
    }

    public T getFirstByPersonalId(@RequestParam("personalId") String personId,
                                  HttpServletResponse response
    ) {

        if (service instanceof PersonalizedService) {
            PersonalizedService<T> personalizedService = (PersonalizedService<T>) service;

            T firstByPersonalId = personalizedService.findFirstByPersonalId(personId);
            StaticUtls.checkNullAndSetNoContent(firstByPersonalId, response);
            return firstByPersonalId;
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
        binder.setValidator(new GeneralValidator(getFieldsConstraints()));
    }

    //must be overriding
    protected Map<String, Map<GeneralValidator.Constraint, String>>  getFieldsConstraints() {
        Map<String, Map<GeneralValidator.Constraint, String>> constraints = new HashMap<>();
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

    private JpaEntitySpecification<T> createSpec(List<SearchCriteries.SearchCriteriaResult> criteriaResults, int index) throws IOException {
        SearchCriteries.SearchCriteriaResult criteriaResult = criteriaResults.get(index);
        String valueJson = criteriaResult.getValue();
        Object object = criteriaResult.getValueType().cast(new ObjectMapper().readValue(valueJson, criteriaResult.getValueType()));
        SearchCriteria searchCriteria = new SearchCriteria(criteriaResult.getFieldName(), criteriaResult.getOperation(), object);
        return new JpaEntitySpecification<>(searchCriteria);
    }
}
