package com.imcode.repositories;

import com.imcode.entities.EntityRestProviderInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ruslan on 01.08.16.
 */
public interface EntityRestProviderInformationRepository extends JpaRepository<EntityRestProviderInformation, Long>, JpaSpecificationExecutor<EntityRestProviderInformation> {

    @Override
    @Query("select info from EntityRestProviderInformation info order by info.entityClass")
    List<EntityRestProviderInformation> findAll();

    EntityRestProviderInformation findByEntityClass(String entityClass);

}
