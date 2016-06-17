Authorization
=============

Basic there are two steps working with authorization.

First step divide into two different ways (with grant type authorization code or password) to retrieve
access token object:

    * In `Step 1 (first way)`_ you retrieve access token by two requests with authorization code:

        * GET call (`Get authorization code`_)
        * POST call (`Get access token with authorization code`_)

    * In `Step 1 (second way)`_ you retrieve access token by one POST call with password

Step 1 (first way)
------------------

Get authorization code
~~~~~~~~~~~~~~~~~~~~~~

In order to be authorized and to obtain the token you have to get authorization code first by sending GET request to

/oauth/authorize?response_type=code&client_id={yourClientId}&redirect_uri={redirectUrl}&display=popup&scope={scope}

where

{yourClientId} is ID of your client (provided by iVIS administrators)

{redirectUrl} is the URL that will receive authorization code after successful authorization

{scope} is the list of the required permissions (currently you can use 'read+write')

Code example **Java** using org.apache.http package

.. code-block:: java
   :linenos:

   String authorizeURI = "http://ivis.dev.imcode.com/oauth/authorize";
   String redirectURI = "{redirectUrl}";
   String clientId = "{yourClientId}";
   String scope = "read+write";

   URIBuilder builder = new URIBuilder(authorizeURI);
   builder.addParameter("response_type", "code");
   builder.addParameter("client_id", clientId);
   builder.addParameter("redirect_uri", redirectURI);
   builder.addParameter("display", "popup");
   builder.addParameter("scope", scope);
   String path = builder.build().toString();
   response.sendRedirect(path); // GET request

Code example **JS** using JQuery

.. code-block:: javascript
   :linenos:

   var authorizeURI = "http://ivis.dev.imcode.com/oauth/authorize";
   var redirectURI = "{redirectUrl}";
   var clientId = "{yourClientId}";
   var scope = "read+write";

   var data = {
       'response_type' : 'code',
       'client_id' : clientId,
       'redirect_uri' : redirectURI,
       'display' : 'popup',
       'scope' : scope
        };

   location.href = authorizeURI + '?' + $.param(data);

Get access token with authorization code
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

When you have the authorization code (it is sent by GET request to your {redirectUrl}) you can try to get token by
sending POST request to

/oauth/token

with parameters

code (= '{code}')

client_id (= '{yourClientId}') - the same as in step 1

client_secret (= '{yourClientSecret}')

redirect_uri (= '{redirectUrl}') - the same as in step 1

grant_type = (= 'authorization_code')

This necessary if you want receive token on server side (see Java example).

If you want receive token in client side you need send client_id and client_secret in header of request.
It's attached as parameter:
Authorization (="Basic " + ConvertBase64Encoding(client_id + ":" + client_secret)).
See JS example.

As response to the redirect_uri you will receive json object with next properties:

access_token (token for access to API)

refresh_token (when token is expired, you can exchange refresh_token to new access_token, see step 3)

expires_in (property is a number of seconds after which the access token expires, and is no longer valid)

access_token object has also another properties, but they aren't necessary for accessing to API.

Code example **Java** using org.apache.http package

.. code-block:: java
   :linenos:

   String tokenURI = "http://ivis.dev.imcode.com/oauth/token";
   String redirectURI = "{redirectUrl}";
   String clientId = "{yourClientId}";
   String clientSecret = "{yourClientSecret}";

   List<NameValuePair> pairsPost = new LinkedList<NameValuePair>();
   pairsPost.add(new BasicNameValuePair("code", request.getParameter("code")));
   pairsPost.add(new BasicNameValuePair("client_id", clientId));
   pairsPost.add(new BasicNameValuePair("client_secret", clientSecret));
   pairsPost.add(new BasicNameValuePair("redirect_uri", redirectURI));
   pairsPost.add(new BasicNameValuePair("grant_type", "authorization_code"));

   HttpPost post = new HttpPost(tokenURI);
   post.setEntity(new UrlEncodedFormEntity(pairsPost));
   HttpClient client = new DefaultHttpClient();
   HttpResponse response = client.execute(post);

   String token = EntityUtils.toString(response.getEntity()); //there is a json object response

Code example **JS** using JQuery

.. code-block:: javascript
   :linenos:

   var tokenURI = "http://ivis.dev.imcode.com/oauth/token";
   var redirectURI = "{redirectUrl}";
   var clientId = "{yourClientId}";
   var clientSecret = "{yourClientSecret}";
   var base64IdAndSecret = btoa(clientId + ':' + clientSecret);//IE 10 and higher
   var code = location.href.split('code=')[1];//get value of parameter code
   // it's only one param, so you can use this way to get code, or write your own

   $.post({
       url : tokenURI,
       data : {
           'code' : code,
           'redirect_uri' : redirectURI,
           'grant_type' : 'authorization_code'
       },
       beforeSend : function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + base64IdAndSecret);
       },
       success : function (token) {
             alert(token['access_token']); //use received token
             alert(token['refresh_token']);
             alert(token['expires_in']);
        }
    });

Step 1 (second way)
-------------------

You can obtain access token by providing your login and password in one POST request to

/oauth/token

with parameters

username (= '{yourUsername}')

password (= '{yourPassword}')

client_id (= '{yourClientId}')

client_secret (= '{yourClientSecret}')

grant_type (= 'password')

As response to the redirect_uri you will receive json object with next properties:

access_token (token for access to API)

refresh_token (when token is expired, you can exchange refresh_token to new access_token, see step 3)

expires_in (property is a number of seconds after which the access token expires, and is no longer valid)

access_token object has also another properties, but they aren't necessary for accessing to API.

Code example **Java** using org.apache.http package

.. code-block:: java
   :linenos:

   String tokenURI = "http://ivis.dev.imcode.com/oauth/token";
   String clientId = "{yourClientId}";
   String clientSecret = "{yourClientSecret}";
   String username = "{yourUsername}";
   String password = "{yourPassword}";

   List<NameValuePair> pairsPost = new LinkedList<NameValuePair>();
   pairsPost.add(new BasicNameValuePair("username", username));
   pairsPost.add(new BasicNameValuePair("password", password));
   pairsPost.add(new BasicNameValuePair("client_id", clientId));
   pairsPost.add(new BasicNameValuePair("client_secret", clientSecret));
   pairsPost.add(new BasicNameValuePair("grant_type", "password"));

   HttpPost post = new HttpPost(tokenURI);
   post.setEntity(new UrlEncodedFormEntity(pairsPost));
   HttpClient client = new DefaultHttpClient();
   HttpResponse response = client.execute(post);

   String token = EntityUtils.toString(response.getEntity()); //there is a json object response

Step 2
------

When your token is expired  you can refresh (update) it without repeating authorization by sending POST request to

/oauth/token

with parameters

refresh_token (='{yourRefreshToken}') - is the refresh token from the step 2

grant_type (= 'refresh_token')

client_id (= 'yourClientId')

client_secret (= 'yourClientSecret')

Code example **Java** using org.apache.http package

.. code-block:: java
   :linenos:

   String tokenURI = "http://ivis.dev.imcode.com/oauth/token";
   String refreshToken = "{yourRefreshToken}";
   String client_id = "{yourClientId}";
   String client_secret = "{yourClientSecret}";
   String refreshToken = "{yourRefreshToken}";

   List<NameValuePair> pairsPost = new LinkedList<NameValuePair>();
   pairsPost.add(new BasicNameValuePair("refresh_token", refreshToken));
   pairsPost.add(new BasicNameValuePair("grant_type", "refresh_token"));
   pairsPost.add(new BasicNameValuePair("client_id", client_id));
   pairsPost.add(new BasicNameValuePair("grant_type", "refresh_token"));

   HttpPost post = new HttpPost(tokenURI);
   post.setEntity(new UrlEncodedFormEntity(pairsPost));

   HttpClient client = new DefaultHttpClient();
   HttpResponse response = client.execute(post);

   String token = EntityUtils.toString(response.getEntity()); //there is a json object response

Code example **JS** using JQuery

.. code-block:: javascript
   :linenos:

   var tokenURI = "http://ivis.dev.imcode.com/oauth/token";
   var refreshToken = "{yourRefreshToken}";
   var client_id = "{yourClientId}";
   var client_secret = "{yourClientSecret}";

   $.post({
       url : tokenURI,
       data : {
           'refresh_token' : refreshToken,
           'grant_type' : 'refresh_token',
           'client_id' : client_id,
           'client_secret' : client_secret,
           'grant_type' : 'refresh_token',
       },
       success : function (token) {
                     alert(token['access_token']); //use received token
                     alert(token['refresh_token']);
                     alert(token['expires_in']);
        }
    });