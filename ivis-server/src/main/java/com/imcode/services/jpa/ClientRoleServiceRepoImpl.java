package com.imcode.services.jpa;

import com.imcode.entities.oauth2.ClientRole;
import com.imcode.repositories.oauth2.ClientRoleRepository;
import com.imcode.services.AbstractNamedEntityService;
import com.imcode.services.AbstractService;
import com.imcode.services.ClientRoleService;
import org.springframework.stereotype.Service;

@Service
public class ClientRoleServiceRepoImpl extends AbstractNamedEntityService<ClientRole, Long, ClientRoleRepository> implements ClientRoleService{

//    @Override
//    public ClientRole findByName(String name) {
//        return getRepo().findByName(name);
//    }
}
