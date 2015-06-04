package com.imcode.repositories;

import com.imcode.entities.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by vitaly on 13.05.15.
 */
public interface PupilRepository extends JpaRepository<Pupil, Long>{
    @Query("select p from Pupil p where p.person.personalId = :personalId")
    Pupil findByPersonalId(@Param("personalId") String personalId);
}
