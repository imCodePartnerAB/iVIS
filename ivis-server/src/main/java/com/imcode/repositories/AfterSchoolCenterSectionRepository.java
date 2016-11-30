package com.imcode.repositories;

import com.imcode.entities.AfterSchoolCenterSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by vitaly on 13.05.15.
 */
public interface AfterSchoolCenterSectionRepository extends JpaRepository<AfterSchoolCenterSection, Long>, JpaSpecificationExecutor<AfterSchoolCenterSection> {
}
