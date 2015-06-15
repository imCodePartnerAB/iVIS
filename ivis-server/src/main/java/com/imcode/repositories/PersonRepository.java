package com.imcode.repositories;

import com.imcode.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vitaly on 13.05.15.
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    Person findByPersonalId(String personalId);
}
