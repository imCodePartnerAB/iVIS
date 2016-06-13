package incidentAPI;

import static org.junit.Assert.*;
import org.json.JSONObject;
import org.junit.Test;
import util.RequestUtil;

/**
 * Created by ruslan on 13.06.16.
 */
public class ActivityTest extends AbstractTest {
    @Override
    String getAllURL() {
        return "activities";
    }

    @Override
    String additional() {
        return "/3";
    }

    @Test
    @Override
    public void testSave() throws Exception {
        String description = "activity description test";
        int issue = 1;

        JSONObject activity = new JSONObject();

        activity.put("description", description);

        activity.put("issue", new JSONObject().put("id", issue));

        String activityJson = requestUtil.makeRequest(RequestUtil.genRelUrl(getAllURL()), activity, "POST");

        JSONObject activityJSONObject = new JSONObject(activityJson);

        savedId = activityJSONObject.getInt("id");

        assertEquals(activityJSONObject.getString("description"), description);
        assertEquals(activityJSONObject.getJSONObject("issue").getInt("id"), issue);

    }

    @Test
    @Override
    public void testUpdate() throws Exception {
        String description = "activity description test changed";
        int issue = 3;

        JSONObject activity = new JSONObject();

        activity.put("description", description);

        activity.put("issue", new JSONObject().put("id", issue));

        String activityJson = requestUtil.makeRequest(RequestUtil.genRelUrl(getAllURL() + "/" + savedId), activity, "PUT");

        JSONObject activityJSONObject = new JSONObject(activityJson);

        savedId = activityJSONObject.getInt("id");

        assertEquals(activityJSONObject.getString("description"), description);
        assertEquals(activityJSONObject.getJSONObject("issue").getInt("id"), issue);

    }

    @Override
    public void testDelete() throws Exception {

    }
}
