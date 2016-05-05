package com.imcode.repositories;

import com.imcode.entities.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 5/4/16.
 */
public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
