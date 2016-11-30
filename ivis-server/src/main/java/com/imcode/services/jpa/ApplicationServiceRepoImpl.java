package com.imcode.services.jpa;

import com.imcode.entities.Application;
import com.imcode.entities.EntityVersion;
import com.imcode.repositories.ApplicationRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.ApplicationService;
import com.imcode.services.EntityVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceRepoImpl extends AbstractService<Application, Long, ApplicationRepository> implements ApplicationService {
//    @Autowired
//    private EntityVersionService entityVersionService;
//
//    @Override
//    public Application save(Application entity) {
//        if (entity.getId() != null) {
//            Application oldEntity = repo.findOne(entity.getId());
//            if (oldEntity != null && !oldEntity.deepEquals(entity)) {
//                EntityVersion version = new EntityVersion(oldEntity);
//                entityVersionService.saveAsync(version);
//            }
//        }
//
//        return super.save(entity);
//    }

//    private boolean deepEquals(Application oldEntity, Application entity) {
//        //a new one
//        if (entity.getId() == null) {
//            return true;
//        }
//
//        if (        (oldEntity.getDecision() == entity.getDecision() || (oldEntity.getDecision() != null && oldEntity.getDecision().equals(entity.getDecision())))
//                &&  (oldEntity.getRegardingUser() == entity.getRegardingUser() || (oldEntity.getRegardingUser() != null && oldEntity.getRegardingUser().equals(entity.getRegardingUser())))
//                &&  (oldEntity.getRegistrationNumber() == entity.getRegistrationNumber() || (oldEntity.getRegistrationNumber() != null && oldEntity.getRegistrationNumber().equals(entity.getRegistrationNumber())))
//                &&  (oldEntity.getHandledUser() == entity.getHandledUser() || (oldEntity.getHandledUser() != null && oldEntity.getHandledUser().equals(entity.getHandledUser())))
//                &&  (oldEntity.getSubmittedUser() == entity.getSubmittedUser() || (oldEntity.getSubmittedUser() != null && oldEntity.getSubmittedUser().equals(entity.getSubmittedUser())))
//                ) {
//            ApplicationForm oldForm = oldEntity.getApplicationForm();
//            ApplicationForm newForm = entity.getApplicationForm();
//            if (        (oldForm.getId() == newForm.getId() || (oldForm.getId() != null && oldForm.getId().equals(newForm.getId())))
//                    &&  (oldForm.getVersion() == newForm.getVersion() || (oldForm.getVersion() != null && oldForm.getVersion().equals(newForm.getVersion())))
//                    &&  (oldForm.getName() == newForm.getName() || (oldForm.getName() != null && oldForm.getName().equals(newForm.getName())))
//                    ) {
//                Set<ApplicationFormQuestion> oldQuestions = oldForm.getQuestions();
//                Set<ApplicationFormQuestion> newQuestions = newForm.getQuestions();
//                if (oldQuestions == newQuestions || (oldQuestions.size() == newQuestions.size())) {
//                    Set<ApplicationFormQuestion> questions = new HashSet<>(newQuestions);
//                    for (ApplicationFormQuestion oldQuestion : oldQuestions) {
//                        Iterator<ApplicationFormQuestion> iterator = questions.iterator();
//                        while (iterator.hasNext()) {
//                            ApplicationFormQuestion newQuestion = iterator.next();
//                            if (oldQuestion.equals(newQuestion) && EqualsBuilder.reflectionEquals(oldQuestion, newQuestion, false)) {
//                                iterator.remove();
//                            }
//                        }
//                    }
//                    return questions.size() == 0;
//                }
//
//            }
//        }
//
//
//        return false;
//    }

    public static void main(String[] args) {
        System.out.println();
    }
}
