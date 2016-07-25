package com.imcode.repositories;

import com.imcode.entities.TypedAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 25.07.16.
 */
public interface TypedAccessTokenRepository extends JpaRepository<TypedAccessToken, Long> {
}
