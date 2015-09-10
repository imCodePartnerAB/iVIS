package com.imcode.services.jpa;

import com.imcode.entities.Role;
import com.imcode.repositories.RoleRepository;
import com.imcode.services.AbstractNamedEntityService;
import com.imcode.services.AbstractService;
import com.imcode.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceRepoImpl extends AbstractNamedEntityService<Role, Long, RoleRepository> implements RoleService{
//    @Override
//    public Role findFirstByName(String name) {
//        return getRepo().findByName(name);
//    }
}
