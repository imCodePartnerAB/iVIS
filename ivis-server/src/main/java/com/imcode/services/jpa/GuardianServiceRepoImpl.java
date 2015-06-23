package com.imcode.services.jpa;

import com.imcode.entities.Guardian;
import com.imcode.repositories.GuardianRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.GuardianService;
import org.springframework.stereotype.Service;

@Service
public class GuardianServiceRepoImpl extends AbstractService<Guardian, Long, GuardianRepository> implements GuardianService {

    @Override
    public Guardian findByPersonalId(String personalId) {
        return getRepo().findByPersonalId(personalId);
    }
}
