package com.imcode.repositories;

import com.imcode.entities.Incident;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by ruslan on 5/4/16.
 */
public interface IncidentRepository extends JpaRepository<Incident, Long>, JpaSpecificationExecutor<Incident> {
    List<Incident> findByTitleContainsAllIgnoreCase (String title, Sort sort);
}
