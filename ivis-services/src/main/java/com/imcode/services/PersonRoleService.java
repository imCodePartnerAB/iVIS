package com.imcode.services;

import com.imcode.entities.Person;
import com.imcode.entities.PersonRole;

import java.util.List;

public interface PersonRoleService extends GenericService<PersonRole, Long> {

    List<PersonRole> findByPerson(Person person);

}
