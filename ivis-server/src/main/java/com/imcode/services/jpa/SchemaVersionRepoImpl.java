package com.imcode.services.jpa;

import com.imcode.entities.SchemaVersion;
import com.imcode.repositories.SchemaVersionRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.SchemaVersionService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 09.06.16.
 */
@Service
public class SchemaVersionRepoImpl extends AbstractService<SchemaVersion, Long, SchemaVersionRepository> implements SchemaVersionService {
    @Override
    public SchemaVersion findCurrentVersion() {
        return getRepo().findByCurrentTrue();
    }

    @Override
    public SchemaVersion findVersion(String version) {
        return getRepo().findByVersion(version);
    }
}
