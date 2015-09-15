package com.imcode.services.jpa;

import com.imcode.entities.SchoolClass;
import com.imcode.repositories.SchoolClassRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.SchoolClassService;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassServiceRepoImpl extends AbstractNamedService<SchoolClass, Long, SchoolClassRepository> implements SchoolClassService {
}
