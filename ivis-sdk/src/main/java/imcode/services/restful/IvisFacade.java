package imcode.services.restful;

import imcode.services.IvisServiceFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.util.regex.Pattern;

/**
 * Created by vitaly on 28.05.15.
 */
@Deprecated
public class IvisFacade {
    private volatile static IvisFacade singleton = null;
    private volatile Configuration configuration = new Configuration();

    public static IvisFacade instance() {
        if (singleton == null) {
            synchronized (IvisFacade.class) {
                if (singleton == null) {
                    singleton = new IvisFacade();
                }
            }
        }
        return singleton;
    }

    public static IvisFacade instance(Configuration configuration) {
        if (singleton == null) {
            synchronized (IvisFacade.class) {
                if (singleton == null) {
                    singleton = new IvisFacade();
                }
            }
        }
        synchronized (IvisFacade.class) {
            singleton.configuration = configuration;
        }
        return singleton;
    }

    protected IvisFacade() {
    }

    public IvisServiceFactory getServiceFactory(OAuth2ProtectedResourceDetails client, OAuth2ClientContext clientContext) {
//        return new DefaultIvisServiceFactory(
//                String.format(
//                        "%s/api/%s/%s/",
//                        configuration.endPointUrl, configuration.version, configuration.responseType
//                ),
//                client, clientContext
//        );
        return null;
    }

    public static class Configuration {
        public String endPointUrl = "http://ivis.dev.imcode.com";
        public String version = "v1";
        public String responseType = "json";

        public static class Builder {
            private Configuration configuration = new Configuration();

            public Builder endPointUrl(String endPointUrl) {
                configuration.endPointUrl = endPointUrl;
                return this;
            }

            public Builder version(String version) {
                configuration.version = version;
                return this;
            }

            public Builder responseType(String responseType) {
                configuration.responseType = responseType;
                return this;
            }

            public Configuration build() {
                Pattern pattern = Pattern.compile("./$");
                configuration.endPointUrl = pattern.matcher(configuration.endPointUrl).replaceAll("");
                configuration.version = pattern.matcher(configuration.version).replaceAll("");
                configuration.responseType = pattern.matcher(configuration.responseType).replaceAll("");
                return configuration;
            }
        }
    }
}
