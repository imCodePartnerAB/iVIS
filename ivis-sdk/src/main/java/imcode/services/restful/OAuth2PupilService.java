package imcode.services.restful;

import com.imcode.entities.Pupil;
import com.imcode.services.PupilService;
import imcode.services.IvisServiceFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by vitaly on 03.06.15.
 */

public class OAuth2PupilService extends AbstractOAuth2Service<Pupil, Long> implements PupilService{
    public OAuth2PupilService() {
    }

    public OAuth2PupilService(IvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2PupilService(IvisServiceFactory factory) {
        super(factory);
    }

    @Override
    public Pupil findByPersonalId(String personalId) {
        Pupil result = null;
        RestTemplate restTemplate = getRestTemplate();
        RestServiseRequest request = getFindAllRequest();
        Object[] uriVariables = {personalId};

        String uri = request.getAddress() + "?personalId={personalId}";
        HttpMethod method = request.getMethod();

        ResponseEntity<Pupil> responseEntity = restTemplate.exchange(uri, method, null, Pupil.class, uriVariables);

        if (responseEntity.getBody() != null) {
            result = responseEntity.getBody();
        }

        return result;
    }
}
