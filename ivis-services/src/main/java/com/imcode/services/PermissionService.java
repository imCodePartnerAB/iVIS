package com.imcode.services;

import com.imcode.entities.Permission;

import java.util.List;

/**
 * Created by ruslan on 15.11.16.
 */
public interface PermissionService extends GenericService<Permission, Long> {
    void makeAllUnUpdated();
    void setUpdated(Integer hash);
    List<Permission> getUnUpdated();
    void deleteAssociation(List<Permission> unUpdated);
    Boolean isPermitted(String clientId, Long userId, Integer hash);
}
