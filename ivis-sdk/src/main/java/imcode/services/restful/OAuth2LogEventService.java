package imcode.services.restful;

import com.imcode.entities.LogEvent;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.services.LogEventService;
import imcode.services.IvisServiceFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by vitaly on 03.06.15.
 */

public class OAuth2LogEventService extends AbstractOAuth2Service<LogEvent, Long> implements LogEventService{
    public OAuth2LogEventService() {
    }

    public OAuth2LogEventService(IvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2LogEventService(IvisServiceFactory factory) {
        super(factory);
    }

    @Override
    public void saveAsync(LogEvent entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogEvent> findByEntity(JpaEntity<Long> entity) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("entityClassName", entity.getClass().getName());
        params.put("entityId", entity.getId());
        List<LogEvent> result = sendRequestList(getFindAllRequest(), params);
//        RestServiseRequest request = getFindAllRequest();
//        String uri = request.getAddress() + "?entityId={entityId}";
//        Object[] uriVariables = {entityId};
//        HttpMethod method = request.getMethod();
//
//        RestTemplate restTemplate = getRestTemplate();
//        ParameterizedTypeReference typeReference = getListTypeReference();
//        ResponseEntity responseEntity = restTemplate.exchange(uri, method, null, typeReference, uriVariables);
//
//        if (responseEntity.getBody() != null) {
//            return (List<LogEvent>) responseEntity.getBody();
//        }

        return result;
    }
}
