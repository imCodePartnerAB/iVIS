Tokens flow
===========

Prerequisites
-------------

    * `Login <http://docs.ivis.se/en/latest/sdk/routines/login.html>`_

Need say few words how to use tokens flow.

After login user in way described at `Login <http://docs.ivis.se/en/latest/sdk/routines/login.html>`_
in session placed
`access token <http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/common/OAuth2AccessToken.html>`_.
And also refresh token value from access token object put in cookie.

.. important::

    Cookie has expiration time. It is defined by value refresh token validity seconds,
    contact system administrator to know that.

So tokens flow looks like

    #. Client app login user (access token -> session, refresh token -> cookie with expiration time).
    #. If token is expired (IvisOAuth2Utils.isTokenGood(httpServletRequest) -> exchange refresh token from cookie (cookie key "refreshToken") to access token.
    #. If cookie does not exist -> login user again.

Let's see how it looks like.

In IvisAuthorizationController.java for last two points let's define method that will work with unauthorized users.

:download:`IvisAuthorizationController.java </sdk/routines/code/IvisAuthorizationController.java>`

.. literalinclude:: /sdk/routines/code/IvisAuthorizationController.java
    :language: java
    :linenos:
    :lineno-start: 61
    :lines: 61-80

As you can see this method also logout user from iVIS.

.. note::

    Don't forget write information about error page in web.xml.

    .. code-block:: xml

        <error-page>
            <error-code>401</error-code>
            <location>/unauthorized</location>
        </error-page>

    Or use something other way for redirect to /unauthorized.








