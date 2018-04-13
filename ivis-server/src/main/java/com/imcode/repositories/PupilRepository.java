package com.imcode.repositories;

import com.imcode.entities.Pupil;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by vitaly on 13.05.15.
 */
public interface PupilRepository extends PersonalizedRepository<Pupil>, JpaSpecificationExecutor<Pupil> {

    @Query("SELECT pupil FROM Pupil pupil WHERE pupil.person.id = ?1")
    Pupil findPupilByPersonId(Long personId);
}
