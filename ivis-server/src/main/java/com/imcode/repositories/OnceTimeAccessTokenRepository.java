package com.imcode.repositories;

import com.imcode.entities.OnceTimeAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 25.07.16.
 */
public interface OnceTimeAccessTokenRepository extends JpaRepository<OnceTimeAccessToken, Long> {
}
