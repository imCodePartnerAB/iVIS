package com.imcode.services;

import com.imcode.entities.oauth2.ClientRole;

public interface ClientRoleService extends GenericService<ClientRole, Long> {
    ClientRole findByName(String name);

}
