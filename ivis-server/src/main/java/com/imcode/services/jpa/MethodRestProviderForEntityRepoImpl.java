package com.imcode.services.jpa;

import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.repositories.MethodRestProviderForEntityRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.MethodRestProviderForEntityService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

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
        return getRepo().findByClients_ClientId(id);
    }

    @Override
    public List<MethodRestProviderForEntity> findAllowedMethodsByUserId(Long id) {
        return getRepo().findByUsers_Id(id);
    }

    @Override
    public Set<MethodRestProviderForEntity> findAllowedMethods(String url, RequestMethod requestMethod, String clientId, Long id) {
        return getRepo().findByUrlAndRequestMethodAndClients_ClientIdAndUsers_Id(url, requestMethod, clientId, id);
    }
}
