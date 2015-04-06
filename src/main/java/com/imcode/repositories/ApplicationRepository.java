package com.imcode.repositories;


import com.imcode.entities._Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<_Application, Long> {
}
