package imcode.services.restful;

import com.imcode.entities.SchoolClass;
import com.imcode.services.SchoolClassService;
import imcode.services.IvisServiceFactory;
import org.springframework.core.ParameterizedTypeReference;

import java.util.LinkedList;

/**
 * Created by vitaly on 27.05.15.
 */
@Deprecated
public class OAuth2SchoolClassService extends AbstractOAuth2Service<SchoolClass, Long> implements SchoolClassService {
    public OAuth2SchoolClassService() {
    }

    public OAuth2SchoolClassService(IvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2SchoolClassService(IvisServiceFactory factory) {
        super(factory);
    }

    @Override
    protected ParameterizedTypeReference getListTypeReference() {
        return new ParameterizedTypeReference<LinkedList<SchoolClass>>() {};
    }
}
