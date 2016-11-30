package com.imcode.services;

import com.imcode.entities.interfaces.JpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vitaly on 10.08.15.
 */
public class AbstractNamedService<T extends JpaEntity, ID extends Serializable, REPOSITORY_TYPE extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>> extends AbstractService<T, ID, REPOSITORY_TYPE> implements NamedService<T> {
    @Override
    @SuppressWarnings("unchecked")
    public T findFirstByName(String name) {
        if (repo instanceof NamedService) {
            NamedService<T> namedRepo = (NamedService<T>) repo;
            return namedRepo.findFirstByName(name);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByName(String name) {
        if (repo instanceof NamedService) {
            NamedService<T> namedRepo = (NamedService<T>) repo;
            return  namedRepo.findByName(name);
        }

        throw new UnsupportedOperationException();
    }
}
