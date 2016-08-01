package com.imcode.services.jpa;

import com.imcode.entities.EntityProviderMethod;
import com.imcode.repositories.EntityProviderMethodRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.EntityProviderMethodService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 01.08.16.
 */
@Service
public class EntityProviderMethodServiceRepoImpl extends AbstractService<EntityProviderMethod, Long, EntityProviderMethodRepository>
        implements EntityProviderMethodService {
}
