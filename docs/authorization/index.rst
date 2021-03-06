Authorization
=============

    * `Few words about OAuth 2.0`_
    * `iVIS authorization`_
    * `Authorization in details`_

Few words about OAuth 2.0
-------------------------

In `OAuth 2.0 <https://tools.ietf.org/html/rfc6749>`_ concept for authorization defines 4 different ways, they
are called `Authorized Grant Types <https://tools.ietf.org/html/rfc6749#section-1.3>`_.

They are:

    * `authorization code <https://tools.ietf.org/html/rfc6749#section-1.3.1>`_
    * `implicit <https://tools.ietf.org/html/rfc6749#section-1.3.2>`_
    * `client credentials <https://tools.ietf.org/html/rfc6749#section-1.3.4>`_
    * `password <https://tools.ietf.org/html/rfc6749#section-1.3.3>`_

iVIS authorization
------------------

According to Authorized Grant Type there 4 ways to be authorized in iVIS.

.. note::
In iVIS administrator define which Authorized Grant Type must use client (it can be 1 or all together).

For authorization client user we recommend use **authorization code grant**.

We implemented authorization of client user in next way:

    #. User which want to login click login.
    #. Client app send redirect to iVIS server with client credentials (see `Step 1`_ `Get authorization code`_).
    #. User input username and password and click Login.
    #. iVIS redirect back (according to redirect url) with parameter code.
    #. Client based on code make request to obtain access token(see `Step 1`_ `Get access token with authorization code`_).
    #. As response client get access token object.
    #. Every API request from client must have the access_token (property from received object).

Authorization in details
------------------------

Basic there are two steps working with authorization.

Step 1
~~~~~~

Get authorization code
""""""""""""""""""""""

In order to be authorized and to obtain the token you have to get authorization code first by sending GET request to

/oauth/authorize?response_type=code&client_id={yourClientId}&redirect_uri={redirectUrl}&display=popup&scope={scope}

where

{yourClientId} is ID of your client (provided by iVIS administrators)

{redirectUrl} is the URL that will receive authorization code after successful authorization

{scope} is the list of the required permissions (currently you can use 'read+write')

Code example **Java** using org.apache.http package

.. code-block:: java
   :linenos:

       String authorizeUrl = "http://ivis.dev.imcode.com/oauth/authorize";
       String redirectUri = "{yourClientRedirectUrl}";
       String clientId = "{yourClientId}";

       URIBuilder builder = new URIBuilder(authorizeUrl);
       builder.addParameter("response_type", "code");
       builder.addParameter("client_id", clientId);
       builder.addParameter("redirect_uri", redirectUri);
       builder.addParameter("display", "popup");

       String path = builder.build().toString();
       response.sendRedirect(path);

Code example **JS** using JQuery

.. code-block:: javascript
   :linenos:

       var authorizeURI = "http://ivis.dev.imcode.com/oauth/authorize";
       var redirectUri = "{yourClientRedirectUrl}";
       var clientId = "{yourClientId}";

       var data = {
           'response_type' : 'code',
           'client_id' : clientId,
           'redirect_uri' : redirectUri,
           'display' : 'popup'
            };

       location.href = authorizeURI + '?' + $.param(data);

Get access token with authorization code
""""""""""""""""""""""""""""""""""""""""

When you have the authorization code (it is set as url parameter to {yourClientRedirectUrl}) you can try to get token by
sending POST request to

/oauth/token

with parameters

code (= '{code}')

redirect_uri (= '{redirectUrl}')

grant_type = (= 'authorization_code')

Also you need to send client_id and client_secret in request headers.
Header parameter looks like following:
Authorization (="Basic " + ConvertBase64Encoding(client_id + ":" + client_secret)).

As response to the redirect_uri you will receive json object with next properties:

access_token (token for access to API)

refresh_token (when token is expired, you can exchange refresh_token to new access_token, see step 2)

expires_in (property is a number of seconds after which the access token expires, and is no longer valid)

access_token object has also another properties, but they aren't necessary for accessing to API.

Code example **Java** using org.apache.http package

.. code-block:: java
   :linenos:

       String tokenURI = "http://ivis.dev.imcode.com/oauth/token";
       String redirectURI = "{redirectUrl}";

       String clientId = "{yourClientId}";
       String clientSecret = "{yourClientSecret}";
       String base64IdAndSecretColonSeparated = new String(
                Base64.getEncoder().encode(
                        (clientId + ":" + clientSecret)
                                .getBytes())
       );

       List<NameValuePair> pairsPost = new LinkedList<NameValuePair>();
       pairsPost.add(new BasicNameValuePair("code", request.getParameter("code")));
       pairsPost.add(new BasicNameValuePair("redirect_uri", redirectURI));
       pairsPost.add(new BasicNameValuePair("grant_type", "authorization_code"));

       HttpPost post = new HttpPost(tokenURI);

       post.setEntity(new UrlEncodedFormEntity(pairsPost));
       post.setHeader("Authorization", "Basic " + base64IdAndSecretColonSeparated);

       HttpClient client = HttpClientBuilder.create().build();
       HttpResponse response = client.execute(post);

       String token = EntityUtils.toString(response.getEntity()); //there is the json object response

Code example **JS** using JQuery

.. code-block:: javascript
   :linenos:

       var tokenURI = "http://ivis.dev.imcode.com/oauth/token";
       var redirectUri = "{redirectUrl}";

       var clientId = "{yourClientId}";
       var clientSecret = "{yourClientSecret}";
       var base64IdAndSecretColonSeparated = btoa(clientId + ':' + clientSecret);//IE 10 and higher
       //For security improvement last line is recommended to generate on server side.

       var code = location.href.split('code=')[1];//get value of parameter code
       // it's only one param, so you can use this way to get code, or write your own

       $.post({
           url : tokenURI,
           data : {
               'code' : code,
               'redirect_uri' : redirectUri,
               'grant_type' : 'authorization_code'
           },
           beforeSend : function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic " + base64IdAndSecretColonSeparated);
           },
           success : function (token) {
                 alert(token['access_token']); //use received token
                 alert(token['refresh_token']);
                 alert(token['expires_in']);
            }
        });

Step 2
~~~~~~

When your token is expired  you can refresh (update) it without repeating authorization by sending POST request to

/oauth/token

with parameters

refresh_token (='{yourRefreshToken}') - is the refresh token from the step 1

grant_type (= 'refresh_token')

Also you need to send client_id and client_secret in request headers.
Header parameter looks like following:
Authorization (="Basic " + ConvertBase64Encoding(client_id + ":" + client_secret)).

Code example **Java** using org.apache.http package

.. code-block:: java
   :linenos:

       String tokenURI = "http://ivis.dev.imcode.com/oauth/token";
       String refreshToken = "{yourRefreshToken}";

       String client_id = "{yourClientId}";
       String client_secret = "{yourClientSecret}";
       String base64IdAndSecretColonSeparated = new String(
                Base64.getEncoder().encode(
                        (clientId + ":" + clientSecret)
                                .getBytes())
       );

       List<NameValuePair> pairsPost = new LinkedList<NameValuePair>();
       pairsPost.add(new BasicNameValuePair("refresh_token", refreshToken));
       pairsPost.add(new BasicNameValuePair("grant_type", "refresh_token"));

       HttpPost post = new HttpPost(tokenURI);

       post.setEntity(new UrlEncodedFormEntity(pairsPost));
       post.setHeader("Authorization", "Basic " + base64IdAndSecretColonSeparated);

       HttpClient client = HttpClientBuilder.create().build();
       HttpResponse response = client.execute(post);

       String token = EntityUtils.toString(response.getEntity()); //there is a json object response

Code example **JS** using JQuery

.. code-block:: javascript
   :linenos:

       var tokenURI = "http://ivis.dev.imcode.com/oauth/token";
       var refreshToken = "{yourRefreshToken}";

       var client_id = "{yourClientId}";
       var client_secret = "{yourClientSecret}";
       var base64IdAndSecretColonSeparated = btoa(clientId + ':' + clientSecret);//IE 10 and higher
       //For security improvement last line is recommended to generate on server side.

       $.post({
           url : tokenURI,
           data : {
               'refresh_token' : refreshToken,
               'grant_type' : 'refresh_token'
           },
           beforeSend : function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic " + base64IdAndSecretColonSeparated);
           },
           success : function (token) {
                         alert(token['access_token']); //use received token
                         alert(token['refresh_token']);
                         alert(token['expires_in']);
            }
        });

Remark
~~~~~~

You need to control expiry of the both tokens:

Make request until access token is good.

.. note::
    To make request you need include token to header parameter:

    Authorization (= 'Bearer {access_token}')

Next if token isn't good (expired or you can't make API call) exchange refresh token to new access token.

.. tip::
    Save refresh token as cookie to manage them for a long time.

Finally if refresh token isn't good (expired or you can't exchange it to access) re-login user again.