package incidentAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;
import util.RequestUtil;
import static org.junit.Assert.*;

/**
 * Created by ruslan on 11.06.16.
 */
public class IncidentTest extends AbstractTest {

    @Override
    String getAllURL() {
        return "incidents";
    }

    @Override
    String additional() {
        return "/1";
    }

    @Test
    @Override
    public void testSave() throws Exception {
        String title = "incident test title";
        String description = "incident test description";
        int cat1Id = 1;
        int cat2Id = 3;
        int priorId = 1;
        int pup1Id = 10;
        int pup2Id = 11;

        JSONObject incident = new JSONObject();

        incident.put("title", title);

        incident.put("description", "incident test description");

        JSONArray categories = new JSONArray();
        categories.put(new JSONObject().put("id", cat1Id));
        categories.put(new JSONObject().put("id", cat2Id));

        incident.put("categories", categories);

        incident.put("priority", new JSONObject().put("id", priorId));

        JSONArray pupils = new JSONArray();
        pupils.put(new JSONObject().put("id", pup1Id));
        pupils.put(new JSONObject().put("id", pup2Id));

        incident.put("pupils", pupils);

        String incidentJson = super.requestUtil.makeRequest(RequestUtil.genRelUrl(getAllURL()), incident, "POST");

        JSONObject incidentJSONObject = new JSONObject(incidentJson);

        savedId = incidentJSONObject.getInt("id");

        assertEquals(incidentJSONObject.getString("title"), title);
        assertEquals(incidentJSONObject.getString("description"), description);

        JSONArray categoryResponse = incidentJSONObject.getJSONArray("categories");
        JSONObject priorityResponse = incidentJSONObject.getJSONObject("priority");
        JSONArray pupilsResponse = incidentJSONObject.getJSONArray("pupils");

        assertEquals(categoryResponse.getJSONObject(0).getInt("id"), cat1Id);
        assertEquals(categoryResponse.getJSONObject(1).getInt("id"), cat2Id);

        assertEquals(priorityResponse.getInt("id"), priorId);

        assertEquals(pupilsResponse.getJSONObject(0).getInt("id"), pup1Id);
        assertEquals(pupilsResponse.getJSONObject(1).getInt("id"), pup2Id);

    }

    @Test
    @Override
    public void testUpdate() throws Exception {
        String title = "incident test title changed";

        JSONObject incident = new JSONObject();

        incident.put("title", title);

        String incidentJson = super.requestUtil.makeRequest(RequestUtil.genRelUrl(getAllURL() + "/" + savedId), incident, "PUT");

        JSONObject incidentJSONObject = new JSONObject(incidentJson);

        assertEquals(incidentJSONObject.getString("title"), title);

    }




}
