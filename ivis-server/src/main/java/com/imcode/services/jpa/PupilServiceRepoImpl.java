package com.imcode.services.jpa;

import com.imcode.entities.Person;
import com.imcode.entities.Pupil;
import com.imcode.repositories.PersonRepository;
import com.imcode.repositories.PupilRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.PersonService;
import com.imcode.services.PupilService;
import org.springframework.stereotype.Service;

@Service
public class PupilServiceRepoImpl extends AbstractService<Pupil, Long, PupilRepository> implements PupilService {

    @Override
    public Pupil findByPersonalId(String personalId) {
        return getRepo().findByPersonalId(personalId);
    }
}
