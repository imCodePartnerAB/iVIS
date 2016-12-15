Login
=====

Prerequisites
-------------

    * `Configuration <http://docs.ivis.se/en/latest/sdk/routines/configuration.html>`_
    * `Authorization <http://docs.ivis.se/en/latest/api/authorization.html>`_

To login you need:

    #. send redirect in context based on HttpServlet (JSP, Spring MVC Controllers, RestControllers etc)
       to authorizeURI.
    #. send POST request with client credentials with code.
    #. receive access token.

Let's see how it looks like.

.. code-block:: java

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcome(ModelAndView view, WebRequest webRequest, Model model) {
        view.setViewName(START_VIEW_NAME);
        return view;
    }

    @RequestMapping(value = LOGIN_RELATIVE_URL, method = RequestMethod.GET)
    public View login(ModelAndView modelAndView,  WebRequest webRequest) throws URISyntaxException, IOException {
        String oAuth2AuthirizationUrl = IvisOAuth2Utils.getOAuth2AuthirizationUrl(client, redirectUrl, false);
        return new RedirectView(oAuth2AuthirizationUrl, false);
    }

    @RequestMapping(value = REDIRECT_RELATIVE_URL, method = RequestMethod.GET)
    public View authorizationClientProcess(HttpServletRequest request,
                                           HttpServletResponse response,
                                           WebRequest webRequest,
                                           @SessionAttribute(value = IvisAuthorizedFilter.REQUEST_URI_ATTRIBUTE_NAME, required = false) String protectedResourcesUrl,
                                           @RequestParam("code") String code) throws IOException {
        OAuth2AccessToken accessToken = IvisOAuth2Utils.getAccessToken(client, code, redirectUrl);
        if (accessToken == null) {
            return new RedirectView(LOGIN_RELATIVE_URL, true);
        }
        IvisOAuth2Utils.setAccessToken(request, accessToken);
        IvisOAuth2Utils.setRefreshTokenAsCokie(response, accessToken.getRefreshToken(), clientProperties.getRefreshTokenValiditySeconds());
        String redirect = StringUtils.isEmpty(protectedResourcesUrl) ? "/" : protectedResourcesUrl;
        if (redirect.startsWith(request.getContextPath())) {
            redirect = redirect.replace(request.getContextPath(), "");
        }
        return new RedirectView(redirect, true);
    }

If user unauthorized you need redirect to login page.

.. code-block:: java
















