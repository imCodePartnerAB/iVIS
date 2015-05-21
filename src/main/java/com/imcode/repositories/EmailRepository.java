package com.imcode.repositories;

import com.imcode.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vitaly on 13.05.15.
 */
public interface EmailRepository extends JpaRepository<Email, Long>{
}
