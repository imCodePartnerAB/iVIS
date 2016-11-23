package com.imcode.services;

import com.imcode.entities.LogEvent;
import com.imcode.entities.interfaces.JpaEntity;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by vitaly on 17.02.15.
 */
public interface LogEventService extends GenericService<LogEvent, Long> {
    @Async
    void saveAsync(LogEvent entity);

    List<LogEvent> findByEntity(JpaEntity<Long> entity);
}
