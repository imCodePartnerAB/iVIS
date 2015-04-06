package com.imcode.services;

import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.List;

/**
 * Created by vitaly on 17.02.15.
 */
public interface GenericService<T, ID> {
    T save(T entity);

    T find(ID id);

    boolean exist(ID id);

    void delete(ID id);

    List<T> findAll();
}
