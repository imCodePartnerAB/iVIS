package com.imcode.oauth2;

import com.imcode.entities.User;
import com.imcode.entities.oauth2.JpaClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;

import java.util.List;

/**
 * Created by vitaly on 15.05.15.
 */
public interface IvisClientDetailsService extends ClientDetailsService, ClientRegistrationService {
    JpaClientDetails findUserClientById(String id, User owner);

    List<JpaClientDetails> findAllUserClients(User owner);

    List<JpaClientDetails> findAll();

    JpaClientDetails findOne(String clientId);

//    JpaClientDetails save(JpaClientDetails clientDetails);


}
