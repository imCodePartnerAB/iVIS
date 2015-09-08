package imcode.services.restful;

import com.imcode.entities.Person;
import com.imcode.entities.User;
import com.imcode.services.UserService;
import imcode.services.IvisServiceFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by vitaly on 03.06.15.
 */

public class OAuth2UserService extends AbstractOAuth2Service<User, Long> implements UserService{
    public OAuth2UserService() {
    }

    public OAuth2UserService(IvisServiceFactory factory, String mainServiceAddres) {
        super(factory, mainServiceAddres);
    }

    public OAuth2UserService(IvisServiceFactory factory) {
        super(factory);
    }

    @Override
    public User getCurrentUser() {
                User result = null;
        RestTemplate restTemplate = getRestTemplate();
        RestServiseRequest request = getFindAllRequest();

        String uri = request.getAddress() + "/current";
        HttpMethod method = request.getMethod();

        ResponseEntity<User> responseEntity = restTemplate.exchange(uri, method, null, User.class);

        if (responseEntity.getBody() != null) {
            result = responseEntity.getBody();
        }

        return result;
    }

    @Override
    public User findByUsername(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User findByPerson(Person person) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User findByPersonId(Long personId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User findByPersonalId(String personalId) {
        throw new UnsupportedOperationException();
//        User result = null;
//        RestTemplate restTemplate = getRestTemplate();
//        RestServiseRequest request = getFindAllRequest();
//        Object[] uriVariables = {personalId};
//
//        String uri = request.getAddress() + "?personalId={personalId}";
//        HttpMethod method = request.getMethod();
//
//        ResponseEntity<User> responseEntity = restTemplate.exchange(uri, method, null, User.class, uriVariables);
//
//        if (responseEntity.getBody() != null) {
//            result = responseEntity.getBody();
//        }
//
//        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException();
    }
}
