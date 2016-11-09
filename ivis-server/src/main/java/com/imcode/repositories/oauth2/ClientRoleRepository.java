package com.imcode.repositories.oauth2;


import com.imcode.entities.Role;
import com.imcode.entities.oauth2.ClientRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClientRoleRepository extends JpaRepository<ClientRole, Long>, JpaSpecificationExecutor<ClientRole> {
    ClientRole findFirstByName(String name);
}
