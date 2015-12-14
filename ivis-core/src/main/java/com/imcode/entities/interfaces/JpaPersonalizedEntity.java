package com.imcode.entities.interfaces;

import com.imcode.entities.Person;

import java.io.Serializable;

/**
 * Created by vitaly on 09.09.15.
 */
public interface JpaPersonalizedEntity extends JpaEntity<Long>{
    Person getPerson();

    void setPerson(Person person);

    String getPersonalId();

    void setPersonalId(String personalId);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

}
