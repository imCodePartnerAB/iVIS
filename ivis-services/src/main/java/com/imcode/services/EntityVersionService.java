package com.imcode.services;

import com.imcode.entities.EntityVersion;
import com.imcode.entities.interfaces.JpaEntity;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by vitaly on 17.02.15.
 */
public interface EntityVersionService extends GenericService<EntityVersion, Long> {
    @Async
    void saveAsync(EntityVersion entity);

    List<EntityVersion> findByEntity(JpaEntity<Long> entity);
}
