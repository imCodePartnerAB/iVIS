package com.imcode.repositories.oauth2;

import com.imcode.entities.User;
import com.imcode.entities.oauth2.JpaAccessToken;
import com.imcode.entities.oauth2.JpaClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by vitaly on 13.05.15.
 */
public interface AccessTokenRepository extends JpaRepository<JpaAccessToken, String>{
}
