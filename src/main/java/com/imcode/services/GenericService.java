package com.imcode.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vitaly on 17.02.15.
 */
public interface GenericService<T, ID> {
    public T save(T entity);

    public T find(ID id);

    public boolean exist(ID id);

    public void delete(ID id);

    @Transactional
    public List<T> findAll();
}
