package com.imcode.services;

import java.util.List;

/**
 * Created by vitaly on 10.08.15.
 */
public interface NamedEntityService<T>{
    T findFirstByName(String name);
    List<T> findByName(String name);
}
