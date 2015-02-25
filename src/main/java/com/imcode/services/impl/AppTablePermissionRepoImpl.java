package com.imcode.services.impl;

import com.imcode.entities.AppTableKey;
import com.imcode.entities.AppTablePermission;
import com.imcode.entities.User;
import com.imcode.repositories.AppTablePermissionRepository;
import com.imcode.repositories.UserRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.AppTablePermissionService;
import com.imcode.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class AppTablePermissionRepoImpl extends AbstractService<AppTablePermission, Long, AppTablePermissionRepository> implements AppTablePermissionService {
}
