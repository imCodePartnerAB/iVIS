Security Layer
==============

iVIS uses `OAuth 2.0 <https://tools.ietf.org/html/rfc6749>`_ protocol
(implemented by standard `Spring Security <http://projects.spring.io/spring-security/>`_ provider).
iVIS is identity provider for any client application that uses it. It means that if user wants to use some iVIS client
application he has to login on iVIS (and receive token in the background).
It works the same way as popular social networks. In addition, iVIS can use third-party identity providers too.
So user after he is redirected to the iVIS login page may choose the option to login using BankId for example.

Users registration
------------------

Each user has to be registered in iVIS and obtain some permissions from iVIS administration before he can use the
system and any of it’s client applications. To become a registered user you need to fill out the form
on http://ivis.dev.imcode.com/registration. After that you will have your username and password. All passwords are
stored as `bcrypt <https://en.wikipedia.org/wiki/Bcrypt>`_ hashes so they can’t be read.

While the login username and password from the login page is sent over HTTPS connection using the SSL encryption
(if SSL certificate is installed; note: it is not installed on the dev iVIS Server).

Permissions
-----------

Allowed users actions set is controlled as intersection of client and user permissions.

Data encryption
---------------

Currently data in the iVIS database is stored as plain text, without encryption.

Error handling
--------------

iVIS handles the errors on 5 stages:

    #. Validation errors.
    #. Database level errors.
    #. JSON/XML mapping errors.
    #. Security errors.
    #. Other errors.

Validation errors
~~~~~~~~~~~~~~~~~

Handling and providing corresponding messages about missing required fields, too long text values etc.

Database level errors
~~~~~~~~~~~~~~~~~~~~~

Handling and providing corresponding messages about database level errors, like missing values with the given key etc.

JSON/XML mapping errors
~~~~~~~~~~~~~~~~~~~~~~~

Missing or extra fields etc.

Security errors
~~~~~~~~~~~~~~~

Expired or invalid tokens etc.

Other errors
~~~~~~~~~~~~

All other errors.

More about OAuth 2.0 implementation
-----------------------------------

There are two main entities in the iVIS OAuth 2.0 implementation: AccessToken and RefreshToken.
Their classes are mapped on the corresponding tables:

oauth_access_token
~~~~~~~~~~~~~~~~~~

.. image:: /images/oauth_access_tokenDiagram.png

oauth_refresh_token
~~~~~~~~~~~~~~~~~~~

.. image:: /images/oauth_refresh_tokenDiagram.png

The provider role in OAuth 2.0 is actually split between Authorization Service and Resource Service,
and these reside in the iVIS with Spring Security OAuth.
The requests for the tokens are handled by Spring MVC controller endpoints, and access to protected
resources is handled by standard Spring Security request filters. The following endpoints are required
in the Spring Security filter chain in order to implement OAuth 2.0 Authorization Server:

    * Authorization Endpoint is used to service requests for authorization (URL: /oauth/authorize)
    * Token Endpoint is used to service requests for access token (URL: /oauth/token)

You can find details `here <http://projects.spring.io/spring-security-oauth/docs/oauth2.html>`_.