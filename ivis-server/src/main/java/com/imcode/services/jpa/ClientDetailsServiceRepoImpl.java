package com.imcode.services.jpa;

import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.entities.User;
import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.repositories.oauth2.ClietnDetailsRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Repository
public class ClientDetailsServiceRepoImpl implements IvisClientDetailsService {
    private static final Log logger = LogFactory.getLog(ClientDetailsServiceRepoImpl.class);

    @Autowired
    private ClietnDetailsRepository clietnDetailsRepository;

    @Override
    @Transactional(readOnly = true)
    public JpaClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        JpaClientDetails clientDetails = clietnDetailsRepository.findOne(clientId);

        if (clientDetails == null) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }

        return clientDetails;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {

        if (clientDetails.getClientId() != null && clietnDetailsRepository.exists(clientDetails.getClientId())) {
            throw new ClientAlreadyExistsException("Client already exists: " + clientDetails.getClientId());
        }

        clietnDetailsRepository.save((JpaClientDetails) clientDetails);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {

        if (clientDetails.getClientId() != null && !clietnDetailsRepository.exists(clientDetails.getClientId())) {
            throw new NoSuchClientException("No client found with id = " + clientDetails.getClientId());
        }

        clietnDetailsRepository.save((JpaClientDetails)clientDetails);
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        JpaClientDetails client = clietnDetailsRepository.findOne(clientId);

        if (client == null) {
            throw new NoSuchClientException("No client found with id = " + clientId);
        }

        client.setClientSecret(secret);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        if (clientId != null && !clietnDetailsRepository.exists(clientId)) {
            throw new NoSuchClientException("No client found with id = " + clientId);
        }

        clietnDetailsRepository.delete(clientId);
    }

    @Override
    @Transactional(readOnly = true)
//    @SuppressWarnings("unchecked")
    public List listClientDetails() {
        return clietnDetailsRepository.findAll();
    }

//    @SuppressWarnings("unchecked")
//    private static List<? extends ClientDetails> castToParent(List<? extends ClientDetails> clientDetails) {
////        List<ClientDetails> list = new ArrayList<>(clientDetails.size());
////        Collections.copy(list, clientDetails);
//        List<? extends ClientDetails> list1 = clientDetails;
//        return list1;
//    }

    @Override
    @Transactional(readOnly = true)
    public JpaClientDetails findUserClientById(String id, User owner) {
        return clietnDetailsRepository.findByOwnerAndClientId(owner, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JpaClientDetails> findAllUserClients(User owner) {
        return clietnDetailsRepository.findByOwner(owner);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JpaClientDetails> findAll() {
        return clietnDetailsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public JpaClientDetails findOne(String clientId) {
        return clietnDetailsRepository.findOne(clientId);
    }

    @Override
    public Boolean isMethodAllowed(String clientId, MethodRestProviderForEntity method) {
        return clietnDetailsRepository.isMethodAllowed(method, clientId);
    }
}
