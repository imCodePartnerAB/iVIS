package com.imcode.repositories;

import com.imcode.entities.Guardian;
import com.imcode.entities.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by vitaly on 13.05.15.
 */
public interface GuardianRepository extends JpaRepository<Guardian, Long>{
    @Query("select g from Guardian g where g.person.personalId = :personalId")
    Guardian findByPersonalId(@Param("personalId") String personalId);
}
