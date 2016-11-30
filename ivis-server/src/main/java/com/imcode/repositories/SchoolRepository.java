package com.imcode.repositories;


import com.imcode.entities.School;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SchoolRepository extends JpaRepository<School, Long>, NamedService<School>, JpaSpecificationExecutor<School> {
//    List<School> findByName(String name);
}
