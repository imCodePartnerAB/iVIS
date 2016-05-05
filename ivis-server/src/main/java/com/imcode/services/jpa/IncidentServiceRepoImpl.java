package com.imcode.services.jpa;

import com.imcode.entities.Incident;
import com.imcode.repositories.IncidentRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.IncidentService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 5/4/16.
 */
@Service
public class IncidentServiceRepoImpl extends AbstractService<Incident, Long, IncidentRepository> implements IncidentService{
}
