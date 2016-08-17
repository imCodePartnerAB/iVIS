<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Test api</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>

</head>
<body>

<sec:authorize access="hasRole('ROLE_ADMIN')">
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
            var URL = $('#path').find(":selected").text() + $('#idOf').val();
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

        function getAccessToken() {
            var clientId = $("#client-id").val();
            var clientSecret = $("#client-secret").val();
            var base64IdAndSecret = btoa(clientId + ':' + clientSecret);
            var tokenURI = "http://localhost:8080/oauth/token";
            $.ajax({
                url : tokenURI,
                type: "POST",
                data : {
                    'grant_type' : 'client_credentials'
                },
                beforeSend : function (xhr) {
                    xhr.setRequestHeader ("Authorization", "Basic " + base64IdAndSecret);
                },
                success : function (token) {
                    $(".access-token-for-client").append("<br>Access token: " + token["access_token"]);
                    $(".access-token-for-client").append("<br>Expires in: " + token["expires_in"]);
                },
                error: function (error) {
                    console.log(error);
                }

            });
        }

    </script>

    <style>
        #jsonResponse {
            float: right;
            display: none;
            width: 70%;
        }
    </style>

    <br>
    Get access token for client

    <br>
    <div class="access-token-for-client">
        Client id:
        <input id="client-id">
        <br>
        Client secret:
        <input id="client-secret">
        <br>
        <button onclick="getAccessToken();">Get access token for client</button>
        <br>
    </div>


</sec:authorize>

<sec:authorize access="!hasRole('ROLE_ADMIN')">
    You are not admin. Access denied.
</sec:authorize>

</body>
</html>