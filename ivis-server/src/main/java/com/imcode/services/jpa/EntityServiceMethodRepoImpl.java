package com.imcode.services.jpa;

import com.imcode.entities.EntityServiceMethod;
import com.imcode.repositories.EntityServiceMethodRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.EntityServiceMethodService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 01.08.16.
 */
@Service
public class EntityServiceMethodRepoImpl extends AbstractService<EntityServiceMethod, Long, EntityServiceMethodRepository> implements EntityServiceMethodService{
}
