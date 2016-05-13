package com.imcode.repositories;

import com.imcode.entities.Issue;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ruslan on 5/11/16.
 */
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByTitleContainsAllIgnoreCase (String title, Sort sort);
}
