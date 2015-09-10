package com.imcode.services;

import java.util.List;

/**
 * Created by vitaly on 17.02.15.
 */
public interface PersonalizedService<T> {

    T findFirstByPersonalId(String personalId);

    List<T> findByPersonalId(String personalId);
}
