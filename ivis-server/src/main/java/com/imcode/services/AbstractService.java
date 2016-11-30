package com.imcode.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.search.SearchCriteria;
import com.imcode.search.SearchCriteries;
import com.imcode.specifications.JpaEntitySpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T extends JpaEntity, ID extends Serializable, REPOSITORY_TYPE extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>> implements GenericService<T, ID> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("messages")
    protected MessageSource messageSource;

    @Autowired
    protected REPOSITORY_TYPE repo;

    @Override
    @Transactional
    public T save(T entity) {
        return repo.saveAndFlush(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T find(ID id) {
        return repo.findOne(id);
    }

    @Override
    public boolean exist(ID id) {
        return repo.exists(id);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        repo.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repo.findAll();
    }

    @Transactional
    public T saveWithoutFlush(T entity) {
        return repo.save(entity);
    }

    @Transactional
    public void flush() {
        repo.flush();
    }

    @Override
    @Transactional
    public Iterable<T> save(Iterable<T> entities) {
        return repo.save(entities);
    }

    @Override
    @Transactional
    public void delete(Iterable<T> entities) {
        repo.delete(entities);
    }

    @Override
    public List<T> search(List<SearchCriteries.SearchCriteriaResult> criteries) {

        SearchCriteries.SearchCriteriaResult first = criteries.get(0);

        Sort sort = null;

        if (first.getOrderBy() != null && !first.getOrderBy().isEmpty() && first.getOrder() != null ) {
            sort = new Sort(new Sort.Order(Sort.Direction.fromString(first.getOrder().toString()), first.getOrderBy()));
        }

        Specification<T> resultSpec = getResultSpec(criteries);

        if (resultSpec == null) {
            return null;
        }

        return sort == null ? findAll(resultSpec) : findAll(resultSpec, sort);
    }

    @Override
    public T searchOne(List<SearchCriteries.SearchCriteriaResult> criteries) {

        Specification<T> resultSpec = getResultSpec(criteries);

        return resultSpec == null ? null : findOne(resultSpec);
    }

    @Transactional
    private List<T> findAll(Specification<T> specification) {
        return repo.findAll(specification);
    }

    @Transactional
    private List<T> findAll(Specification<T> specification, Sort sort) {
        return repo.findAll(specification, sort);
    }

    @Transactional
    private T findOne(Specification<T> specification) {
        return repo.findOne(specification);
    }

    public REPOSITORY_TYPE getRepo() {
        return repo;
    }

    public void setRepo(REPOSITORY_TYPE repo) {
        this.repo = repo;
    }

    public Logger getLogger() {
        return logger;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    private JpaEntitySpecification<T> createSpec(List<SearchCriteries.SearchCriteriaResult> criteriaResults, int index) {
        try {
            SearchCriteries.SearchCriteriaResult criteriaResult = criteriaResults.get(index);
            String valueJson = criteriaResult.getValue();
            Object object = criteriaResult.getValueType().cast(new ObjectMapper().readValue(valueJson, criteriaResult.getValueType()));
            SearchCriteria searchCriteria = new SearchCriteria(criteriaResult.getFieldName(), criteriaResult.getOperation(), object);
            return new JpaEntitySpecification<>(searchCriteria);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getCause());
        }
    }

    private Specification<T> getResultSpec(List<SearchCriteries.SearchCriteriaResult> criteries) {
        if (criteries == null || criteries.isEmpty()) {
            return null;
        }

        SearchCriteries.SearchCriteriaResult first = criteries.get(0);

        Specification<T> resultSpec = createSpec(criteries, 0);
        Boolean isNextConditionAnd = first.getNextAnd();
        for (int i = 1; i < criteries.size(); i++) {
            if (isNextConditionAnd) {
                resultSpec = Specifications.where(resultSpec).and(createSpec(criteries, i));
            } else {
                resultSpec = Specifications.where(resultSpec).or(createSpec(criteries, i));
            }
            isNextConditionAnd = criteries.get(i).getNextAnd();
        }

        return resultSpec;
    }
}
