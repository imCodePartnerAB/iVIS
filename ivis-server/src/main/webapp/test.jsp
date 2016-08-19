<%@ page import="org.apache.http.client.utils.URIBuilder" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="org.apache.http.util.EntityUtils" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<%
    String authorizeURI = "http://ivis.dev.imcode.com/oauth/authorize";
    String tokenURI = "http://ivis.dev.imcode.com/oauth/token";
    String redirectURI = "http://ivis.dev.imcode.com/test.jsp";
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


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Test api</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>

</head>
<body>

    <div id="jsonResponse">
        <button onclick="hideResult();">Hide result</button>
        <pre id="formatText">
    </pre>
        <div id="responseText">

        </div>
    </div>

    <div class="testUsual">
        URL:
        <select id="path">
            <option>/api/v1/json/users/loggedin</option>
            <option>/api/v1/json/priorities</option>
            <option>/api/v1/json/categories</option>
            <option>/api/v1/json/statuses</option>
            <option>/api/v1/json/persons</option>
            <option>/api/v1/json/pupils</option>
            <option>/api/v1/json/incidents</option>
            <option>/api/v1/json/issues</option>
            <option>/api/v1/json/activities</option>
            <option>/api/v1/json/activities/attach</option>
            <option>/api/v1/json/applications</option>
            <option>/api/v1/json/applicationformquestiongroups</option>
            <option>/api/v1/json/applicationformquestions</option>
            <option>/api/v1/json/applicationforms</option>
            <option>/api/v1/json/applicationformsteps</option>
            <option>/api/v1/json/schoolclasses</option>
            <option>/api/v1/json/schools</option>
            <option>/schema_versions</option>
        </select>
        <br/>
        <br/>
        {id}
        <input id="idOf" value="/">
        (if not need, not delete "/")
        <br/>
        <br/>
        HTTP Method:
        <select id="typeMethod">
            <option>GET</option>
            <option>POST</option>
            <option>PUT</option>
            <option>DELETE</option>
        </select>
        <br/>
        <br/>
        JSON data (suitable also with GET)
        <br/>
        <textarea id="jsonInput" cols=50 rows=30></textarea>
        <br/>
        <br/>

        <button id="requestBtn" onclick="makeRequest();">Send request</button>
        <br/>
        <br/>
        Working with file section:
        <br/>
        <form id="formUpload" action="" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <br/>
            <button id="attachBtn" onclick="attachFile();">Attach file</button>
            <br/>

        </form>

        <button id="downloadBtn" onclick="downloadFile();">Download file</button>

    </div>


    <script>
        function makeRequest() {
            var URL = $('#idOf').val() == "/" ? $('#path').find(":selected").text()
                    : $('#path').find(":selected").text() + $('#idOf').val();
            var typeMethod = $('#typeMethod').find(":selected").text();
            var $jsonInput = $('#jsonInput').val();
            var data = {};

            if ($jsonInput) {
                data = $.parseJSON($jsonInput);
            }

            var accessToken = $.parseJSON('${token}');
            var access_token = accessToken["access_token"];

            if (typeMethod == 'GET') {
                data["access_token"] = access_token;
            } else {
                URL += "?access_token=" + access_token;
            }

            $.ajax({
//            url: '/ivis' + URL,
                url: URL,
                contentType: typeMethod == 'GET' ? 'application/x-www-form-urlencoded; charset=UTF-8' : 'application/json; charset=utf-8',
                data: typeMethod == 'GET' ? data : JSON.stringify(data),
                type: typeMethod,
                cache: false,

                success: function (result) {
                    try {
                        var jsonResult = null;

                        if (typeof result == 'string') {
                            jsonResult = JSON.stringify(JSON.parse(result), null, '\t');
                        } else {
                            jsonResult = JSON.stringify(result, null, '\t');
                        }
                        $('#formatText').text(jsonResult);
                        $('#jsonResponse').show();
                    } catch (err) {
                        $('#formatText').text("ERROR:\n" + err.toString());
                        $('#formatText').css('color', 'red');
                        $('#jsonResponse').show();
                    }

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    var result = "ERROR:\n";
                    result += "Status: " + jqXHR.status + "(" + jqXHR.statusText + ")\n";

                    $('#formatText').text(result);
                    $('#formatText').css('color', 'red');

                    var respText = null;
                    try {
                        var respObject = JSON.parse(jqXHR.responseText);
                        respText = JSON.stringify(respObject, null, '\t');
                    } catch (ex) {
                        respText = jqXHR.responseText;
                    }
                    $('#responseText').html('<pre>' + respText + '</pre>');
                    $('#jsonResponse').show();

                }
            });
        }

        function attachFile() {
            var accessToken = $.parseJSON('${token}');
            var URL = $('#path').find(":selected").text() + $('#idOf').val() + "?access_token=" + accessToken["access_token"];
            var typeMethod = $('#typeMethod').find(":selected").text().toLowerCase();

            var $form = $('#formUpload');
            $form.attr("action", URL);
            $form.attr("method", typeMethod);

            $form.submit();
        }


        function downloadFile() {
            var URL = $('#path').find(":selected").text() + $('#idOf').val();
            location.href = URL;
        }

        function hideResult() {
            $('#jsonResponse').hide();
        }

//        function getAccessToken() {
//            var clientId = "ff11397c-3e3b-4398-80a9-feba203f1928";
//            var clientSecret = "secret";
//            var base64IdAndSecret = btoa(clientId + ':' + clientSecret);
//            var tokenURI = "http://ivis.dev.imcode.com/oauth/token";
//            var accessToken = null;
//            $.ajax({
//                url : tokenURI,
//                type: "POST",
//                data : {
//                    'grant_type' : 'client_credentials'
//                },
//                async: false,
//                cache: false,
//                beforeSend : function (xhr) {
//                    xhr.setRequestHeader ("Authorization", "Basic " + base64IdAndSecret);
//                },
//                success : function (token) {
//                    accessToken = token["access_token"];
//                },
//                error: function (error) {
//                    console.log(error);
//                }
//
//            });
//
//            return accessToken;
//        }

    </script>

    <style>
        #jsonResponse {
            float: right;
            display: none;
            width: 70%;
        }
    </style>

    <%--<br>--%>
    <%--Get access token for client--%>

    <%--<br>--%>
    <%--<div class="access-token-for-client">--%>
        <%--Client id:--%>
        <%--<input id="client-id">--%>
        <%--<br>--%>
        <%--Client secret:--%>
        <%--<input id="client-secret">--%>
        <%--<br>--%>
        <%--<button onclick="getAccessToken();">Get access token for client</button>--%>
        <%--<br>--%>
    <%--</div>--%>


</sec:authorize>

<sec:authorize access="!hasRole('ROLE_ADMIN')">
    You are not admin. Access denied.
</sec:authorize>

</body>
</html>