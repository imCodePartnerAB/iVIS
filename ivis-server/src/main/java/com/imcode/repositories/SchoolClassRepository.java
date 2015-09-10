package com.imcode.repositories;


import com.imcode.entities.SchoolClass;
import com.imcode.services.NamedEntityService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long>, NamedEntityService<SchoolClass> {
}
