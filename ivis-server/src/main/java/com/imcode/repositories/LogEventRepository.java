package com.imcode.repositories;


import com.imcode.entities.LogEvent;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogEventRepository extends JpaRepository<LogEvent, Long> {
    List<LogEvent> findByEntityClassNameAndEntityId(String entityClassName, Long entityId);
}
