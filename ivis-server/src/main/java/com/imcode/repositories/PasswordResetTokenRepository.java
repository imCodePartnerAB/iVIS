package com.imcode.repositories;

import com.imcode.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 22.07.16.
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
}
