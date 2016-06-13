
package incidentAPI;


import static org.junit.Assert.*;
import org.json.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import util.RequestUtil;

/**
 * Created by ruslan on 10.06.16.
 */
public abstract class AbstractTest {
    protected static RequestUtil requestUtil;
    protected static int savedId;

    @BeforeClass
    public static void receiveAccessToken() {
        requestUtil = new RequestUtil("http://localhost:8080");
        requestUtil.receiveAccessToken();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetAll() throws Exception {

        String json = requestUtil.makeRequest(RequestUtil.genRelUrl(getAllURL()), null, "GET");

        JSONArray jsonArray = new JSONArray(json);

        assertNotNull(jsonArray);

    }

    abstract String getAllURL();

    @Test
    public void testGet() throws Exception {

        String json = requestUtil.makeRequest(RequestUtil.genRelUrl(getAllURL() +  additional()), null, "GET");

        JSONObject jsonObject = new JSONObject(json);

        assertNotNull(jsonObject);

    }

    abstract String additional();

    abstract void testSave() throws Exception;

    abstract void testUpdate() throws Exception;

    @Test
    public void testDelete() throws Exception {

        String json = requestUtil.makeRequest(RequestUtil.genRelUrl(getAllURL() +  "/" + savedId), null, "DELETE");

        JSONObject jsonObject = new JSONObject(json);

        assertEquals(jsonObject.getInt("id"), savedId);

    }

}
