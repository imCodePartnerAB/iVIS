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
    String apiProvider = "http://localhost:8080/ivis";
    String apiURL = null;

    HttpClient client = new DefaultHttpClient();

    //Receive token from request
    String tokenJSON = (String) request.getAttribute("token");

    JSONObject token = new JSONObject(tokenJSON);

    String accessToken = token.getString("access_token");
    String refreshToken = token.getString("refresh_token");
    long expiredIn = token.getLong("expires_in");

    //************************************************************
    //GetLoggedInUser example

//    apiURL = "/api/v1/json/users/loggedin";
//
//    URIBuilder builder = new URIBuilder(apiProvider + apiURL);
//
//    builder.addParameter("access_token", accessToken);
//
//    HttpGet getLoggedInUser = new HttpGet(builder.build());
//
//    HttpResponse responses = client.execute(getLoggedInUser);
//
//    String userJSON = EntityUtils.toString(responses.getEntity());
//
//    JSONObject user = new JSONObject(userJSON);
//
//    // do something with received object
//
//    user.getInt("id");
//
//    user.getString("first_name");
//
//    user.getString("last_name");
//
//    user.getJSONArray("rights");
//    //***********************************************************

    //************************************************************
    //SaveIncident example

    apiURL = "/api/v1/json/incidents";

    URIBuilder builder = new URIBuilder(apiProvider + apiURL);

    builder.addParameter("access_token", accessToken);

    HttpPost saveIncident = new HttpPost(builder.build());

    JSONObject incident = new JSONObject();

    incident.put("title", "incident title1");

    incident.put("description", "incident title1");

    JSONArray categories = new JSONArray();

    categories.put(new JSONObject().put("id", 12));

    incident.put("categories", categories);

    incident.put("priority", new JSONObject().put("id", 1));

    JSONArray pupils = new JSONArray();

    pupils.put(new JSONObject().put("id", 1));

    incident.put("pupils", new JSONObject().put("id", 1));

    StringEntity input = new StringEntity(incident.toString());
    input.setContentType("application/json");
    saveIncident.setEntity(input);

    HttpResponse responses = client.execute(saveIncident);

    String incidentJSON = EntityUtils.toString(responses.getEntity());

    JSONObject savedIncident = new JSONObject(incidentJSON);

    // see object which saved
    //*******************************************************************

/*
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

    FileBody file = new FileBody(new File("/home/ruslan/Documents/some document.docx"));

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

    issue.put("responsible_person", new JSONObject().put("id", 1));

    issue.put("authorized_persons", new JSONArray().put(new JSONObject().put("id", "1")));

    JSONArray incidents = new JSONArray();

    JSONObject incident1 = new JSONObject();

    incident1.put("id" , 4);

    incident1.put("reported_by", new JSONObject().put("id", 3));
    incident1.put("assigned_date", 1463043458000l);
    incident1.put("assigned_by", new JSONObject().put("id", 3));
    incident1.put("archived_date", 1463043458000l);
    incident1.put("archived_by", new JSONObject().put("id", 3));

    incidents.put(incident1);

    issue.put("incidents", incidents);

    input = new StringEntity(issue.toString());
    input.setContentType("application/json");
    saveIssue.setEntity(input);

    responses = client.execute(saveIssue);

    String issueJSON = EntityUtils.toString(responses.getEntity());

    JSONObject savedIssue = new JSONObject(issueJSON);

    //*******************************************************************
*/
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
