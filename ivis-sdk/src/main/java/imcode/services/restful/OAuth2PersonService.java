package imcode.services.restful;

import com.imcode.entities.Person;
import com.imcode.services.PersonService;
import imcode.services.IvisServiceFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by vitaly on 03.06.15.
 */
@Deprecated
public class OAuth2PersonService extends AbstractOAuth2Service<Person, Long> implements PersonService{
    public OAuth2PersonService() {
    }

    public OAuth2PersonService(IvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2PersonService(IvisServiceFactory factory) {
        super(factory);
    }

    @Override
    public List<Person> findByPersonalId(String personalId) {
        throw new UnsupportedOperationException();
//        Person result = null;
//        RestTemplate restTemplate = getRestTemplate();
//        RestServiseRequest request = getFindAllRequest();
//        Object[] uriVariables = {personalId};
//
//        String uri = request.getAddress() + "?personalId={personalId}";
//        HttpMethod method = request.getMethod();
//
//        ResponseEntity<Person> responseEntity = restTemplate.exchange(uri, method, null, Person.class, uriVariables);
//
//        if (responseEntity.getBody() != null) {
//            result = responseEntity.getBody();
//        }
//
//        return result;
    }
}
