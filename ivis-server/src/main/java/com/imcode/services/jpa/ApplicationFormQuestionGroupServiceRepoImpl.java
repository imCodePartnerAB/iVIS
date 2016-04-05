package com.imcode.services.jpa;

import com.imcode.entities.ApplicationFormQuestionGroup;
import com.imcode.repositories.ApplicationFormQuestionGroupRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.ApplicationFormQuestionGroupService;
import com.imcode.services.ApplicationService;
import com.imcode.services.EntityVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationFormQuestionGroupServiceRepoImpl extends AbstractService<ApplicationFormQuestionGroup, Long, ApplicationFormQuestionGroupRepository> implements ApplicationFormQuestionGroupService {
//    @Autowired
//    private EntityVersionService entityVersionService;

    @Autowired
    private ApplicationService applicationService;

//    @Override
//    public ApplicationForm save(ApplicationForm entity) {
//        ApplicationForm oldEntity = repo.findOne(entity.getId());
//        if (!oldEntity.deepEquals(entity)) {
//            for (Application application :entity.getApplications()) {
//                EntityVersion version = new EntityVersion(application);
//                entityVersionService.saveAsync(version);
//            }
//        }
//
//        return super.save(entity);
//    }
}

