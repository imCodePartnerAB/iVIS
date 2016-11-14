package com.imcode.repositories.oauth2;

import com.imcode.entities.User;
import com.imcode.entities.enums.ApiEntities;
import com.imcode.entities.enums.HttpMethod;
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

    @Query("SELECT CASE WHEN COUNT(c.clientId) > 0 THEN true ELSE false END " +
            "FROM JpaClientDetails c, User u " +
            "WHERE :clientId = c.clientId AND " +
            ":method member c.allowedHttpMethods AND " +
            ":entity member c.allowedEntities AND " +
            ":userId = u.id AND " +
            ":method member u.allowedHttpMethods AND " +
            ":entity member u.allowedEntities")
    boolean isMethodAllowed(@Param("clientId") String clientId, @Param("userId") Long userId
            , @Param("entity") ApiEntities entity, @Param("method") HttpMethod method);

}
