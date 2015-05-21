package com.imcode.services.impl;

import com.imcode.entities.Role;
import com.imcode.repositories.RoleRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceRepoImpl extends AbstractService<Role, Long, RoleRepository> implements RoleService{
    @Override
    public Role findByName(String name) {
        return getRepo().findByName(name);
    }
}
