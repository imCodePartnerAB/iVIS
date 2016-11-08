package com.imcode.repositories;


import com.imcode.entities.ApplicationForm;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long>, JpaSpecificationExecutor<ApplicationForm> {
}
