package com.imcode.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vitaly on 10.08.15.
 */
public class AbstractNamedEntityService<T, ID extends Serializable, REPOSITORY_TYPE extends JpaRepository<T, ID>> extends AbstractService<T, ID, REPOSITORY_TYPE> implements NamedEntityService<T> {
    @Override
    public T findFirstByName(String name) {
        if (repo instanceof NamedEntityService) {
            NamedEntityService<T> namedRepo = (NamedEntityService<T>) repo;
            return namedRepo.findFirstByName(name);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> findByName(String name) {
        if (repo instanceof NamedEntityService) {
            NamedEntityService<T> namedRepo = (NamedEntityService) repo;
            return  namedRepo.findByName(name);
        }

        throw new UnsupportedOperationException();
    }
}
