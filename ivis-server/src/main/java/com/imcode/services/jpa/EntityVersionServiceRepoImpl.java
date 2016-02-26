package com.imcode.services.jpa;

import com.imcode.entities.EntityVersion;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.repositories.EntityVersionRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.EntityVersionService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityVersionServiceRepoImpl extends AbstractService<EntityVersion, Long, EntityVersionRepository> implements EntityVersionService{

    @Override
    @Async
    public void saveAsync(EntityVersion entity) {
        super.save(entity);
    }

    @Override
    public List<EntityVersion> findByEntity(JpaEntity<Long> entity) {
        return repo.findByEntityClassAndEntityId(entity.getClass(), entity.getId());
    }
}
