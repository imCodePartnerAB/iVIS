package com.imcode.services.jpa;

import com.imcode.entities.LogEvent;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.repositories.LogEventRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.LogEventService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogEventServiceRepoImpl extends AbstractService<LogEvent, Long, LogEventRepository> implements LogEventService{

    @Override
    @Async
    public void saveAsync(LogEvent entity) {
        save(entity);
    }

    @Override
    public List<LogEvent> findByEntity(JpaEntity<Long> entity) {
        return repo.findByEntityClassNameAndEntityId(entity.getClass().getName(), entity.getId());
    }
}
