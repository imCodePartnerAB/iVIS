package com.imcode.repositories;

import com.imcode.entities.MethodRestProviderForEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

/**
 * Created by ruslan on 01.08.16.
 */
public interface MethodRestProviderForEntityRepository extends NamedRepository<MethodRestProviderForEntity>, JpaSpecificationExecutor<MethodRestProviderForEntity> {

    @Override
    @Query("select method from MethodRestProviderForEntity method order by method.entityRestProviderInformation.entityClass")
    List<MethodRestProviderForEntity> findAll();

    List<MethodRestProviderForEntity> findByClients_ClientId(String clientId);

    List<MethodRestProviderForEntity> findByUsers_Id(Long id);

    MethodRestProviderForEntity findByNameAndEntityRestProviderInformation_EntityClassAndClients_ClientIdAndUsers_Id(
            String name,
            String entityName,
            String clientId,
            Long id
    );

}
