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

    Cookie has expiration time. It is defined by value "Refresh token validity" in seconds,
    contact system administrator to know that.

So tokens flow looks like

    #. Client app login user (access token -> session, refresh token -> cookie with expiration time).
    #. If token is expired (IvisOAuth2Utils.isTokenGood(httpServletRequest) -> exchange refresh token from cookie (cookie key "refreshToken") to access token.
    #. If cookie does not exist -> login user again.

Let's see how it looks like.

For last two points let's define handler that will work with unauthorized users.

.. code-block:: java

    public static final String PATH = "/unauthorized";

    private final AuthorizationCodeResourceDetails client;
    private final ClientProperties clientProperties;

    private final String ivisLogoutUrl;

    @Autowired
    public UnauthorizedErrorController(
            @Qualifier("clientInformation")
                    AuthorizationCodeResourceDetails client,
            ClientProperties clientProperties) {
        this.client = client;
        this.clientProperties = clientProperties;
        this.ivisLogoutUrl = clientProperties.getApiServerAddress() + clientProperties.getIvisLogoutRelativeUri();
    }

    @RequestMapping(value = PATH)
    public View unauthorizedUsers(ModelAndView view,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  @CookieValue(value = "refreshToken", required = false) String refreshTokenCookie) throws UnsupportedEncodingException, URISyntaxException {
        logger.info("User unauthorized!");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OAuth2AccessToken accessToken = IvisOAuth2Utils.getAccessToken(client, refreshTokenCookie);
        //logout client
        if (accessToken == null) {
            String loginUrl = clientProperties.getClientAddress() + IvisAuthorizationController.LOGIN_RELATIVE_URI;
            String redirectUrl = new URIBuilder(ivisLogoutUrl)
                    .addParameter("redirect_url", loginUrl)
                    .build()
                    .toString();
            logger.debug(redirectUrl);
            return new RedirectView(redirectUrl, false);
        }

        IvisOAuth2Utils.setAccessToken(request, accessToken);
        return new RedirectView("/", true);
    }

As you can see this method also logout user from iVIS.

.. note::

    In `Access to protected resources <http://docs.ivis.se/en/latest/sdk/routines/access_to_protected_resources.html>`_
    routine described IvisAuthorizedFilter.

    If user not logged in, filter intercept access to protected resources by error thrown:

    1. org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException with message "Token isn't good".
    2. org.springframework.security.access.AccessDeniedException with message "Token is good, but roles aren't".







