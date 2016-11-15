package com.imcode.services;

import com.imcode.entities.Permission;

/**
 * Created by ruslan on 15.11.16.
 */
public interface PermissionService extends GenericService<Permission, Long> {
    void makeAllUnUpdated();
    void setUpdated(Integer hash);
}
