package com.imcode.services.jpa;

import com.imcode.entities.School;
import com.imcode.repositories.SchoolRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.SchoolService;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceRepoImpl extends AbstractNamedService<School, Long, SchoolRepository> implements SchoolService {
}
