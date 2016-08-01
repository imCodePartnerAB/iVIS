package com.imcode.services.jpa;

import com.imcode.entities.EntityRestProviderInformation;
import com.imcode.repositories.EntityProviderInformationRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.EntityProviderInformationService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 01.08.16.
 */
@Service
public class EntityProviderInformationServiceRepoImpl extends AbstractService<EntityRestProviderInformation, Long, EntityProviderInformationRepository>
implements EntityProviderInformationService {
}
