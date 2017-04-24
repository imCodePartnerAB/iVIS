package com.imcode.services.jpa;

import com.imcode.entities.Person;
import com.imcode.entities.PersonRole;
import com.imcode.entities.School;
import com.imcode.repositories.PersonRoleRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.PersonRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruslan on 14.03.17.
 */
@Service
public class PersonRoleServiceRepoImpl extends AbstractService<PersonRole, Long, PersonRoleRepository> implements PersonRoleService {
    @Override
    public List<PersonRole> findByPerson(Person person) {
        return getRepo().findByPerson(person);
    }

    @Override
    public List<PersonRole> findBySchool(School school) {
        return getRepo().findBySchool(school);
    }
}
