Beans configuration
===================

To use services with provided methods we need next beans:

.. code-block:: java

    private final ClientProperties clientProperties;

    @Autowired
    public ClientConfiguration(ClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

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

    @Bean
    public OAuth2ClientContext clientContext() {
        return new DefaultOAuth2ClientContext();
    }

    @Bean
    public ProxyIvisServiceFactory ivisServiceFactory() {
        String apiUrl = clientProperties.getApiServerAddress() + clientProperties.getApiRelativeUri();
        return new ProxyIvisServiceFactory(apiUrl, clientContext(), ivisClient());
    }


