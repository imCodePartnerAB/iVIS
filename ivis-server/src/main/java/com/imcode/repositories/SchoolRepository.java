package com.imcode.repositories;


import com.imcode.entities.School;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long>, NamedService<School> {
//    List<School> findByName(String name);
}
