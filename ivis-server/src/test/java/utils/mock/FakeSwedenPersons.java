package utils.mock;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vitaly on 29.10.15.
 */
public class FakeSwedenPersons {
    public static void main(String[] args) throws IOException {
//        final String startURL = "https://fejk.se/";
//        Map<String, String> dictionary = getSwedenDictionary();
//        Pattern pattern = Pattern.compile("<tr.+>\\s*<td>(?:<a.*?>)?([\\w-Åö]+)(?:</a>)?:.*\\s*<td>(?:<a.*?>)?(.*?)(?:</a>)?</td>", Pattern.MULTILINE + Pattern.CASE_INSENSITIVE);
//        List<Map<String, String>> swedenPersons = new ArrayList<>();
//
//        try(PageGetter pageGetter = new PageGetter(startURL)){
//            for (int i = 0; i < 30; i++) {
//                String page = pageGetter.getText();
//                Map<String, String> swede = new LinkedHashMap<>();
//                swedenPersons.add(swede);
//
//                Matcher matcher = pattern.matcher(page);
//                while (matcher.find()) {
//                    String key = dictionary.get(matcher.group(1));
//                    if (key != null) {
//                        final String value = matcher.group(2);
//                        swede.put(key, value);
//                        System.out.printf("%-40s", value);
//                    }
//                }
//                System.out.println();
//            }
//        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new PersonMapperTask());
        }

        executorService.shutdown();
    }

    public static Map<String, String> getSwedenDictionary() {
        Map<String, String> map = new HashMap<>();
        map.put("Namn", "Name");
        map.put("Gata", "Street");
        map.put("Postort", "City");
        map.put("Telefon", "Phone");
        map.put("Mail", "Mail");
        map.put("Ålder", "Age");
        map.put("Födelsedatum", "Birthday");
        map.put("Personnummer", "PersonalId");
        map.put("IP-adres", "IpAddress");
        map.put("PIN-kod", "PinCode");
        map.put("Lösenord", "Password");
        map.put("Skostorlek", "ShoeSize");

        return map;
    }

    public static String getPage(String startURL) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(startURL);
        String result = "";
        try {
            HttpResponse response = httpclient.execute(httpget);
            result = EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return result;
    }

    public static String getFakePage() {
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n" +
                " \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Fejk - Fejk personnummer med namn, adress, lösenord, IP-adress och fler uppgifter</title>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "<meta name=\"description\" content=\"Fejkade uppgifter. Falskt personnummer, adress, telefon, IP-nummer, lösenord med mera. Inte äkta utan fejk.\">\n" +
                "<meta name=\"keywords\" content=\"fejk, personnummer, generator, IP-adress, IP-nummer, lösenord, IP-kontroll, PIN-kod, falsk identitet, adress, telefonnummer, födelsedag, födelsedatum\">\n" +
                "<meta name=\"robots\" content=\"noarchive\">\n" +
                "<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\">\n" +
                "<link rel=\"shortcut icon\" href=\"/favicon.ico\">\n" +
                "<link rel=\"icon\" href=\"/favicon.ico\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"content\">\n" +
                "<h1 class=\"header\">Fejkade uppgifter</h1>\n" +
                "<div id=\"ident\">\n" +
                "<p>Uppgifterna nedan är <strong>fejk</strong> och eventuell liknelse med någon är en tillfällighet.</p>\n" +
                "<table summary=\"tabellen visar en påhittad identitet\" class=\"table-center list-spacing ident\">\n" +
                "<tr class=\"d0\">\n" +
                "     <td>Namn:</td>\n" +
                "     <td>Sven-Erik Hammarberg</td>\n" +
                "</tr>\n" +
                "<tr class=\"d1\">\n" +
                "    <td>Gata:</td>\n" +
                "    <td>Järperud russerud</td>\n" +
                "</tr>\n" +
                "<tr class=\"d0\">\n" +
                "    <td>Postort:</td>\n" +
                "    <td>374 32  KARLSHAMN</td>\n" +
                "</tr>\n" +
                "<tr class=\"d1\">\n" +
                "    <td>Telefon:</td>\n" +
                "    <td>0454-4513405</td>\n" +
                "</tr>\n" +
                "<tr class=\"d0\">\n" +
                "    <td>Mail:</td>\n" +
                "    <td><a href=\"http://dodgit.com/run/checkmail?mailbox=sven-erik.hammarberg\">sven-erik.hammarberg@dodgit.com</a></td>\n" +
                "</tr>\n" +
                "<tr class=\"d1\">\n" +
                "    <td>Ålder:</td>\n" +
                "    <td>24 år</td>\n" +
                "</tr>\n" +
                "<tr class=\"d0\">\n" +
                "    <td>Födelsedatum:</td>\n" +
                "    <td>1991-08-07</td>\n" +
                "</tr>\n" +
                "<tr class=\"d1\">\n" +
                "    <td><a href=\"/personnummer\">Personnummer</a>:</td>\n" +
                "    <td>910807-7398</td>\n" +
                "</tr>\n" +
                "<tr class=\"d0\">\n" +
                "    <td>IP-adress:</td>\n" +
                "    <td><a href=\"/ip?adress=81.232.28.104\">81.232.28.104</a></td>\n" +
                "</tr>\n" +
                "<tr class=\"d1\">\n" +
                "    <td>PIN-kod:</td>\n" +
                "    <td>9043</td>\n" +
                "</tr>\n" +
                "<tr class=\"d0\">\n" +
                "    <td>Lösenord:</td>\n" +
                "    <td>]l:tA;Dv</td>\n" +
                "</tr>\n" +
                "<tr class=\"d1\">\n" +
                "    <td>Skostorlek:</td>\n" +
                "    <td>36</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "<p>Ladda om sidan för att slumpa fram nya uppgifter med <a href=\"/personnummer\">personnummer</a>. E-postadressen fungerar utan lösenord och kan användas för att slippa spam till din vanliga adress.</p>\n" +
                "<p>Kolla <a href=\"/ip\">IP-kontroll</a> för ditt <a href=\"/ip?adress=46.174.165.162\">IP-nummer</a>.\n" +
                "</p>\n" +
                "<p>Vill du bli spårad på Internet? Testa hur din webbläsare är inställd med <a href=\"/dnt\">Do Not Track testet</a> (DNT).\n" +
                "</p>\n" +
                "<h2 class=\"header\">Söktjänster för information</h2>\n" +
                "Riktiga personuppgifter och upplysning köpes numera lätt hos diverse webbtjänster som <a href=\"http://www.upplysning.se/\">Upplysning.se</a> och <a href=\"http://www.ratsit.se/\">Ratsit</a> på grund av eller tack vare nuvarande lagstiftning. Läs Datainspektionens pressmeddelande <a href=\"http://www.datainspektionen.se/press/nyheter/2006/Lillebror-vet-allt-om-dina-skulder/\">Lillebror vet allt om dina skulder</a>.\n" +
                "<ul>\n" +
                "<li>Leta hos <a href=\"https://foretagsfakta.bolagsverket.se/\">Bolagsverket</a> för att ta reda på organisationsnummer, företagsnamn, bolagsform samt län.</li>\n" +
                "<li>Kontrollera momsregistreringsnummer/VAT-nummer och få fram mer info från <a href=\"http://ec.europa.eu/taxation_customs/vies/vieshome.do\">VAT Information Exchange System</a>.</li>\n" +
                "<li>Hitta på ett nytt efternamn hos Patent- och registreringsverket med hjälp av deras <a href=\"http://was.prv.se/NAMTWeb/\">efternamnsförslag</a>.</li>\n" +
                "</ul>\n" +
                "<h2 class=\"header\">Fler länkar</h2>\n" +
                "<ul>\n" +
                "<li><a href=\"/tor\">Använd och stöd Tor</a></li>\n" +
                "<li><a href=\"http://piratbyran.org/bevismaskinen/\">Bevismaskinen</a> bygger bevisningen.</li>\n" +
                "<li><a href=\"http://sv.wikipedia.org/wiki/PKU-registret\">Gå ur PKU-registret</a>.</li>\n" +
                "<li>Sidan fejk.se inspirerades av Linux programmet <a href=\"http://packages.debian.org/stable/misc/rig\">Random Identity Generator (RIG)</a>.</li>\n" +
                "<li>En liknande sida med val för flera länder är <a href=\"http://fakenamegenerator.com/\">Fake Name Generator</a>.</li>\n" +
                "</ul>\n" +
                "<hr>\n" +
                "<p class=text-foot>Nu är det torsdag, 29 oktober 2015 klockan 10:00 i vecka 44. \n" +
                "Idag har Viola namnsdag. Lola, Violet, Violetta och Violette har nästan namnsdag.\n" +
                "<br><a href=\"/\">fejk</a> 1.1.6. Kommentarer? Hör av dig till <a href=\"mailto:kontakt@fejk.se\">kontakt@fejk.se</a>.<br>\n" +
                "<!-- Skicka mail till a2an7t1oe7-eo2tao2e2@junkmail.se om du vill bli svartlistad, dvs låt bli. Mail <a href=\"mailto:a2an7t1oe7-eo2tao2e2@junkmail.se\">no contact</a> if you want to be blacklisted, hint don't. -->\n" +
                "</p></div>\n" +
                "<!-- Begin Creeper--> \n" +
                "<a href=\"https://gnuheter.com/creeper/senaste\" title=\"Creeper\"><img src=\"https://gnuheter.com/creeper/image\" alt=\"Creeper\" width=\"80\" height=\"15\" border=\"0\"/></a>\n" +
                "<!-- End Creeper-->\n" +
                "</body>\n" +
                "</html>";
    }
}

class PageGetter implements AutoCloseable{
    private final HttpClient httpclient;
    private final URI uri;
    private final HttpGet httpGet;

    PageGetter(String uri) {
        try {
            this.uri = new URI(uri);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
        httpclient = new DefaultHttpClient();
        httpGet = new HttpGet(uri);
    }

    public HttpResponse getResponse() {
        try {
            return httpclient.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getText() {
        try {
            return EntityUtils.toString(getResponse().getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close(){
        httpclient.getConnectionManager().shutdown();
    }
}

class PersonMapperTask implements Callable<List<Map<String, String>>> {
    private final int personCount;
    private static final String startURL = "https://fejk.se/";
    private static final Map<String, String> dictionary = getSwedenDictionary();
    private Pattern pattern = Pattern.compile("<tr.+>\\s*<td>(?:<a.*?>)?([\\w-Åö]+)(?:</a>)?:.*\\s*<td>(?:<a.*?>)?(.*?)(?:</a>)?</td>", Pattern.MULTILINE + Pattern.CASE_INSENSITIVE);
//    private final PageGetter pageGetter;

    public PersonMapperTask() {
        this(new PageGetter(startURL));
    }

    public PersonMapperTask(PageGetter pageGetter) {
        this(pageGetter, 5);
    }

    PersonMapperTask(PageGetter pageGetter, int personCount) {
        this.personCount = personCount;
//        this.pageGetter = pageGetter;
    }

    @Override
    public List<Map<String, String>> call() throws Exception {
        List<Map<String, String>> result = new ArrayList<>();

//        for (int j = 0; j < personCount && !Thread.currentThread().isInterrupted(); j++) {
            try(PageGetter pageGetter = new PageGetter(startURL)){
                for (int i = 0; i < personCount && !Thread.currentThread().isInterrupted(); i++) {
                    String page = pageGetter.getText();
                    Map<String, String> swede = new LinkedHashMap<>();
                    result.add(swede);

                    Matcher matcher = pattern.matcher(page);
                    while (matcher.find()) {
                        String key = dictionary.get(matcher.group(1));
                        if (key != null) {
                            final String value = matcher.group(2);
                            swede.put(key, value);
                            System.out.printf("%-40s", value);
                        }
                    }
                    System.out.println();
                }
            }
//        }

        return result;
    }

    public static Map<String, String> getSwedenDictionary() {
        Map<String, String> map = new HashMap<>();
        map.put("Namn", "Name");
        map.put("Gata", "Street");
        map.put("Postort", "City");
        map.put("Telefon", "Phone");
        map.put("Mail", "Mail");
        map.put("Ålder", "Age");
        map.put("Födelsedatum", "Birthday");
        map.put("Personnummer", "PersonalId");
        map.put("IP-adres", "IpAddress");
        map.put("PIN-kod", "PinCode");
        map.put("Lösenord", "Password");
        map.put("Skostorlek", "ShoeSize");

        return map;
    }

}
