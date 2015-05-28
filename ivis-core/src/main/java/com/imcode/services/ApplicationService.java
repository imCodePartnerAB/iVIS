package com.imcode.services;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.List;

/**
 * Created by vitaly on 17.02.15.
 */
public interface ApplicationService extends GenericService<ClientDetails, String> {

    List<ClientDetails> findAllUserApplications(String UserId);

    BaseClientDetails findUserApplication(String id, String userId);
}
