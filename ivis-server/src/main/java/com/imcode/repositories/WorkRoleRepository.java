package com.imcode.repositories;

import com.imcode.entities.WorkRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkRoleRepository extends JpaRepository<WorkRole, Long>, JpaSpecificationExecutor<WorkRole> {
}
