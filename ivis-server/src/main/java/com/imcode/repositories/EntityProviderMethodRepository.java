package com.imcode.repositories;

import com.imcode.entities.MethodRestProviderForEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 01.08.16.
 */
public interface EntityProviderMethodRepository extends JpaRepository<MethodRestProviderForEntity, Long> {
}
