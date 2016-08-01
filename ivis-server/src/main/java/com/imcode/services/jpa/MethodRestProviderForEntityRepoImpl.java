package com.imcode.services.jpa;

import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.repositories.MethodRestProviderForEntityRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.MethodRestProviderForEntityService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 01.08.16.
 */
@Service
public class MethodRestProviderForEntityRepoImpl extends AbstractService<MethodRestProviderForEntity, Long, MethodRestProviderForEntityRepository> implements MethodRestProviderForEntityService{
}
