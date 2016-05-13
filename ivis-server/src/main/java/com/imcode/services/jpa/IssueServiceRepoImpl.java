package com.imcode.services.jpa;

import com.imcode.entities.Issue;
import com.imcode.repositories.IssueRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.IssueService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruslan on 5/11/16.
 */
@Service
public class IssueServiceRepoImpl extends AbstractService<Issue, Long, IssueRepository> implements IssueService {
    @Override
    public List<Issue> findBySearchCriteria(String searchText, String orderBy) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, orderBy));
        return getRepo().findByTitleContainsAllIgnoreCase(searchText, sort);
    }
}
