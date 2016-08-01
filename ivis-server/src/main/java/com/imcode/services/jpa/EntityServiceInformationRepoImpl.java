package com.imcode.services.jpa;

import com.imcode.entities.EntityServiceInformation;
import com.imcode.repositories.EntityServiceInformationRepository;
import com.imcode.services.AbstractService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 01.08.16.
 */
@Service
public class EntityServiceInformationRepoImpl extends AbstractService<EntityServiceInformation, Long, EntityServiceInformationRepository> {
}
