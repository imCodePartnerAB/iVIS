package com.imcode.services.jpa;

import com.imcode.entities.Permission;
import com.imcode.repositories.PermissionRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ruslan on 15.11.16.
 */
@Service
public class PermissionServiceRepoImpl extends AbstractService<Permission, Long, PermissionRepository> implements PermissionService {

    @Override
    @Transactional
    public void makeAllUnUpdated() {
        getRepo().makeAllUnUpdated();
    }

    @Override
    @Transactional
    public void setUpdated(Integer hash) {
        getRepo().setUpdated(hash);
    }
}
