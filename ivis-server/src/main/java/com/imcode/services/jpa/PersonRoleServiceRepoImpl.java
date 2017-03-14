package com.imcode.services.jpa;

import com.imcode.entities.PersonRole;
import com.imcode.repositories.PersonRoleRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.PersonRoleService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 14.03.17.
 */
@Service
public class PersonRoleServiceRepoImpl extends AbstractService<PersonRole, Long, PersonRoleRepository> implements PersonRoleService {
}
