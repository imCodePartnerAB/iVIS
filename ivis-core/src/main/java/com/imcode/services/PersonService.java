package com.imcode.services;

import com.imcode.entities.Person;

import java.util.List;

/**
 * Created by vitaly on 17.02.15.
 */
public interface PersonService extends GenericService<Person, Long>, PersonalizedService<Person>, SearchCriteriaService<Person> {
//    Person findByPersonalId(String personalId);

}
