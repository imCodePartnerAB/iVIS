package com.imcode.services.jpa;

import com.imcode.entities.Guardian;
import com.imcode.repositories.GuardianRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.GuardianService;
import com.imcode.services.PersonalizedService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardianServiceRepoImpl extends AbstractService<Guardian, Long, GuardianRepository> implements GuardianService , PersonalizedService<Guardian>{

    @Override
    public Guardian findFirstByPersonalId(String personalId) {
        return getRepo().findFirstByPersonalId(personalId);
    }

    @Override
    public List<Guardian> findByPersonalId(String personalId) {
        return getRepo().findByPersonalId(personalId);
    }
}
