package com.imcode.services.jpa;

import com.imcode.entities.Person;
import com.imcode.entities.Pupil;
import com.imcode.repositories.PupilRepository;
import com.imcode.services.AbstractPersonalizedEntityService;
import com.imcode.services.PupilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PupilServiceRepoImpl extends AbstractPersonalizedEntityService<Pupil, PupilRepository> implements PupilService {

    @Override
    @Transactional(readOnly = true)
    public Pupil getPupilByPerson(final Person person) {
        return getRepo().findPupilByPersonId(person.getId());
    }
}
