package com.imcode.repositories;


import com.imcode.entities.ApplicationForm;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long> {
}
