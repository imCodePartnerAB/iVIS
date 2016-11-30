package com.imcode.repositories;

import com.imcode.entities.AcademicYear;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by vitaly on 13.05.15.
 */
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long>, NamedService<AcademicYear>, JpaSpecificationExecutor<AcademicYear> {
}
