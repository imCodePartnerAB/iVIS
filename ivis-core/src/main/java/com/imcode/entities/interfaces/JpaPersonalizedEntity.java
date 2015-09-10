package com.imcode.entities.interfaces;

import com.imcode.entities.Person;

import java.io.Serializable;

/**
 * Created by vitaly on 09.09.15.
 */
public interface JpaPersonalizedEntity<ID extends Serializable> extends JpaEntity<ID>{
    Person getPerson();
    void setPerson(Person person);
}
