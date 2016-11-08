package com.imcode.repositories;

import com.imcode.entities.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by ruslan on 5/10/16.
 */
public interface PriorityRepository extends JpaRepository<Priority, Long>, JpaSpecificationExecutor<Priority> {
}
