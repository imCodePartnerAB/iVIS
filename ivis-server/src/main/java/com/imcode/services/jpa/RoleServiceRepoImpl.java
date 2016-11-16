package com.imcode.services.jpa;

import com.imcode.entities.Role;
import com.imcode.repositories.RoleRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceRepoImpl extends AbstractNamedService<Role, Long, RoleRepository> implements RoleService {
    @Override
    public List<Role> findAllNonInternal() {
        return getRepo().findByInternalFalse();
    }
}
