package com.imcode.repositories;


import com.imcode.entities.Application;
import com.imcode.entities.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
