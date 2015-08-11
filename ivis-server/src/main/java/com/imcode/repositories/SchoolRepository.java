package com.imcode.repositories;


import com.imcode.entities.School;
import com.imcode.services.NamedEntityService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long>, NamedEntityService<School> {
//    List<School> findByName(String name);
}
