package com.imcode.repositories;

import com.imcode.entities.MethodRestProviderForEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ruslan on 01.08.16.
 */
public interface MethodRestProviderForEntityRepository extends NamedRepository<MethodRestProviderForEntity> {

    @Modifying(clearAutomatically = true)
    @Query(value = "TRUNCATE TABLE dbo_user_allowed_methods", nativeQuery = true
    )
    void deleteRelationsWithUser();

    @Modifying(clearAutomatically = true)
    @Query(value = "TRUNCATE TABLE dbo_client_allowed_methods", nativeQuery = true
    )
    void deleteRelationsWithClient();

    @Override
    @Query("select method from MethodRestProviderForEntity method order by method.entityRestProviderInformation.entityClass")
    List<MethodRestProviderForEntity> findAll();

}
