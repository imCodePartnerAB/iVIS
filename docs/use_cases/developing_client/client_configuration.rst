Client configuration
====================

Client configuration represented at
`this <https://github.com/imCodePartnerAB/iVIS-Client-Sample/blob/master/src/main/java/com/imcode/configuration/ClientConfiguration.java>`_
file.

Injecting client properties:

.. code-block:: java

    private final ClientProperties clientProperties;

    @Autowired
    public ClientConfiguration(ClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

Current iVIS client:

.. code-block:: java

    @Bean(name = "clientInformation")
    public AuthorizationCodeResourceDetails ivisClient() {
        IvisAuthorizationCodeResourceDetails client = new IvisAuthorizationCodeResourceDetails();
        String userAuthorizationUrl = clientProperties.getApiServerAddress() + clientProperties.getUserAuthorizationRelativeUri();
        String accessTokenUrl = clientProperties.getApiServerAddress() + clientProperties.getAccessTokenRelativeUri();

        client.setClientId(clientProperties.getClientId());
        client.setClientSecret(clientProperties.getClientSecret());
        client.setUserAuthorizationUri(userAuthorizationUrl);
        client.setAccessTokenUri(accessTokenUrl);

        return client;
    }

Client context for service invocation:

.. code-block:: java

    @Bean
    public OAuth2ClientContext clientContext() {
        return new DefaultOAuth2ClientContext();
    }

Service factory for creating service classes:

.. code-block:: java

    @Bean
    public ProxyIvisServiceFactory ivisServiceFactory() {
        String apiUrl = clientProperties.getApiServerAddress() + clientProperties.getApiRelativeUri();
        return new ProxyIvisServiceFactory(apiUrl, clientContext(), ivisClient());
    }