package com.imcode.repositories;

import com.imcode.entities.OnceTimeAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ruslan on 25.07.16.
 */
public interface OnceTimeAccessTokenRepository extends JpaRepository<OnceTimeAccessToken, Long> {

    @Query("select token from OnceTimeAccessToken token where token.used = true or token.expiryDate < CURRENT_DATE")
    List<OnceTimeAccessToken> selectExpiredOrUsedTokens();

}
