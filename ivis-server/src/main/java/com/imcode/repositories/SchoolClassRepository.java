package com.imcode.repositories;


import com.imcode.entities.SchoolClass;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long>, NamedService<SchoolClass>, JpaSpecificationExecutor<SchoolClass> {
}
