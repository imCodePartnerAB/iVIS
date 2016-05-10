<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.util.EntityUtils" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="org.apache.http.client.utils.URIBuilder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String authorizeURI = "http://ivis.dev.imcode.com/oauth/authorize";
    String tokenURI = "http://ivis.dev.imcode.com/oauth/token";
    String redirectURI = "http://ivis-imcms-client-demo.dev.imcode.com/test.jsp";
    String clientId = "ff11397c-3e3b-4398-80a9-feba203f1928";
    String clientSecret = "secret";
    String scope = "read+write";

    if (request.getParameter("code") == null) {
        URIBuilder builder = new URIBuilder(authorizeURI);
        builder.addParameter("response_type", "code");
        builder.addParameter("client_id", clientId);
        builder.addParameter("redirect_uri", redirectURI);
        builder.addParameter("display", "popup");
        builder.addParameter("scope", scope);
        String path = builder.build().toString();
        response.sendRedirect(path);
    } else {
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

        String responseBody = EntityUtils.toString(responses.getEntity());
        request.setAttribute("token", responseBody);
    }
%>
<html>
<head>
    <title>Test</title>
</head>

<body>

<script>
    var token = JSON.parse('${token}');
    if (token != null) {
        document.write('access_token: ' + token['access_token'] + '<br/>');
        document.write('refresh_token: ' + token['refresh_token'] + '<br/>');
        document.write('expires_in: ' + token['expires_in'] + '<br/>');
    }
</script>

<button onclick="test();">Test api in new windows with access token</button>

<script>
    function test() {
        window.open("http://ivis.dev.imcode.com/api/v1/json/incidents?access_token=" + token['access_token'],
        "Test", "height=500,width=500");
    }
</script>

<br/>

</body>
</html>
