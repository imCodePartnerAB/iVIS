package imcode.services.restful;

import com.imcode.entities.EntityVersion;
import com.imcode.entities.EntityVersion;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.services.EntityVersionService;
import com.imcode.services.EntityVersionService;
import imcode.services.IvisServiceFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vitaly on 03.06.15.
 */

public class OAuth2EntityVersionService extends AbstractOAuth2Service<EntityVersion, Long> implements EntityVersionService{
    public OAuth2EntityVersionService() {
    }

    public OAuth2EntityVersionService(IvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2EntityVersionService(IvisServiceFactory factory) {
        super(factory);
    }

    @Override
    public void saveAsync(EntityVersion entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EntityVersion> findByEntity(JpaEntity<Long> entity) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("entityClassName", entity.getClass().getName());
        params.put("entityId", entity.getId());
        List<EntityVersion> result = sendRequestList(getFindAllRequest(), params);

        return result;
    }
}
