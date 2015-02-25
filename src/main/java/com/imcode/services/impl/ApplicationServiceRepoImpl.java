package com.imcode.services.impl;

import com.imcode.entities.Application;
import com.imcode.entities.School;
import com.imcode.repositories.ApplicationRepository;
import com.imcode.repositories.SchoolRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.ApplicationService;
import com.imcode.services.SchoolService;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceRepoImpl extends AbstractService<Application, Long, ApplicationRepository> implements ApplicationService {
}
