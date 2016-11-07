package com.imcode.services;

import java.util.Iterator;
import java.util.List;

/**
 * Created by vitaly on 17.02.15.
 */
public interface GenericService<T, ID> {
    T save(T entity);

    Iterable<T> save(Iterable<T> entities);

    T find(ID id);

    boolean exist(ID id);

    void delete(ID id);

    default void delete(Iterable<T> entities) {

    }

    List<T> findAll();
}
