package com.imcode.services.jpa;

import com.imcode.entities.EntityRestProviderInformation;
import com.imcode.repositories.EntityRestProviderInformationRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.EntityRestProviderInformationService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 01.08.16.
 */
@Service
public class EntityRestProviderInformationRepoImpl extends AbstractService<EntityRestProviderInformation, Long, EntityRestProviderInformationRepository> implements EntityRestProviderInformationService {
}
