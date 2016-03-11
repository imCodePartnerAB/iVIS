Authorization
=============

1. In order to be authorized and to obtain the token you have to get authorization code first by sending GET request to

/oauth/authorize?response_type=code&client_id={yourClientId}&redirect_uri={redirectUrl}&display=popup&scope={scope}

where

{yourClientId} is ID of your client (provided by iVIS administrators)
{redirectUrl} is the URL that will receive authorization code after successful authorization
{scope} is the list of the required permissions (currently you can use 'read+write')

2. When you have the authorization code (it is sent by GET request to your {redirectUrl}) you can try to get token by
sending POST request to

/oauth/token

with parameters

grant_type (= 'authorization_code')
code (your authorization code from the step 1)
redirect_uri (URL that will receive token)

Also you have to set request headers:

Authorization (= 'Basic {base64Hash}')

where

{base64Hash} is Base64-encoded string created as 'ClientId:ClientSecret' (colon is required).

As response to the redirect_uri you will have following parameters:

access_token (token itself)
expiration_date
refresh_token

3. When your token is expired you can refresh (update) it without repeating authorization by sending request to

/oauth/token?grant_type=refresh_token&refresh_token={yourRefreshToken}

where

{yourRefreshToken} is the refresh token from the step 2.