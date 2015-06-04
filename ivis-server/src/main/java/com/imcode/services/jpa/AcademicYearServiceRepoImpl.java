package com.imcode.services.jpa;

import com.imcode.entities.AcademicYear;
import com.imcode.repositories.AcademicYearRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.AcademicYearService;
import org.springframework.stereotype.Service;

@Service
public class AcademicYearServiceRepoImpl extends AbstractService<AcademicYear, Long, AcademicYearRepository> implements AcademicYearService {
}
