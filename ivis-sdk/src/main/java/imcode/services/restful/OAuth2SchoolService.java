package imcode.services.restful;

import com.imcode.entities.School;
import com.imcode.services.SchoolService;
import org.springframework.core.ParameterizedTypeReference;

import java.util.LinkedList;

/**
 * Created by vitaly on 27.05.15.
 */
@Deprecated
public class OAuth2SchoolService extends AbstractOAuth2Service<School, Long> implements SchoolService {
    public OAuth2SchoolService() {
    }

    public OAuth2SchoolService(DefaultIvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2SchoolService(DefaultIvisServiceFactory factory) {
        super(factory);
    }

    @Override
    protected ParameterizedTypeReference getListTypeReference() {
        return new ParameterizedTypeReference<LinkedList<School>>() {};
    }
}
