package com.imcode.repositories;

import com.imcode.entities.SchemaVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by ruslan on 09.06.16.
 */
public interface SchemaVersionRepository extends JpaRepository<SchemaVersion, Long>, JpaSpecificationExecutor<SchemaVersion> {
    SchemaVersion findByCurrentTrue();

    SchemaVersion findByVersion(String version);
}
