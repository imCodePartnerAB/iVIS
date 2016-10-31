package imcode.services.utils;

import com.imcode.entities.Person;
import com.imcode.entities.User;
import com.imcode.services.UserService;
import imcode.services.IvisServiceFactory;
import imcode.services.restful.ProxyIvisServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by vitaly on 28.05.15.
 */
public class IvisOAuth2Utils {
    public static final String CLIENT_CONTEXT_PARAMETER_NAME = "oauth2ClientContext";
    public static final String LOGGED_IN_USER_PARAMETER_NAME = "loggedInUser";
    public static final String IVIS_SERVICE_FACTORY_PARAMETER_NAME = "ivisServiceFactory";

    public static OAuth2ClientContext getClientContext(HttpServletRequest request) {
        return getClientContext(request.getSession(true));
    }

    public static OAuth2ClientContext getClientContext(HttpSession session) {

        OAuth2ClientContext clientContext = (OAuth2ClientContext) session.getAttribute(CLIENT_CONTEXT_PARAMETER_NAME);
        if (clientContext == null) {
            clientContext = new DefaultOAuth2ClientContext();
            session.setAttribute(CLIENT_CONTEXT_PARAMETER_NAME, clientContext);
        }
        return clientContext;
    }


    public static void setAccessToken(HttpSession session, OAuth2AccessToken accessToken) {
        OAuth2ClientContext clientContext = getClientContext(session);
        clientContext.setAccessToken(accessToken);
    }

    public static void setAccessToken(HttpServletRequest request, OAuth2AccessToken accessToken) {
        setAccessToken(request.getSession(true), accessToken);
    }

    public static OAuth2AccessToken getAccessToken(HttpServletRequest request) {
        return getAccessToken(request.getSession(true));
    }

    public static OAuth2AccessToken getAccessToken(HttpSession session) {
        OAuth2AccessToken accessToken = null;
        OAuth2ClientContext clientContext = getClientContext(session);

        if (clientContext != null) {
            accessToken = clientContext.getAccessToken();
        }

        return accessToken;
    }

    public static OAuth2AccessToken getAccessToken(AuthorizationCodeResourceDetails client, String code, String redirectUri) throws UnsupportedEncodingException {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("code", code);
        form.add("redirect_uri", redirectUri);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",
                String.format("Basic %s", new String(Base64.encode(String.format("%s:%s", client.getClientId(), client.getClientSecret()).getBytes("UTF-8")), "UTF-8")));
        HttpEntity httpEntity = new HttpEntity(form, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OAuth2AccessToken> result = restTemplate.postForEntity(client.getAccessTokenUri(), httpEntity, OAuth2AccessToken.class);
        return result.getBody();
    }

    public static boolean isTokenGood(HttpServletRequest request) {
        OAuth2AccessToken accessToken = getAccessToken(request);

        if (accessToken != null && !accessToken.isExpired()) {
            return true;
        } else {
            return false;
        }
    }

    public static User getIvisLoggedInUser(HttpServletRequest request) {
        return isTokenGood(request) ? getIvisLoggedInUser(request.getSession(true)) : null;
    }

    private static User getIvisLoggedInUser(HttpSession session) {
        Object user = session.getAttribute(LOGGED_IN_USER_PARAMETER_NAME);

        if ( user != null ) {
            return user instanceof User ? (User) user : null;
        }  else {
            User currentUser = getServiceFactory(session).getService(UserService.class).getCurrentUser();
            if (currentUser == null) {
                return currentUser;
            }
            loginUser(session, currentUser);
            return currentUser;
        }
    }

    private static void loginUser(HttpSession session, User user) {
        session.setAttribute(LOGGED_IN_USER_PARAMETER_NAME, user);
    }

    public static String getOAuth2AuthirizationUrl(AuthorizationCodeResourceDetails client, String redirectUri) throws URISyntaxException {

        return getOAuth2AuthirizationUrl(client, redirectUri, true);
    }

    public static String getOAuth2AuthirizationUrl(AuthorizationCodeResourceDetails client, String redirectUri, boolean isPopup) throws URISyntaxException {

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("response_type", "code"));
        parameters.add(new BasicNameValuePair("client_id", client.getClientId()));
        parameters.add(new BasicNameValuePair("redirect_uri", redirectUri));

        if (isPopup) {
            parameters.add(new BasicNameValuePair("display", "popup"));
        }

        if (client.isScoped()) {

            StringBuilder builder = new StringBuilder();
            List<String> scope = client.getScope();

            if (scope != null) {
                Iterator<String> scopeIt = scope.iterator();
                while (scopeIt.hasNext()) {
                    builder.append(scopeIt.next());
                    if (scopeIt.hasNext()) {
                        builder.append(' ');
                    }
                }
            }

            parameters.add(new BasicNameValuePair("scope", builder.toString()));
        }


        URIBuilder uriBuilder = new URIBuilder(client.getUserAuthorizationUri());
        uriBuilder.addParameters(parameters);

        return uriBuilder.toString();
    }

    public static IvisServiceFactory getServiceFactory(HttpSession session) {
        ProxyIvisServiceFactory serviceFactory = (ProxyIvisServiceFactory) session.getAttribute(IVIS_SERVICE_FACTORY_PARAMETER_NAME);

        if (serviceFactory == null) {
            ApplicationContext ctx = getSpringContext(session);
            ProxyIvisServiceFactory serviceFactoryTemplate = ctx.getBean(ProxyIvisServiceFactory.class);
            OAuth2ProtectedResourceDetails clientDetails = ctx.getBean(OAuth2ProtectedResourceDetails.class);
            serviceFactory = new ProxyIvisServiceFactory(serviceFactoryTemplate.getApiUrl(), getClientContext(session), clientDetails);
            serviceFactory.initialize();
            session.setAttribute(IVIS_SERVICE_FACTORY_PARAMETER_NAME, serviceFactory);
        }

        return serviceFactory;
    }

    public static IvisServiceFactory getServiceFactory(HttpServletRequest request) {
        return getServiceFactory(request.getSession(true));
    }

    public static ApplicationContext getSpringContext(HttpServletRequest request) {
        return getSpringContext(request.getSession(true));
    }

    public static ApplicationContext getSpringContext(HttpSession session) {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
    }

    public static ServiceAddressBuilder getServiceAddressBuilder() {
        return new ServiceAddressBuilder();
    }
    
    public static class ServiceAddressBuilder {
        private boolean builded;
        private String endPointUrl = "http://ivis.dev.imcode.com";
        private String version = "v1";
        private String responseType = "json";

        public ServiceAddressBuilder endPointUrl(String endPointUrl) {
            checkForBuilded();
            this.endPointUrl = endPointUrl;
            return this;
        }

        public ServiceAddressBuilder version(String version) {
            checkForBuilded();
            this.version = version;
            return this;
        }

        public ServiceAddressBuilder responseType(String responseType) {
            checkForBuilded();
            this.responseType = responseType;
            return this;
        }

        public String toString() {
            Pattern pattern = Pattern.compile("./$");
            this.endPointUrl = pattern.matcher(this.endPointUrl).replaceAll("");
            this.version = pattern.matcher(this.version).replaceAll("");
            this.responseType = pattern.matcher(this.responseType).replaceAll("");
            builded = true;

            return String.format(
                    "%s/api/%s/%s/",
                    this.endPointUrl, this.version, this.responseType
            );
        }

        private void checkForBuilded() {
            if (builded)
                throw new RuntimeException("This builder is allredy builded!");
        }

        public boolean isBuilded() {
            return builded;
        }

        public String getEndPointUrl() {
            return endPointUrl;
        }

        public String getVersion() {
            return version;
        }

        public String getResponseType() {
            return responseType;
        }
    }

    public static boolean personContainsString(Person person, String searchText) {
        return person != null
                && (StringUtils.containsIgnoreCase(person.getPersonalId(), searchText)
                || StringUtils.containsIgnoreCase(person.getFirstName(), searchText)
                || StringUtils.containsIgnoreCase(person.getLastName(), searchText));
    }
}
