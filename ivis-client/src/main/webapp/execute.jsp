<%@ page import="org.apache.http.client.methods.HttpGet" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="org.apache.http.util.EntityUtils" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.apache.http.client.utils.URIBuilder" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.entity.StringEntity" %>
<%@ page import="org.apache.http.entity.mime.content.FileBody" %>
<%@ page import="java.io.File" %>
<%@ page import="org.apache.http.entity.mime.MultipartEntity" %>
<%@ page import="org.json.JSONArray" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String apiProvider = "http://ivis.dev.imcode.com";
    String apiURL = null;

    HttpClient client = new DefaultHttpClient();

    //Receive token from test.jsp
    String tokenJSON = (String) request.getAttribute("token");

    JSONObject token = new JSONObject(tokenJSON);

    String accessToken = token.getString("access_token");
    String refreshToken = token.getString("refresh_token");
    long expiredIn = token.getLong("expires_in");

    //************************************************************
    //GetLoggedInUser example

    apiURL = "/api/v1/json/users/loggedin";

    URIBuilder builder = new URIBuilder(apiProvider + apiURL);

    builder.addParameter("access_token", accessToken);

    HttpGet getLoggedInUser = new HttpGet(builder.build());

    HttpResponse responses = client.execute(getLoggedInUser);

    String userJSON = EntityUtils.toString(responses.getEntity());

    JSONObject user = new JSONObject(userJSON);

    // do something with received object

    user.getInt("id");

    user.getString("first_name");

    user.getString("last_name");

    user.getJSONArray("rights");
    //***********************************************************

    //************************************************************
    //SaveIncident example

    apiURL = "/api/v1/json/incidents";

    builder = new URIBuilder(apiProvider + apiURL);

    builder.addParameter("access_token", accessToken);

    HttpPost saveIncident = new HttpPost(builder.build());

    JSONObject incident = new JSONObject();

    incident.put("title", "incident title1");

    incident.put("description", "incident title1");

    incident.put("category", new JSONObject().put("id", 1));

    incident.put("priority", new JSONObject().put("id", 1));

    StringEntity input = new StringEntity(incident.toString());
    input.setContentType("application/json");
    saveIncident.setEntity(input);

    responses = client.execute(saveIncident);

    String incidentJSON = EntityUtils.toString(responses.getEntity());

    JSONObject savedIncident = new JSONObject(incidentJSON);

    // see object which saved
    //*******************************************************************


    //*******************************************************************
    //SaveActivity example

    apiURL = "/api/v1/json/activities";

    builder = new URIBuilder(apiProvider + apiURL);

    builder.addParameter("access_token", accessToken);

    HttpPost saveActivity = new HttpPost(builder.build());

    JSONObject activity = new JSONObject();

    activity.put("description", "activity description");

    activity.put("issue", new JSONObject().put("id", 1));

    input = new StringEntity(activity.toString());
    input.setContentType("application/json");
    saveActivity.setEntity(input);

    responses = client.execute(saveActivity);

    String activityJSON = EntityUtils.toString(responses.getEntity());

    JSONObject savedActivity = new JSONObject(activityJSON);

    // see object which saved
    //*******************************************************************


    //*******************************************************************
    //Attach file to activity

    apiURL = "/api/v1/json/activities/attach/6";

    builder = new URIBuilder(apiProvider + apiURL);

    builder.addParameter("access_token", accessToken);

    HttpPost attachFileToActivity = new HttpPost(builder.build());

    FileBody file = new FileBody(new File("/home/ruslan/Documents/IO-32_Pershiy_rozdil_Popenko_Ruslan.docx"));

    MultipartEntity reqEntity = new MultipartEntity();
    reqEntity.addPart("file", file);
    attachFileToActivity.setEntity(reqEntity);

    responses = client.execute(attachFileToActivity);

    String nameOfFile = EntityUtils.toString(responses.getEntity());

    //*******************************************************************

    //*******************************************************************
    //SaveIssue

    apiURL = "/api/v1/json/issues";

    builder = new URIBuilder(apiProvider + apiURL);

    builder.addParameter("access_token", accessToken);

    HttpPost saveIssue = new HttpPost(builder.build());

    JSONObject issue = new JSONObject();

    issue.put("title", "issue title");

    issue.put("description", "issue description");

    issue.put("status", new JSONObject().put("id", 1));

    issue.put("responsiblePerson", new JSONObject().put("id", 1));

    JSONArray authorizedUsers = new JSONArray();

    authorizedUsers.put(new JSONObject().put("id", 1));
    authorizedUsers.put(new JSONObject().put("id", 3));

    issue.put("authorizedUsers", authorizedUsers);

    JSONArray incidents = new JSONArray();

    incidents.put(new JSONObject().put("id", 1));
    incidents.put(new JSONObject().put("id", 2));

    issue.put("incidents", incidents);

    input = new StringEntity(issue.toString());
    input.setContentType("application/json");
    saveIssue.setEntity(input);

    responses = client.execute(saveIssue);

    String issueJSON = EntityUtils.toString(responses.getEntity());

    JSONObject savedIssue = new JSONObject(issueJSON);

    //*******************************************************************
%>


<%--****************************************************************************************--%>
<%--Attach file to activity--%>
<form action="http://localhost:8080/ivis/api/v1/json/activities/attach/3" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <br/>
    <br/>
    <button type="submit">Submit</button>
</form>
<%--****************************************************************************************--%>
