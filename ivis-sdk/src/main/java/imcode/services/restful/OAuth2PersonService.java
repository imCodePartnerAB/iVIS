package imcode.services.restful;

import com.imcode.entities.Person;
import com.imcode.services.PersonService;
import imcode.services.IvisServiceFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by vitaly on 03.06.15.
 */

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
    public Person findByPersonalId(String personalId) {
        Person result = null;
        RestTemplate restTemplate = getRestTemplate();
        RestServiseRequest request = getFindAllRequest();
        Object[] uriVariables = {personalId};

        String uri = request.getAddress() + "?personalId={personalId}";
        HttpMethod method = request.getMethod();

        ResponseEntity<Person> responseEntity = restTemplate.exchange(uri, method, null, Person.class, uriVariables);

        if (responseEntity.getBody() != null) {
            result = responseEntity.getBody();
        }

        return result;
    }
}
