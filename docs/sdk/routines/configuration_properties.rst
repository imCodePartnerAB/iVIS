Configuration properties
========================

Properties
----------

.. code-block:: properties

    api-server-address=http://localhost:8080
    client-address=http://localhost:8085/client
    client-id=ff11397c-3e3b-4398-80a9-feba203f1928
    client-secret=secret
    user-authorization-relative-uri=/oauth/authorize
    access-token-relative-uri=/oauth/token
    ivis-logout-relative-uri=/logout.do
    #one month
    refresh-token-validity-seconds=2592000
    api-relative-uri=/api/v1/json

Properties mappings
-------------------

.. code-block:: java

    @Component
    @ConfigurationProperties(locations = "classpath:client.properties")
    public class ClientProperties {

        private String apiServerAddress;
        private String clientAddress;
        private String clientId;
        private String clientSecret;
        private String userAuthorizationRelativeUri;
        private String accessTokenRelativeUri;
        private String ivisLogoutRelativeUri;
        private Integer refreshTokenValiditySeconds;
        private String apiRelativeUri;

        //getters and setters
    }
