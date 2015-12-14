package utils.mock;

import com.imcode.entities.Person;
import com.imcode.entities.embed.Address;
import com.imcode.entities.embed.Email;
import com.imcode.entities.embed.Phone;
import com.imcode.entities.enums.AddressTypeEnum;
import com.imcode.entities.enums.CommunicationTypeEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vitaly on 14.12.15.
 */
public class FakeSwedenPersonPageParser implements PageParser<Person> {
    private static Map<String, String> dictionary = new HashMap<>();
    private static Pattern pattern = Pattern.compile("<tr.+?>\\s*?<td>(?:<a.*?>)??([\\wÅö\\-]+?)(?:</a>)?:.*?\\s*?<td>(?:<a.*?>)?(.*?)(?:</a>)?</td>", Pattern.MULTILINE + Pattern.CASE_INSENSITIVE);

    static {
        dictionary.put("Namn", "Name");
        dictionary.put("Gata", "Street");
        dictionary.put("Postort", "City");
        dictionary.put("Telefon", "Phone");
        dictionary.put("Mail", "Mail");
        dictionary.put("Ålder", "Age");
        dictionary.put("Födelsedatum", "Birthday");
        dictionary.put("Personnummer", "PersonalId");
        dictionary.put("IP-adres", "IpAddress");
        dictionary.put("PIN-kod", "PinCode");
        dictionary.put("Lösenord", "Password");
        dictionary.put("Skostorlek", "ShoeSize");
    }

    @Override
    public Person parse(String pageContent) {
        int begin = pageContent.indexOf("<table");
        int end = pageContent.indexOf("</table>");
        String table = pageContent.substring(begin, end);

        Map<String, String> swede = new HashMap<>();
        Person person = new Person();
        Matcher matcher = pattern.matcher(pageContent);
        while (matcher.find()) {
            String key = dictionary.get(matcher.group(1));
            if (key != null) {
                String value = matcher.group(2);
                swede.put(key, value);
//                System.out.printf("%-40s", value);
            }
        }

        String[] names = swede.get("Name").split(" ", 2);
        person.setFirstName(names[0]);
        person.setLastName(names[1]);
        person.setPersonalId(swede.get("PersonalId"));
        person.setPhone(Phone.of(CommunicationTypeEnum.HOME, swede.get("Phone")));
        person.setEmail(Email.of(CommunicationTypeEnum.HOME, swede.get("Mail")));
        Address address = Address.of(AddressTypeEnum.REGISTERED);
        String cityPostalCode = swede.get("City");
        String city = cityPostalCode.replaceAll("\\d", "").trim();
        String postalCode = cityPostalCode.replaceAll("\\D", "").trim();
        address.setCity(city);
//        address.setCareOf(swede.get(""));
        address.setMunicipalityCode(city.substring(0, 3).toUpperCase());
        address.setPostalCode(Integer.parseInt(postalCode));
        address.setStreet(swede.get("Street"));
        person.setAddress(address);
//        person.set(swede.get(""));

//        System.out.println(swede);

        return person;
    }

    private Phone generatePhone() {
        StringBuilder sb = new StringBuilder('+');
        Random rand = new Random();

        for (int i = 0; i < 12; i++) {
            sb.append(rand.nextInt(10));
        }

        return Phone.of(CommunicationTypeEnum.HOME, sb.toString());
    }
}
