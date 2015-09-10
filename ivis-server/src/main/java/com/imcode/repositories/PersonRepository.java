package com.imcode.repositories;

import com.imcode.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by vitaly on 13.05.15.
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    Person findFirstByPersonalId(String personalId);
    List<Person> findByPersonalId(String personalId);
}
