package com.imcode.services.jdbc;

import com.imcode.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 27.03.15.
 */
@Service
public class JdbcApplicationServiceImpl implements ApplicationService {
    @Autowired
    JdbcClientDetailsService jdbcClientDetailsService;

    @Override
    public ClientDetails save(ClientDetails entity) {
        jdbcClientDetailsService.addClientDetails(entity);

        return entity;
    }

    @Override
    public BaseClientDetails find(String s) {

        return (BaseClientDetails) jdbcClientDetailsService.loadClientByClientId(s);
    }

//    @Override
    public boolean exist(String s) {
        return find(s) != null;
    }

    @Override
    public void delete(String s) {
        jdbcClientDetailsService.removeClientDetails(s);
    }

    @Override
    public List<ClientDetails> findAll() {
        return jdbcClientDetailsService.listClientDetails();
    }

    @Override
    public List<ClientDetails> findAllUserApplications(String userId) {
        List<ClientDetails> userApplications = new LinkedList<>();
        List<ClientDetails> allApplications = findAll();

        if (allApplications != null) {

            for (ClientDetails clientDetails : allApplications) {
//                ((BaseBaseClientDetails)clientDetails).addAdditionalInformation("user", userId);
//                jdbcClientDetailsService.removeBaseClientDetails(clientDetails.getClientId());
//                jdbcClientDetailsService.addBaseClientDetails(clientDetails);
                if (isUserApplication(clientDetails, userId)) {
                    userApplications.add(clientDetails);
                }
            }

        }

        return userApplications;
    }

    @Override
    public BaseClientDetails findUserApplication(String id, String userId) {
        BaseClientDetails result = null;
        BaseClientDetails clientDetails = (BaseClientDetails) jdbcClientDetailsService.loadClientByClientId(id);

        if (clientDetails != null && isUserApplication(clientDetails, userId)) {
            result = clientDetails;
        }

        return result;
    }

    private boolean isUserApplication(ClientDetails clientDetails, String userId) {
        Map<String, Object> additionalInfo = clientDetails.getAdditionalInformation();
        if (additionalInfo != null) {
            String ownerId = (String) additionalInfo.get("user");

            if (ownerId != null && userId.equalsIgnoreCase(ownerId)) {
                return true;
            }
        }

        return false;
    }
}
