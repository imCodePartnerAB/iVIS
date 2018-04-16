package com.imcode.services.jpa;

import com.imcode.entities.SchoolClass;
import com.imcode.repositories.SchoolClassRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.SchoolClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SchoolClassServiceRepoImpl extends AbstractNamedService<SchoolClass, Long, SchoolClassRepository> implements SchoolClassService {

    @Override
    public void setSchoolCloudEnabling(SchoolClass persistEntity, boolean nextCloudEnabled) {
        getRepo().updateSchoolCloudEnabling(persistEntity.getId(), nextCloudEnabled);
    }
}
