package com.imcode.repositories;

import com.imcode.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vitaly on 28.05.15.
 */
public interface ApplicationRepository extends JpaRepository<Application, Long>{
}
