package imcode.services.restful;

import com.imcode.entities.Guardian;
import com.imcode.services.GuardianService;
import imcode.services.IvisServiceFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by vitaly on 03.06.15.
 */

public class OAuth2GuardianService extends AbstractOAuth2Service<Guardian, Long> implements GuardianService{
    public OAuth2GuardianService() {
    }

    public OAuth2GuardianService(IvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2GuardianService(IvisServiceFactory factory) {
        super(factory);
    }

    @Override
    public Guardian findByPersonalId(String personalId) {
        Guardian result = null;
        RestTemplate restTemplate = getRestTemplate();
        RestServiseRequest request = getFindAllRequest();
        Object[] uriVariables = {personalId};

        String uri = request.getAddress() + "?personalId={personalId}";
        HttpMethod method = request.getMethod();

        ResponseEntity<Guardian> responseEntity = restTemplate.exchange(uri, method, null, Guardian.class, uriVariables);

        if (responseEntity.getBody() != null) {
            result = responseEntity.getBody();
        }

        return result;
    }
}
