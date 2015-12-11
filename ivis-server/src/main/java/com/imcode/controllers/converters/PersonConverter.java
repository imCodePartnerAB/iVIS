package com.imcode.controllers.converters;

import com.imcode.entities.Person;
import com.imcode.services.PersonalizedService;

/**
 * Created by vitaly on 11.12.15.
 */
public class PersonConverter extends PersonalizedConverter<Person>{
    public PersonConverter(PersonalizedService<Person> entityService) {
        super(entityService);
    }
}
