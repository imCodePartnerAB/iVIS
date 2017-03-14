package com.imcode.services.jpa;

import com.imcode.entities.WorkRole;
import com.imcode.repositories.WorkRoleRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.WorkRoleService;
import org.springframework.stereotype.Service;

@Service
public class WorkRoleServiceRepoImpl extends AbstractNamedService<WorkRole, Long, WorkRoleRepository> implements WorkRoleService {
}
