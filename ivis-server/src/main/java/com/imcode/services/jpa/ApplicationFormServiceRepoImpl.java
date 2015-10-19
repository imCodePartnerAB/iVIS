package com.imcode.services.jpa;

import com.imcode.entities.ApplicationForm;
import com.imcode.repositories.ApplicationFormRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.AbstractService;
import com.imcode.services.ApplicationFormService;
import org.springframework.stereotype.Service;

@Service
public class ApplicationFormServiceRepoImpl extends AbstractService<ApplicationForm, Long, ApplicationFormRepository> implements ApplicationFormService{ }
