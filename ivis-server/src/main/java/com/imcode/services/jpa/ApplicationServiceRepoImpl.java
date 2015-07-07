package com.imcode.services.jpa;

import com.imcode.entities.Application;
import com.imcode.repositories.ApplicationRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.ApplicationService;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceRepoImpl extends AbstractService<Application, Long, ApplicationRepository> implements ApplicationService {
}
