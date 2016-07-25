package com.imcode.repositories;

import com.imcode.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 25.07.16.
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

}
