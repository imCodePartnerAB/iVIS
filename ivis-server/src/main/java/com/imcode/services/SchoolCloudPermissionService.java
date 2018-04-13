package com.imcode.services;

import com.imcode.entities.User;

public interface SchoolCloudPermissionService {

    boolean hasUserApprovedAccess(User user);
}
