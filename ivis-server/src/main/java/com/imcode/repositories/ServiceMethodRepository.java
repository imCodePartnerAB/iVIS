package com.imcode.repositories;

import com.imcode.entities.ServiceMethod;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 01.08.16.
 */
public interface ServiceMethodRepository extends JpaRepository<ServiceMethod, Long> {
}
