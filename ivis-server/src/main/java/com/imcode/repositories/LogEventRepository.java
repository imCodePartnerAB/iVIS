package com.imcode.repositories;


import com.imcode.entities.LogEvent;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LogEventRepository extends JpaRepository<LogEvent, Long>, JpaSpecificationExecutor<LogEvent> {
    List<LogEvent> findByEntityClassNameAndEntityId(String entityClassName, Long entityId);
}
