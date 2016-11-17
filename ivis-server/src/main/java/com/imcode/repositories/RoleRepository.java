package com.imcode.repositories;


import com.imcode.entities.Role;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>, NamedService<Role>, JpaSpecificationExecutor<Role> {

    List<Role> findByInternalFalse();
    List<Role> findByUserRoleFalse();
    List<Role> findByUserRoleTrue();

}
