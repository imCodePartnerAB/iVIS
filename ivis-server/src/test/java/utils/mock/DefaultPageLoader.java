package utils.mock;

import com.imcode.entities.Person;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by vitaly on 14.12.15.
 */
public class DefaultPageLoader implements PageLoader {
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    protected static final String DEFAULT_URL = "https://fejk.se/";
    protected static final String MOZILLA_USER_AGENT = "Mozilla/5.0";
    //    private final URLConnection connection;
    private final URL url;
    private final String method;
    private final String userAgent;

    public DefaultPageLoader(String urlString) {
        this(urlString, GET_METHOD, MOZILLA_USER_AGENT);
    }

    public DefaultPageLoader(String urlString, String method, String userAgent) {
        try {
            this.url = new URL(urlString);
            this.method = method;
            this.userAgent = userAgent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private URLConnection getConnection() {
        try {
            if (url.getProtocol().equalsIgnoreCase("https")) {
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod(method);
                con.setRequestProperty("User-Agent", userAgent);
                con.setRequestProperty("Connection", "keep-alive");
                return con;
            } else {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(method);
                con.setRequestProperty("User-Agent", userAgent);
                con.setRequestProperty("Connection", "keep-alive");
                return con;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//    public static String load() {
//
//        try {
//            URL url = new URL(DEFAULT_URL);
//            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//            // optional default is GET
//            con.setRequestMethod("GET");
//
//            //add request header
//            con.setRequestProperty("User-Agent", MOZILLA_USER_AGENT);
//            con.setRequestProperty("Connection", "keep-alive");
//
//            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));) {
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                return response.toString();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public String load() {
        URLConnection connection = getConnection();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public String load() {
//        return load(DEFAULT_URL);
//    }

    public static void main(String[] args) {

        DefaultPageLoader pageLoader = new DefaultPageLoader(DEFAULT_URL);
        FakeSwedenPersonPageParser parser = new FakeSwedenPersonPageParser();

        Person person = parser.parse(pageLoader.load());
        System.out.println(person);
//        for (int i = 0; i < 10; i++) {
//            String pageContent = pageLoader.load();
//            parser.parse(pageContent);
//        }

//        pageLoader.getConnection();
//        mLoad(pageLoader);
//        sLoad();
    }
//
//    private static void mLoad(DefaultPageLoader pageLoader) {
//        long start;
//        long end;
//        start = System.nanoTime();
//        for (int i = 0; i < 10; i++) {
//            String pageContent = pageLoader.load(DEFAULT_URL);
//            pageContent = null;
//        }
//        end = System.nanoTime();
//
//        System.out.println(end - start);
//    }
//
//    private static void sLoad() {
//        long start = System.nanoTime();
//        for (int i = 0; i < 10; i++) {
//            String pageContent = load();
//            pageContent = null;
//        }
//
//        long end = System.nanoTime();
//        System.out.println(end - start);
//    }
}
