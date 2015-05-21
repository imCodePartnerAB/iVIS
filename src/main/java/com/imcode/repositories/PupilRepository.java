package com.imcode.repositories;

import com.imcode.entities.Phone;
import com.imcode.entities.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vitaly on 13.05.15.
 */
public interface PupilRepository extends JpaRepository<Pupil, Long>{
}
