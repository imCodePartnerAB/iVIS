package imcode.services.restful;

import com.imcode.entities.Statement;
import com.imcode.services.StatementService;

/**
 * Created by vitaly on 27.05.15.
 */
@Deprecated
public class OAuth2StatementService extends AbstractOAuth2Service<Statement, Long> implements StatementService {
    public OAuth2StatementService() {
    }

    public OAuth2StatementService(DefaultIvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2StatementService(DefaultIvisServiceFactory factory) {
        super(factory);
    }
}
