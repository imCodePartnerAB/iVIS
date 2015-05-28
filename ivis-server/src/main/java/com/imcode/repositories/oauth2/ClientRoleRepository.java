package com.imcode.repositories.oauth2;


import com.imcode.entities.Role;
import com.imcode.entities.oauth2.ClientRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRoleRepository extends JpaRepository<ClientRole, Long> {
    ClientRole findByName(String name);
}
