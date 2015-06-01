package imcode.services.restful;

import com.imcode.entities.School;
import com.imcode.services.SchoolService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vitaly on 27.05.15.
 */
public class OAuth2SchoolService extends AbstractOAuth2Service implements SchoolService {
    public OAuth2SchoolService() {
    }

    public OAuth2SchoolService(OAuth2ProtectedResourceDetails client, OAuth2AccessToken accessToken, String mainServiceAddres) {
        super(client, accessToken, mainServiceAddres);
    }

    public OAuth2SchoolService(IvisServiceFactory ivisServiceFactory) {
        super(ivisServiceFactory);
    }

    public School save(School entity) {
        return null;
    }

    public School find(Long aLong) {
        return null;
    }

    public boolean exist(Long aLong) {
        return false;
    }

    public void delete(Long aLong) {

    }

    public List<School> findAll() {
        List<School> result = Collections.emptyList();
        RestServiseRequest request = getFindAllRequest();
        String uri = request.getAddress();
        HttpMethod method = request.getMethod();

        RestTemplate restTemplate = getRestTemplate();

        ResponseEntity<School[]> responseEntity = restTemplate.exchange(uri, method, null, School[].class);

        if (responseEntity.getBody() != null) {
            result = Arrays.asList(responseEntity.getBody());
        }

        return result;
    }
}
