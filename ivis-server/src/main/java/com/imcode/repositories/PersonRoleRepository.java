package com.imcode.repositories;

import com.imcode.entities.Person;
import com.imcode.entities.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PersonRoleRepository extends JpaRepository<PersonRole, Long>, JpaSpecificationExecutor<PersonRole> {

    List<PersonRole> findByPerson(Person person);

}
