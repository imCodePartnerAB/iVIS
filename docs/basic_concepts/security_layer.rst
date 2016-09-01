Security Layer
==============

iVIS uses `OAuth 2.0 <https://tools.ietf.org/html/rfc6749>`_ protocol
(implemented by standard `Spring Security <http://projects.spring.io/spring-security/>`_ provider)

The provider role in OAuth 2.0 is actually split between Authorization Service and Resource Service,
and these reside in the iVIS with Spring Security OAuth.
The requests for the tokens are handled by Spring MVC controller endpoints, and access to protected
resources is handled by standard Spring Security request filters. The following endpoints are required
in the Spring Security filter chain in order to implement OAuth 2.0 Authorization Server:

    * Authorization Endpoint is used to service requests for authorization (URL: /oauth/authorize)
    * Token Endpoint is used to service requests for access token (URL: /oauth/token)

You can find details `here <http://projects.spring.io/spring-security-oauth/docs/oauth2.html>`_.

Authorization and tokens management processes
---------------------------------------------

Authorization process is based on `Information about the client`_

And tokens management is based on `Tokens flow`_

Interaction between them provide Spring Security, which need configure.

Information about the client
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

For **data access** responsible
`JpaClientDetails
<https://github.com/imCodePartnerAB/iVIS/blob/6171e27d59301a4dd3cc9df768062b0d37241236/ivis-core/src/main/java/com/imcode/entities/oauth2/JpaClientDetails.java>`_
class which used for persist client credentials.

It class mapped on tables
    * dbo_oauth_client_details
    * dbo_oauth_client_additional_info
    * dbo_oauth_client_garant_types
    * dbo_oauth_client_redirect_uris
    * dbo_oauth_client_resources
    * dbo_oauth_client_roles_cross

:download:`Tables diagram <../images/clientTables.png>`

**Service** layer represent interface is
`IvisClientDetailsService
<https://github.com/imCodePartnerAB/iVIS/blob/6bc6afd037563992fb6770762cf2c3fabe312d7f/ivis-core/src/main/java/com/imcode/oauth2/IvisClientDetailsService.java>`_
and implementation is
`ClientDetailsServiceRepoImpl
<https://github.com/imCodePartnerAB/iVIS/blob/6171e27d59301a4dd3cc9df768062b0d37241236/ivis-server/src/main/java/com/imcode/services/jpa/ClientDetailsServiceRepoImpl.java>`_

MVC controller for manage is
`ClientDetailsControllerImpl
<https://github.com/imCodePartnerAB/iVIS/blob/398d6eb2ddd4cbaf137c4f1c5189ee3ce9eac87f/ivis-server/src/main/java/com/imcode/controllers/html/ClientDetailsControllerImpl.java>`_

Tokens flow
~~~~~~~~~~~

**Data access:**

`AccessToken
<https://github.com/imCodePartnerAB/iVIS/blob/fe37e74bf8af36c3908ffea80e65d6f7313c24be/ivis-core/src/main/java/com/imcode/entities/oauth2/AccessToken.java>`_
class for persist access token.

It class mapped on table *oauth_access_token*

.. image:: /images/oauth_access_tokenDiagram.png

`RefreshToken
<https://github.com/imCodePartnerAB/iVIS/blob/fe37e74bf8af36c3908ffea80e65d6f7313c24be/ivis-core/src/main/java/com/imcode/entities/oauth2/RefreshToken.java>`_
class for persist refresh token.

It class mapped on table *oauth_refresh_token*

.. image:: /images/oauth_refresh_tokenDiagram.png

**Service and controller** represented by
`JdbcTokenStore
<http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/token/store/JdbcTokenStore.html>`_













