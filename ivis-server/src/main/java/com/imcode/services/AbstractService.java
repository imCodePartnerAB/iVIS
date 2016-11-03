package com.imcode.services;

import com.imcode.utils.SelectCriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T, ID extends Serializable, REPOSITORY_TYPE extends JpaRepository<T, ID>> implements GenericService<T, ID> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("messages")
    protected MessageSource messageSource;

    @PersistenceContext
    private EntityManager em;

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
    @Transactional
    public List findAllByCriteria(SelectCriteriaBuilder builder) {
        return em.createNativeQuery(builder.getQuery(), builder.getClazz()).getResultList();
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
}
