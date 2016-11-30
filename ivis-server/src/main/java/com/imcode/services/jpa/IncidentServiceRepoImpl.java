package com.imcode.services.jpa;

import com.imcode.entities.Incident;
import com.imcode.repositories.IncidentRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.IncidentService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruslan on 5/4/16.
 */
@Service
public class IncidentServiceRepoImpl extends AbstractService<Incident, Long, IncidentRepository> implements IncidentService{
    @Override
    public List<Incident> findBySearchCriteria(String searchText, String orderBy) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, orderBy));
        return getRepo().findByTitleContainsAllIgnoreCase(searchText, sort);
    }
}
