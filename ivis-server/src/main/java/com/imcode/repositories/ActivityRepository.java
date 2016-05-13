package com.imcode.repositories;

import com.imcode.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 5/12/16.
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
