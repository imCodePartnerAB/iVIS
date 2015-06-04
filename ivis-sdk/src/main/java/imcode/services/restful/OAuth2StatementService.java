package imcode.services.restful;

import com.imcode.entities.Statement;
import com.imcode.services.StatementService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vitaly on 27.05.15.
 */
public class OAuth2StatementService extends AbstractOAuth2Service<Statement, Long> implements StatementService {
    public OAuth2StatementService() {
    }

    public OAuth2StatementService(IvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2StatementService(IvisServiceFactory factory) {
        super(factory);
    }

//    public Statement save(Statement entity) {
//        T result = null;
//        RestTemplate restTemplate = getRestTemplate();
//        RestServiseRequest request = null;
//        Object[] uriVariables = null;
//
//        if (entity.getId() == null) {
//            request = getCreateRequest();
//            String uri = request.getAddress();
//            HttpMethod method = request.getMethod();
//            Statement res = restTemplate.postForObject(uri, entity, Statement.class);
//        } else {
//            request = getUpdateRequest();
//            uriVariables = new Object[]{entity.getId()};
//            String uri = request.getAddress();
//            HttpMethod method = request.getMethod();
//            restTemplate.put(uri, entity, uriVariables);
//        }
//
//
//
////        ResponseEntity<Statement> responseEntity = restTemplate.exchange(uri, method, entity, Statement.class, uriVariables);
//
////        if (responseEntity.getBody() != null) {
////            result = responseEntity.getBody();
////        }
//
//        return result;
//    }

//    public Statement find(Long id) {
//        Statement result = null;
//        RestTemplate restTemplate = getRestTemplate();
//        RestServiseRequest request = null;
//
//           request = getFindRequest();
//        Object[] uriVariables = {id};
//
//        String uri = request.getAddress();
//        HttpMethod method = request.getMethod();
//
//        ResponseEntity<Statement> responseEntity = restTemplate.exchange(uri, method, null, Statement.class, uriVariables);
//
//        if (responseEntity.getBody() != null) {
//            result = responseEntity.getBody();
//        }
//
//        return result;
//    }

//    public boolean exist(Long id) {
//        boolean result = false;
//        RestTemplate restTemplate = getRestTemplate();
//        RestServiseRequest request = getExistsRequest();
//        String uri = request.getAddress();
//        HttpMethod method = request.getMethod();
//
//        Object[] uriVariables = {id};
//
//        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(uri, method, null, Boolean.class, uriVariables);
//
//        if (responseEntity.getBody() != null) {
//            result = responseEntity.getBody();
//        }
//        return result;
//    }

//    public void delete(Long id) {
//        RestTemplate restTemplate = getRestTemplate();
//        RestServiseRequest request = getDeleteRequest();
//        String uri = request.getAddress();
//        HttpMethod method = request.getMethod();
//
//        Object[] uriVariables = {id};
//
//        restTemplate.exchange(uri, method, null, void.class, uriVariables);
//    }

    @Override
    protected ParameterizedTypeReference getListTypeReference() {
        return new ParameterizedTypeReference<LinkedList<Statement>>() {
        };
    }

//    public List<Statement> findAll() {
//        List<Statement> result = Collections.emptyList();
//        RestServiseRequest request = getFindAllRequest();
//        String uri = request.getAddress();
//        HttpMethod method = request.getMethod();
//
//        RestTemplate restTemplate = getRestTemplate();
//
//        ResponseEntity<Statement[]> responseEntity = restTemplate.exchange(uri, method, null, Statement[].class);
//
//        if (responseEntity.getBody() != null) {
//            result = Arrays.asList(responseEntity.getBody());
//        }
//
//        return result;
//    }
}
