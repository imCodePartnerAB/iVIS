package com.imcode.services.jpa;

import com.imcode.entities.Application;
import com.imcode.entities.ApplicationForm;
import com.imcode.entities.ApplicationFormQuestion;
import com.imcode.entities.EntityVersion;
import com.imcode.repositories.ApplicationFormQuestionRepository;
import com.imcode.repositories.ApplicationFormRepository;
import com.imcode.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationFormQuestionServiceRepoImpl extends AbstractService<ApplicationFormQuestion, Long, ApplicationFormQuestionRepository> implements ApplicationFormQuestionService {
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

