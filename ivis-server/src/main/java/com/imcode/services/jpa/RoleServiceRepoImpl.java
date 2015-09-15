package com.imcode.services.jpa;

import com.imcode.entities.Role;
import com.imcode.repositories.RoleRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceRepoImpl extends AbstractNamedService<Role, Long, RoleRepository> implements RoleService{
//    @Override
//    public Role findFirstByName(String name) {
//        return getRepo().findByName(name);
//    }
}
