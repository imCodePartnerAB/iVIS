package com.imcode.services.jpa;

import com.imcode.entities.ApplicationFormStep;
import com.imcode.repositories.ApplicationFormStepRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.ApplicationFormStepService;
import com.imcode.services.ApplicationService;
import com.imcode.services.EntityVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationFormStepServiceRepoImpl extends AbstractService<ApplicationFormStep, Long, ApplicationFormStepRepository> implements ApplicationFormStepService {
//    @Autowired
//    private EntityVersionService entityVersionService;

//    @Autowired
//    private ApplicationService applicationService;

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

