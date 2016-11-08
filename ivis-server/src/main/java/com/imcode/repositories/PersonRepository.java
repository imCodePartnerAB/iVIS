package com.imcode.repositories;

import com.imcode.entities.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by vitaly on 13.05.15.
 */
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    Person findFirstByPersonalId(String personalId);
    List<Person> findByPersonalId(String personalId);
    List<Person> findByFirstNameContainsOrLastNameContainsAllIgnoreCase (String firstName, String lastName, Sort sort);
}
