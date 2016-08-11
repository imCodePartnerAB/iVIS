package com.imcode.services.jpa;

import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.repositories.MethodRestProviderForEntityRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.MethodRestProviderForEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruslan on 01.08.16.
 */
@Service
public class MethodRestProviderForEntityRepoImpl extends AbstractNamedService<MethodRestProviderForEntity,
        Long, MethodRestProviderForEntityRepository> implements MethodRestProviderForEntityService{

    @Override
    public void deleteAll() {
        getRepo().deleteAll();
    }

    @Override
    public List<MethodRestProviderForEntity> findAllowedMethodsByClientId(String id) {
        return getRepo().findByClientDetails_ClientId(id);
    }
}
