package com.imcode.services.jpa;

import com.imcode.entities.oauth2.AppTablePermission;
import com.imcode.repositories.oauth2.AppTablePermissionRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.AppTablePermissionService;
import org.springframework.stereotype.Service;

@Service
public class AppTablePermissionRepoImpl extends AbstractService<AppTablePermission, Long, AppTablePermissionRepository> implements AppTablePermissionService {
}
