package com.imcode.repositories;

import com.imcode.entities.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRoleRepository extends JpaRepository<PersonRole, Long>, JpaSpecificationExecutor<PersonRole> {
}
