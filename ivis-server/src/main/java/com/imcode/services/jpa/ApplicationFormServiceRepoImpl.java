package com.imcode.services.jpa;

import com.imcode.entities.Application;
import com.imcode.entities.ApplicationForm;
import com.imcode.entities.EntityVersion;
import com.imcode.repositories.ApplicationFormRepository;
import com.imcode.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationFormServiceRepoImpl extends AbstractService<ApplicationForm, Long, ApplicationFormRepository> implements ApplicationFormService {
//    @Autowired
//    private EntityVersionService entityVersionService;

//    @Autowired
//    private ApplicationService applicationService;
//
//    @Override
//    public ApplicationForm save(ApplicationForm entity) {
//        if (entity.getId() != null) {
//            ApplicationForm oldEntity = repo.findOne(entity.getId());
//            if (oldEntity != null && !oldEntity.deepEquals(entity)) {
//                for (Application application : entity.getApplications()) {
//                    EntityVersion version = new EntityVersion(application);
//                    entityVersionService.saveAsync(version);
//                }
//            }
//        }
//
//        return super.save(entity);
//    }
}

