package com.imcode.services.impl;

import com.imcode.entities.SchoolClass;
import com.imcode.repositories.SchoolClassRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.SchoolClassService;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassServiceRepoImpl extends AbstractService<SchoolClass, Long, SchoolClassRepository> implements SchoolClassService {
}
