package com.imcode.repositories;

import com.imcode.entities.AcademicYear;
import com.imcode.entities.Pupil;
import com.imcode.services.NamedEntityService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vitaly on 13.05.15.
 */
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long>, NamedEntityService<AcademicYear> {
}
