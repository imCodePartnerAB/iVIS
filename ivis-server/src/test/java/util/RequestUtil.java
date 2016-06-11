package util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by ruslan on 10.06.16.
 */
public class RequestUtil {
    private final String tokenURI = "http://ivis.dev.imcode.com/oauth/token";
    private final String clientId = "ff11397c-3e3b-4398-80a9-feba203f1928";
    private final String clientSecret = "secret";
    private final String userName = "admin";
    private final String userPassword = "pass";

    private final String apiURL = "http://ivis.dev.imcode.com";

    private String jsonAccessToken;
    private JSONObject accessTokenJSONObject;
    private String accessToken;

    private boolean isReceived = false;

    private enum Method {GET, POST, PUT, DELETE}

    public RequestUtil() {

    }

    public void receiveAccessToken() {
        List<NameValuePair> pairsPost = new LinkedList<NameValuePair>();
        pairsPost.add(new BasicNameValuePair("client_id", clientId));
        pairsPost.add(new BasicNameValuePair("client_secret", clientSecret));
        pairsPost.add(new BasicNameValuePair("username", userName));
        pairsPost.add(new BasicNameValuePair("password", userPassword));
        pairsPost.add(new BasicNameValuePair("grant_type", "password"));

        HttpPost post = new HttpPost(tokenURI);

        try {
            post.setEntity(new UrlEncodedFormEntity(pairsPost));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            jsonAccessToken = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }


        accessTokenJSONObject = new JSONObject(jsonAccessToken);


        accessToken = accessTokenJSONObject.getString("access_token");



        isReceived = true;
    }

    public String getJsonAccessToken() {
        if (isReceived) {
            return jsonAccessToken;
        }
        return null;
    }

    private String getDeleteRequest(String URL, JSONObject jsonObject, Method method) {

        URIBuilder builder = null;
        try {
            builder = new URIBuilder(URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        builder.addParameter("access_token", accessToken);

        if (jsonObject != null) {
            Iterator keys = jsonObject.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();

                builder.addParameter(key, jsonObject.getString(key));

            }
        }

        HttpClient httpClient = new DefaultHttpClient();

        HttpRequestBase requestBase = null;

        URI uri = null;
        try {
            uri = builder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        switch (method) {
            case GET:
                requestBase = new HttpGet(uri);
                break;
            case DELETE:
                requestBase = new HttpDelete(uri);
                break;
        }

        HttpResponse response = null;
        try {
            response = httpClient.execute(requestBase);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = null;
        try {
            jsonString = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            return null;
        }

        return jsonString;
    }

    private String postPutRequest(String URL, JSONObject jsonObject, Method method) {
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        builder.addParameter("access_token", accessToken);

        HttpEntityEnclosingRequestBase requestBase = null;

        URI uri = null;
        try {
            uri = builder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        switch (method) {
            case POST:
                requestBase = new HttpPost(uri);
                break;
            case PUT:
                requestBase = new HttpPut(uri);
                break;

        }

        HttpClient httpClient = new DefaultHttpClient();

        StringEntity input = null;
        try {
            input = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        input.setContentType("application/json");
        requestBase.setEntity(input);

        HttpResponse response = null;
        try {
            response = httpClient.execute(requestBase);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = null;
        try {
            jsonString = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;

    }

    public String makeRequest(String URL, JSONObject jsonObject, String method) {
        if (isReceived) {
            switch (method) {
                case "GET":
                    return getDeleteRequest(apiURL + URL, jsonObject, Method.GET);
                case "POST":
                    return postPutRequest(apiURL + URL, jsonObject, Method.POST);
                case "PUT":
                    return postPutRequest(apiURL + URL, jsonObject, Method.PUT);
                case "DELETE":
                    return getDeleteRequest(apiURL + URL, jsonObject, Method.DELETE);

            }
        }
        return null;
    }

    public static String genRelUrl (String str) {
        return "/api/v1/json/" + str;
    }



    public static void main(String[] args) {
        RequestUtil requestUtil = new RequestUtil();
        requestUtil.receiveAccessToken();
        System.out.println(requestUtil.makeRequest("/api/v1/json/categories", null, "GET"));
    }
}
