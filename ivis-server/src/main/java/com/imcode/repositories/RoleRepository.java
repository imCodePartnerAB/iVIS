package com.imcode.repositories;


import com.imcode.entities.Role;
import com.imcode.services.NamedEntityService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>, NamedEntityService<Role> {
//    Role findByName(String name);
}
