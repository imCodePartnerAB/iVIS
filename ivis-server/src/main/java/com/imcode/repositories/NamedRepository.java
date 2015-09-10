package com.imcode.repositories;

import com.imcode.entities.interfaces.JpaNamedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by vitaly on 09.09.15.
 */
@NoRepositoryBean
public interface NamedRepository<T extends JpaNamedEntity<Long>> extends JpaRepository<T, Long> {

    List<T> findByName(String name);
    T findFirstByName(String name);
}
