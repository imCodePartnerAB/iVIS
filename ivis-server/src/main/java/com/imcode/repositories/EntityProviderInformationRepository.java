package com.imcode.repositories;

import com.imcode.entities.EntityRestProviderInformation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 01.08.16.
 */
public interface EntityProviderInformationRepository extends JpaRepository<EntityRestProviderInformation, Long> {
}
