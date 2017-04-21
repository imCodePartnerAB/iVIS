<%@ page import="org.apache.http.client.utils.URIBuilder" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="org.apache.http.util.EntityUtils" %>
<%@ page import="org.apache.http.impl.client.HttpClientBuilder" %>
<%@ page import="java.util.Base64" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
    String authorizeURI = "http://ivis.dev.imcode.com/oauth/authorize";
    String tokenURI = "http://ivis.dev.imcode.com/oauth/token";
    String redirectURI = "http://ivis.dev.imcode.com/test.jsp";

//    String authorizeURI = "http://localhost:8080/oauth/authorize";
//    String tokenURI = "http://localhost:8080/oauth/token";
//    String redirectURI = "http://localhost:8080/test.jsp";

    String clientId = "ff11397c-3e3b-4398-80a9-feba203f1928";
    String clientSecret = "secret";

    if (request.getParameter("code") == null) {
        URIBuilder builder = new URIBuilder(authorizeURI);
        builder.addParameter("response_type", "code");
        builder.addParameter("client_id", clientId);
        builder.addParameter("redirect_uri", redirectURI);
        builder.addParameter("display", "popup");
        String path = builder.build().toString();
        response.sendRedirect(path);
    } else {
        List<NameValuePair> pairsPost = new LinkedList<NameValuePair>();
        pairsPost.add(new BasicNameValuePair("code", request.getParameter("code")));
        pairsPost.add(new BasicNameValuePair("redirect_uri", redirectURI));
        pairsPost.add(new BasicNameValuePair("grant_type", "authorization_code"));

        String base64IdAndSecretColonSeparated = new String(
                Base64.getEncoder().encode(
                        (clientId + ":" + clientSecret)
                                .getBytes())
        );

        HttpPost post = new HttpPost(tokenURI);
        post.setEntity(new UrlEncodedFormEntity(pairsPost));
        post.setHeader("Authorization", "Basic " + base64IdAndSecretColonSeparated);

        HttpClient client = HttpClientBuilder.create().build();
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

    <div class="testUsual" style="float:left; width:500px;">
        URL: /api/v1/json/<select id="path"></select>
        <br/>
        <br/>
        <div id="idOf-wrapper" style="display: none;">
            {id}<input id="idOf"><br/>
        </div>
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

    <div id="jsonResponse" style="float:left; whitespace:pre;">
        <pre id="formatText">
        </pre>
        <div id="error-placement"></div>
    </div>

    <script>

        $(function () {
            var $path = $('#path');
            $.get('/roles/available_urls', function (data) {
                $.each(data, function (index, item) {
                    $('<option></option>')
                        .text(item)
                        .val('${pageContext.request.contextPath}/api/v1/json/' + item)
                        .appendTo($path);
                });
                $('<option></option>')
                    .text('/admin_action/')
                    .val('/admin_action/{id}')
                    .appendTo($path);

                $path.change(function () {
                    var $idOf = $('#idOf-wrapper');
                    $('#idOf').val('');
                    if ($(this).val().indexOf('{id}') !== -1) {
                        $idOf.show();
                    } else {
                        $idOf.hide();
                    }
                });

                $path.change();
            });
        });

        function makeRequest() {
            var URL = $('#path').val();
            URL = URL.replace('{id}', $('#idOf').val());
            if (URL.indexOf('/admin_action/') !== -1) {
                URL = '${pageContext.request.contextPath}' + $('#idOf').val();
            }
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

            var $jsonResponse = $('#jsonResponse');
            if ($jsonResponse.is(':hidden')) {
                $jsonResponse.show();
            }
            var $formatText = $('#formatText');
            $formatText.html('');

            var $errorPlacement = $('#error-placement');
            $errorPlacement.html('');

            $.ajax({
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
                        $formatText.text(jsonResult);
                    } catch (err) {
                        $('<div></div>')
                            .text("ERROR: " + err.toString())
                            .css('color', 'red')
                            .appendTo($errorPlacement);
                    }

                },
                error: function (jqXHR, textStatus, errorThrown) {

                    $('<div></div>')
                        .text("ERROR!")
                        .css('color', 'red')
                        .appendTo($errorPlacement);

                    $('<div></div>')
                        .text("Status: " + jqXHR.status)
                        .css('color', 'red')
                        .appendTo($errorPlacement);

                    var respText = null;
                    try {
                        var respObject = JSON.parse(jqXHR.responseText);
                        respText = JSON.stringify(respObject, null, '\t');
                    } catch (ex) {
                        respText = jqXHR.responseText;
                    }
                    $('<pre></pre>')
                        .html(respText)
                        .appendTo($errorPlacement);

                }
            });
        }

        function attachFile() {
            var accessToken = $.parseJSON('${token}');
            var URL = $('#path').val()  + "?access_token=" + accessToken["access_token"];
            URL = URL.replace('{id}', $('#idOf').val());
            if (URL.indexOf('/admin_action/') !== -1) {
                URL = '${pageContext.request.contextPath}' + $('#idOf').val();
            }
            var typeMethod = $('#typeMethod').find(":selected").text().toLowerCase();
            var $form = $('#formUpload');
            $form.attr("action", URL);
            $form.attr("method", typeMethod);
            $form.submit();
        }


        function downloadFile() {
            var URL = $('#path').val();
            URL = URL.replace('{id}', $('#idOf').val());
            if (URL.indexOf('/admin_action/') !== -1) {
                URL = '${pageContext.request.contextPath}' + $('#idOf').val();
            }
            location.href = URL;
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

</body>
</html>