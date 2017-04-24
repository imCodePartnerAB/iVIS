package com.imcode.services;

import com.imcode.entities.Person;
import com.imcode.entities.PersonRole;
import com.imcode.entities.School;

import java.util.List;

public interface PersonRoleService extends GenericService<PersonRole, Long> {

    List<PersonRole> findByPerson(Person person);

    List<PersonRole> findBySchool(School school);
}
