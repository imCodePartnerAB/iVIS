package com.imcode.repositories;


import com.imcode.entities.EntityVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EntityVersionRepository extends JpaRepository<EntityVersion, Long>, JpaSpecificationExecutor<EntityVersion> {
    List<EntityVersion> findByEntityClassAndEntityId(Class<?> entityClass, Long entityId);
}
