package com.imcode.services.impl;

import com.imcode.entities.Person;
import com.imcode.repositories.PersonRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceRepoImpl extends AbstractService<Person, Long, PersonRepository> implements PersonService {
}
