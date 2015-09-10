package com.imcode.repositories;

import com.imcode.entities.Pupil;
import com.imcode.entities.interfaces.JpaNamedEntity;
import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by vitaly on 09.09.15.
 */
@NoRepositoryBean
public interface PersonalizedRepository<T extends JpaPersonalizedEntity<Long>> extends JpaRepository<T, Long> {

    @Query("select p from #{#entityName} p where p.person.personalId = :personalId")
    T findFirstByPersonalId(@Param("personalId") String personalId);

    @Query("select p from #{#entityName} p where p.person.personalId = :personalId")
    List<T> findByPersonalId(@Param("personalId") String personalId);
}
