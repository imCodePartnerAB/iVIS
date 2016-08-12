package com.imcode.services;

import com.imcode.entities.MethodRestProviderForEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

/**
 * Created by ruslan on 01.08.16.
 */
public interface MethodRestProviderForEntityService extends GenericService<MethodRestProviderForEntity, Long>, NamedService<MethodRestProviderForEntity>   {

    void deleteAll();

    List<MethodRestProviderForEntity> findAllowedMethodsByClientId(String id);

    List<MethodRestProviderForEntity> findAllowedMethodsByUserId(Long id);

    Set<MethodRestProviderForEntity> findAllowedMethods(
            String url,
            RequestMethod requestMethod,
            String clientId,
            Long id
    );

}
