package imcode.services.restful;

import com.imcode.entities.Application;
import com.imcode.services.ApplicationService;
import imcode.services.IvisServiceFactory;

/**
 * Created by vitaly on 27.05.15.
 */
@Deprecated
public class OAuth2ApplicationService extends AbstractOAuth2Service<Application, Long> implements ApplicationService {
    public OAuth2ApplicationService() {
    }

    public OAuth2ApplicationService(IvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2ApplicationService(IvisServiceFactory factory) {
        super(factory);
    }
}
