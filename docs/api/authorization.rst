Authorization
=============

Step 1
------

In order to be authorized and to obtain the token you have to get authorization code first by sending GET request to

/oauth/authorize?response_type=code&client_id={yourClientId}&redirect_uri={redirectUrl}&display=popup&scope={scope}

where

{yourClientId} is ID of your client (provided by iVIS administrators)
{redirectUrl} is the URL that will receive authorization code after successful authorization
{scope} is the list of the required permissions (currently you can use 'read+write')

Code example **Java** using org.apache.http package

.. code-block:: properties

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

.. code-block:: properties

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

Step 2
------

When you have the authorization code (it is sent by GET request to your {redirectUrl}) you can try to get token by
sending POST request to

/oauth/token

with parameters

code (= '{code}')
client_id (= '{yourClientId}') - the same as in step 1
client_secret (= '{yourClientSecret}')
redirect_uri (= '{redirectUrl}') - the same as in step 1
grant_type = (= 'authorization_code')

As response to the redirect_uri you will receive json object with next properties:

access_token (token for access to API)
refresh_token (when token is expired, you can exchange refresh_token to new access_token, see step 3)
expires_in (date and time until token is suitable to getting access)

access_token object has another properties, but trey aren't necessary for accessing to API.

Code example **Java** using org.apache.http package

.. code-block:: properties

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
   HttpResponse responses = client.execute(post);

   String token = EntityUtils.toString(responses.getEntity()); //there is a json object response

Code example **JS** using JQuery

.. code-block:: properties

   var tokenURI = "http://ivis.dev.imcode.com/oauth/token";
   var redirectURI = "{redirectUrl}";
   var clientId = "{yourClientId}";
   var clientSecret = "{yourClientSecret}";
   var code = location.href.split('code=')[1];//get value of parameter code
   // it's only one param, so you can use this way to get code, or write your own

   $.post({
       url : tokenURI,
       data : {
           'code' : code,
           'client_id' : clientId,
           'client_secret' : clientSecret,
           'redirect_uri' : redirectURI,
           'grant_type' : 'authorization_code'
       },
       success : function (token) {
                     alert(token['access_token']); //use received token
                     alert(token['refresh_token']);
                     alert(token['expires_in']);
        }
    });

Step 3
------

When your token is expired you can refresh (update) it without repeating authorization by sending POST request to

/oauth/token

with parameters

refresh_token (='{yourRefreshToken}') - is the refresh token from the step 2
grant_type = (= 'refresh_token')

Code example **Java** using org.apache.http package

.. code-block:: properties

   String refreshToken = "{yourRefreshToken}";

   List<NameValuePair> pairsPost = new LinkedList<NameValuePair>();
   pairsPost.add(new BasicNameValuePair("refresh_token", refreshToken));
   pairsPost.add(new BasicNameValuePair("grant_type", "refresh_token"));

   HttpPost post = new HttpPost(tokenURI);
   post.setEntity(new UrlEncodedFormEntity(pairsPost));

   HttpClient client = new DefaultHttpClient();
   HttpResponse responses = client.execute(post);

   String token = EntityUtils.toString(responses.getEntity()); //there is a json object response

Code example **JS** using JQuery

.. code-block:: properties

   var refreshToken = "{yourRefreshToken}";

   $.post({
       url : tokenURI,
       data : {
           'refresh_token' : refreshToken,
           'grant_type' : 'refresh_token'
       },
       success : function (token) {
                     alert(token['access_token']); //use received token
                     alert(token['refresh_token']);
                     alert(token['expires_in']);
        }
    });