package imcode.services.utils;

import imcode.services.IvisServiceFactory;
//import imcode.services.restful.IvisServiceFactory;
//import imcode.services.restful.IvisFacade;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by vitaly on 28.05.15.
 */
public class IvisOAuth2Utils {
    public static final String CLIENT_CONTEXT_PARAMETER_NAME = "oauth2ClientContext";
    public static final String IVIS_SERVICE_FACTORY_PARAMETER_NAME = "ivisServiceFactory";

    public static OAuth2ClientContext getClientContext(HttpServletRequest request) {
        return getClientContext(request.getSession());
    }

    public static OAuth2ClientContext getClientContext(HttpSession session) {
        OAuth2ClientContext clientContext = null;
        ApplicationContext ctx = getSpringContext(session);
        clientContext = ctx.getBean(OAuth2ClientContext.class);
//        clientContext = (OAuth2ClientContext) session.getAttribute(CLIENT_CONTEXT_PARAMETER_NAME);

        return clientContext;
    }

//    public static void setClientContext(HttpServletRequest request, OAuth2ClientContext clientContext) {
//        setClientContext(request.getSession(), clientContext);
//    }
//
//    public static void setClientContext(HttpSession session, OAuth2ClientContext clientContext) {
//        session.setAttribute(CLIENT_CONTEXT_PARAMETER_NAME, clientContext);
//    }

    public static void setAccessToken(HttpSession session, OAuth2AccessToken accessToken) {
        OAuth2ClientContext clientContext = getClientContext(session);

//        if (clientContext == null) {
//            clientContext = new DefaultOAuth2ClientContext();
//            setClientContext(session, clientContext);
//        }

        clientContext.setAccessToken(accessToken);
    }

    public static void setAccessToken(HttpServletRequest request, OAuth2AccessToken accessToken) {
        setAccessToken(request.getSession(), accessToken);
    }

    public static OAuth2AccessToken getAccessToken(HttpServletRequest request) {
        return getAccessToken(request.getSession());
    }

    public static OAuth2AccessToken getAccessToken(HttpSession session) {
        OAuth2AccessToken accessToken = null;
//        OAuth2ClientContext clientContext = (OAuth2ClientContext) session.getAttribute(CLIENT_CONTEXT_PARAMETER_NAME);
        OAuth2ClientContext clientContext = getClientContext(session);

        if (clientContext != null) {
            accessToken = clientContext.getAccessToken();
        }

        return accessToken;
    }

    public static boolean isTokenGood(HttpServletRequest request) {
        OAuth2AccessToken accessToken = getAccessToken(request);

        if (accessToken != null && !accessToken.isExpired()) {
            return true;
        } else {
            return false;
        }
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

//    public static IvisServiceFactory createServiceFactory(HttpSession session, OAuth2ProtectedResourceDetails client, String serverAddress) {
//        IvisServiceFactory factory = null;
//        ApplicationContext context = getSpringContext(session);
//        factory = context.getBean(IvisServiceFactory.class);
////        OAuth2ClientContext clientContext = getClientContext(session);
////
////        if (client != null) {
//////            IvisFacade ivis = IvisFacade.instance(new IvisFacade.Configuration.Builder()
//////                    .endPointUrl(serverAddress)
//////                    .responseType("json")
//////                    .version("v1").build());
//////
//////            factory = ivis.getServiceFactory(client, clientContext);
////            ServiceAddressBuilder builder = getServiceAddressBuilder();
////            String serviceAddess = builder.
////
////            session.setAttribute(IVIS_SERVICE_FACTORY_PARAMETER_NAME, factory);
////        }
//
//        return factory;
//    }

    public static IvisServiceFactory getServiceFactory(HttpSession session) {
//        return (IvisServiceFactory) session.getAttribute(IVIS_SERVICE_FACTORY_PARAMETER_NAME);
        ApplicationContext ctx = getSpringContext(session);

        return ctx.getBean(IvisServiceFactory.class);
    }

    public static IvisServiceFactory getServiceFactory(HttpServletRequest request) {
        return getServiceFactory(request.getSession());
    }

    public static ApplicationContext getSpringContext(HttpServletRequest request) {
        return getSpringContext(request.getSession());
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
}
