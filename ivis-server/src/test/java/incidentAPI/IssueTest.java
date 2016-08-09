package incidentAPI;

import static org.junit.Assert.*;

import com.sun.deploy.config.JCPStoreConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import util.RequestUtil;

import java.util.Date;

/**
 * Created by ruslan on 13.06.16.
 */
public class IssueTest extends AbstractTest {
//    @Override
//    String getAllURL() {
//        return "issues";
//    }
//
//    @Override
//    String additional() {
//        return "/1";
//    }
//
//    @Test
//    @Override
//    public void testSave() throws Exception {
//        String title = "issue test title";
//        String description = "issue test description";
//        int status = 1;
//        int respPerson = 23;
//        int authPers1 = 32;
//        int authPers2 = 33;
//
//        int inc1 = 1;
//        long inc1AssDate = new Date().getTime();
//        long inc1AchDate = new Date().getTime();
//        int inc1AssBy = 945;
//        int inc1AchBy = 945;
//
//        int inc2 = 3;
//        long inc2AssDate = new Date().getTime();
//        long inc2AchDate = new Date().getTime();
//        int inc2AssBy = 951;
//        int inc2AchBy = 951;
//
//        JSONObject issue = new JSONObject();
//
//        issue.put("title", title);
//        issue.put("description", description);
//        issue.put("status", new JSONObject().put("id", status));
//        issue.put("responsible_person", new JSONObject().put("id", respPerson));
//
//        JSONArray authorizedPersons = new JSONArray();
//        authorizedPersons.put(new JSONObject().put("id", authPers1));
//        authorizedPersons.put(new JSONObject().put("id", authPers2));
//        issue.put("authorized_persons", authorizedPersons);
//
//        JSONArray incidents = new JSONArray();
//
//        JSONObject incident1 = new JSONObject();
//        incident1.put("id", inc1);
//        incident1.put("assigned_date", inc1AssDate);
//        incident1.put("assigned_by", new JSONObject().put("id", inc1AssBy));
//        incident1.put("archived_date", inc1AchDate);
//        incident1.put("archived_by", new JSONObject().put("id", inc1AchBy));
//        incidents.put(incident1);
//
//        JSONObject incident2 = new JSONObject();
//        incident2.put("id", inc2);
//        incident2.put("assigned_date", inc2AssDate);
//        incident2.put("assigned_by",  new JSONObject().put("id", inc2AssBy));
//        incident2.put("archived_date", inc2AchDate);
//        incident2.put("archived_by", new JSONObject().put("id", inc2AchBy));
//        incidents.put(incident2);
//
//        issue.put("incidents", incidents);
//
//        String issueJson = requestUtil.makeRequest(RequestUtil.genRelUrl(getAllURL()), issue, "POST");
//
//        JSONObject issueJSONObject = new JSONObject(issueJson);
//
//        savedId = issueJSONObject.getInt("id");
//
//        assertEquals(issueJSONObject.getString("title"), title);
//        assertEquals(issueJSONObject.getString("description"), description);
//
//        JSONObject statusResponse = issueJSONObject.getJSONObject("status");
//        JSONObject respPersonResponse = issueJSONObject.getJSONObject("responsible_person");
//        JSONArray authPersResponse = issueJSONObject.getJSONArray("authorized_persons");
//        JSONArray incidentsResponse = issueJSONObject.getJSONArray("incidents");
//        JSONObject inc1Response = incidentsResponse.getJSONObject(0);
//        JSONObject inc2Response = incidentsResponse.getJSONObject(1);
//
//        assertEquals(statusResponse.getInt("id"), status);
//
//        assertEquals(respPersonResponse.getInt("id"), respPerson);
//
//        assertEquals(authPersResponse.getJSONObject(0).getInt("id"), authPers1);
//        assertEquals(authPersResponse.getJSONObject(1).getInt("id"), authPers2);
//
//        assertEquals(inc1Response.getInt("id"), inc1);
//        assertEquals(inc1Response.getLong("assigned_date"), inc1AssDate);
//        assertEquals(inc1Response.getLong("archived_date"), inc1AchDate);
//        assertEquals(inc1Response.getJSONObject("assigned_by").getInt("id"), inc1AssBy);
//        assertEquals(inc1Response.getJSONObject("archived_by").getInt("id"), inc1AchBy);
//
//        assertEquals(inc2Response.getInt("id"), inc2);
//        assertEquals(inc2Response.getLong("assigned_date"), inc2AssDate);
//        assertEquals(inc2Response.getLong("archived_date"), inc2AchDate);
//        assertEquals(inc2Response.getJSONObject("assigned_by").getInt("id"), inc2AssBy);
//        assertEquals(inc2Response.getJSONObject("archived_by").getInt("id"), inc2AchBy);
//
//    }
//
//    @Test
//    @Override
//    public void testUpdate() throws Exception {
//        int act1 = 1;
//        int act2 = 3;
//        String title = "issue title test changed";
//
//        JSONArray activities = new JSONArray();
//
//        activities.put(new JSONObject().put("id", act1));
//        activities.put(new JSONObject().put("id", act2));
//
//        JSONObject issue = new JSONObject();
//
//        issue.put("activities", activities);
//
//        issue.put("title", title);
//
//        String issueJson = requestUtil.makeRequest(RequestUtil.genRelUrl(getAllURL() + "/" + savedId), issue, "PUT");
//
//        JSONObject issueJSONObject = new JSONObject(issueJson);
//
//        JSONArray activitiesResponse = issueJSONObject.getJSONArray("activities");
//
//        assertEquals(activitiesResponse.getJSONObject(0).getInt("id"), act1);
//        assertEquals(activitiesResponse.getJSONObject(1).getInt("id"), act2);
//
//        assertEquals(issueJSONObject.getString("title"), "issue title test changed");
//
//    }
//
//    @Override
//    public void testDelete() throws Exception {
//
//
//    }
}
