package com.imcode.repositories;


import com.imcode.entities.ApplicationFormStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationFormStepRepository extends JpaRepository<ApplicationFormStep, Long>, JpaSpecificationExecutor<ApplicationFormStep> {
}
