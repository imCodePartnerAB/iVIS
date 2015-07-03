package com.imcode.services.jpa;

import com.imcode.entities.AfterSchoolCenterSection;
import com.imcode.repositories.AfterSchoolCenterSectionRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.AfterSchoolCenterSectionService;
import org.springframework.stereotype.Service;

@Service
public class AfterSchoolCenterSectionServiceRepoImpl extends AbstractService<AfterSchoolCenterSection, Long, AfterSchoolCenterSectionRepository> implements AfterSchoolCenterSectionService {
}
