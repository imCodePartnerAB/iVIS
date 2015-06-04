package imcode.services.restful;

import com.imcode.entities.School;
import com.imcode.entities.Statement;
import com.imcode.services.SchoolService;
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
public class OAuth2SchoolService extends AbstractOAuth2Service<School, Long> implements SchoolService {
    public OAuth2SchoolService() {
    }

    public OAuth2SchoolService(IvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2SchoolService(IvisServiceFactory factory) {
        super(factory);
    }

    @Override
    protected ParameterizedTypeReference getListTypeReference() {
        return new ParameterizedTypeReference<LinkedList<School>>() {};
    }
}
