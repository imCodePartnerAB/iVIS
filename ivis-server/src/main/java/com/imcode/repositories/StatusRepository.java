package com.imcode.repositories;

import com.imcode.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by ruslan on 5/10/16.
 */
public interface StatusRepository extends JpaRepository<Status, Long>, JpaSpecificationExecutor<Status> {
}
