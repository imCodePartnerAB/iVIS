package com.imcode.services.jpa;

import com.imcode.entities.SchoolClass;
import com.imcode.repositories.SchoolClassRepository;
import com.imcode.services.AbstractNamedEntityService;
import com.imcode.services.AbstractService;
import com.imcode.services.SchoolClassService;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassServiceRepoImpl extends AbstractNamedEntityService<SchoolClass, Long, SchoolClassRepository> implements SchoolClassService {
}
