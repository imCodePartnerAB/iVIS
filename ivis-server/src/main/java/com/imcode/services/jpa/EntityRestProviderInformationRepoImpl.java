package com.imcode.services.jpa;

import com.imcode.entities.EntityRestProviderInformation;
import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.repositories.EntityRestProviderInformationRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.EntityRestProviderInformationService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 01.08.16.
 */
@Service
public class EntityRestProviderInformationRepoImpl extends AbstractService<EntityRestProviderInformation, Long, EntityRestProviderInformationRepository> implements EntityRestProviderInformationService {

    @Override
    public void deleteAll() {
        getRepo().deleteAll();
    }

    @Override
    public EntityRestProviderInformation findByEntityClass(String entityClass) {
        return getRepo().findByEntityClass(entityClass);
    }

}