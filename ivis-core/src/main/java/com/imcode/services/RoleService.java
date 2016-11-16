package com.imcode.services;

import com.imcode.entities.Role;

import java.util.List;

public interface RoleService extends GenericService<Role, Long>, NamedService<Role> {

    List<Role> findAllNonInternal();

}
