package com.imcode.services;

import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.repositories.PersonalizedRepository;

import java.util.List;

/**
 * Created by vitaly on 10.08.15.
 */
public class AbstractPersonalizedEntityService<T extends JpaPersonalizedEntity, REPOSITORY_TYPE extends PersonalizedRepository<T>> extends AbstractService<T, Long, REPOSITORY_TYPE> implements PersonalizedService<T> {
    @Override
    public T findFirstByPersonalId(String personalId) {
//        if (repo instanceof NamedService) {
//            NamedService<T> namedRepo = (NamedService<T>) repo;
            return repo.findFirstByPersonalId(personalId);
//        }
//        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> findByPersonalId(String personalId) {
//        if (repo instanceof NamedService) {
//            NamedService<T> namedRepo = (NamedService) repo;
            return  repo.findByPersonalId(personalId);
//        }
//
//        throw new UnsupportedOperationException();
    }
}
