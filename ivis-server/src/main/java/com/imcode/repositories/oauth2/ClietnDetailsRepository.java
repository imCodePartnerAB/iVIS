package com.imcode.repositories.oauth2;

import com.imcode.entities.User;
import com.imcode.entities.oauth2.JpaClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by vitaly on 13.05.15.
 */
public interface ClietnDetailsRepository extends JpaRepository<JpaClientDetails, String>{
    JpaClientDetails findByOwnerAndClientId(User owner, String clientId);

    List<JpaClientDetails> findByOwner(User owner);

}
