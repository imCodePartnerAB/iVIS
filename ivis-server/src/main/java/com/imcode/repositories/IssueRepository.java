package com.imcode.repositories;

import com.imcode.entities.Issue;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by ruslan on 5/11/16.
 */
public interface IssueRepository extends JpaRepository<Issue, Long>, JpaSpecificationExecutor<Issue> {
    List<Issue> findByTitleContainsAllIgnoreCase (String title, Sort sort);
}
