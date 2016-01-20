package com.imcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.entities.Guardian;
import com.imcode.entities.Person;
import com.imcode.entities.Pupil;
import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.services.PupilService;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

//import sun.plugin.dom.core.Document;

/**
 * Created by vitaly on 17.02.15.
 */
public class MainTest {


//    public static void main(String[] args) {
//        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
//        context.load("classpath:/");
//    }

//    private static void printInfo(UUID uuid) {
//        System.out.println(uuid);
//        System.out.println(uuid.version());
//        System.out.println(uuid.variant());
//    }

//    private static Marshaller getMarshaller() {
//        XMLContext xmlContext = new XMLContext();
//
//        return xmlContext.createMarshaller();
//    }
//
//    private static Unmarshaller getUnmarshaller() {
//        XMLContext xmlContext = new XMLContext();
//
//        return xmlContext.createUnmarshaller();
//    }
//
//    public static void convertFromObjectToXML(Object object, String filepath)
//            throws IOException {
//
//        FileOutputStream os = null;
//        try {
//            os = new FileOutputStream(filepath);
//            getMarshaller().marshal(object, new StreamResult(os));
//        } finally {
//            if (os != null) {
//                os.close();
//            }
//        }
//    }
//
//    public static Object convertFromXMLToObject(String xmlfile) throws IOException, MarshalException, ValidationException {
//
//        FileInputStream is = null;
//        try {
//            is = new FileInputStream(xmlfile);
//            return getUnmarshaller().unmarshal(new StreamSource(is));
//        } finally {
//            if (is != null) {
//                is.close();
//            }
//        }
//    }


    public static Map<String, Object> convertNodesFromXml(String xml) throws Exception {

        InputStream is = new ByteArrayInputStream(xml.getBytes());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(is);
        return createMap(document.getDocumentElement());
    }

    public static Map<String, Object> createMap(Node node) {
        Map<String, Object> map = new HashMap<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
//            if (currentNode.hasAttributes()) {
//                for (int j = 0; j < currentNode.getAttributes().getLength(); j++) {
//                    Node item = currentNode.getAttributes().item(i);
//                    map.put(item.getNodeName(), item.getTextContent());
//                }
//            }
            if (node.getFirstChild() != null && node.getFirstChild().getNodeType() == Node.ELEMENT_NODE) {
                map.putAll(createMap(currentNode));
            } else if (node.getFirstChild().getNodeType() == Node.TEXT_NODE) {
                map.put(node.getLocalName(), node.getTextContent());
            }
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
//        XMLContext context = new XMLContext();
////        context.addMapping();
//
//        String s = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
//                "<FlowInstance>\n" +
//                "              <FamilyID>37</FamilyID>\n" +
//                "              <Version>3</Version>\n" +
//                "              <FlowID>181</FlowID>\n" +
//                "</FlowInstance>";
//        Map<String, Object> map = convertNodesFromXml(s);
//        System.out.println(map);
//        // Create a Reader to the file to unmarshal from
//
//        FileReader reader = new FileReader("/home/vitaly/SkypeFiles/0.xml");
//
//// Create a new Unmarshaller
//        Unmarshaller unmarshaller =
//                context.createUnmarshaller();
//        unmarshaller.setClass(Object.class);
//
//// Unmarshal the person object
//        Object o = unmarshaller.unmarshal(reader);


//        System.out.println(StatementStatus.valueOf("created"));
//        Object o = convertFromXMLToObject("/home/vitaly/SkypeFiles/795.xml");
//        System.out.println(o);
//        FileReader fis = null;
//        try (fis = new FileReader("/home/vitaly/SkypeFiles/795.xml");){
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        RestTemplate restTemplate = new RestTemplate();

////        System.out.println("Hello world");
////        JpaClientDetails clientDetails = new JpaClientDetails();
//        Role role = new Role();
//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("classpath:/spring/data.xml");
////        javax.el.ExpressionFactory
//        ctx.refresh();
//        Initializator initializator = ctx.getBean(Initializator.class);
//        initializator.init();
////
//////        try {
//////            RestTemplate restTemplate = new RestTemplate();
////
////            String result = restTemplate.getForObject("http://client:8083/web2/ivis/hastoken", String.class);
////            System.out.println(result);
//////            if (result) {
//////
//////            } else {
//////
//////            }
////        } catch (Exception ignore) { }
        GenericXmlApplicationContext ctx = getApplicationContext();
        PupilService pupilService = ctx.getBean(PupilService.class);
        List<Pupil> pupils = pupilService.findAll();


        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pupils);
//        ConversionService cs = ctx.getBean("csvConversionService", ConversionService.class);
        System.out.println(json);
//        EntityManager em = ctx.getBean(EntityManager.class);
//
//        GuardianService guardianService = ctx.getBean(GuardianService.class);
//
//        GuardianRepository guardianRepository = ctx.getBean(GuardianRepository.class);
//        PupilRepository pupilRepository = ctx.getBean(PupilRepository.class);
////
////        TestService service = ctx.getBean(TestService.class);
////
//        Guardian guardian = guardianService.find(24L);
////        Guardian g0 = getPersonalizedEntity(Guardian::new, "790411-5867", "Birgit", "Engström");
//        Guardian g1 = getPersonalizedEntity(Guardian::new, "790411-5867", "Birgit", "Engström");
//        Guardian g2 = getPersonalizedEntity(Guardian::new, "530304-5677", "Orvar", "Vestman");
//        Guardian g3 = getPersonalizedEntity(Guardian::new, "841214-4142", "Adela", "Leandersson");
//        Guardian g4 = getPersonalizedEntity(Guardian::new, "840612-7657", "Karl-Gunnar", "Lovén");
//        Guardian g5 = getPersonalizedEntity(Guardian::new, "570526-9289", "Margit", "Ljungqvist");
//        Pupil p1 = getPersonalizedEntity(Pupil::new, "630815-2237", "Seth", "Hällström");
//        Pupil p2 = getPersonalizedEntity(Pupil::new, "820614-8051", "Torvald", "Forsmark");
//        Pupil p3 = getPersonalizedEntity(Pupil::new, "600327-6273", "Linné", "Rosell");
//        Pupil p4 = getPersonalizedEntity(Pupil::new, "870609-0316", "Isidor", "Medin");
//        Pupil p5 = getPersonalizedEntity(Pupil::new, "950808-3442", "Sofia", "Karlström");
//
//        p1.setGuardians(new HashSet<>(Arrays.asList()));
//        p2.setGuardians(new HashSet<>(Arrays.asList()));
////        p2.setGuardians(new HashSet<>(Arrays.asList(g1,g2)));
////        p3.setGuardians(new HashSet<>(Arrays.asList(g3,g4)));
////        p4.setGuardians(new HashSet<>(Arrays.asList(g5)));
////        p5.setGuardians(new HashSet<>(Arrays.asList(g5)));
////
//        g1.setPupils(new HashSet<>(Arrays.asList()));
////        g1.setPupils(new HashSet<>(Arrays.asList(p1, p2)));
////        g2.setPupils(new HashSet<>(Arrays.asList(p1, p2)));
////        g3.setPupils(new HashSet<>(Arrays.asList(p3)));
////        g4.setPupils(new HashSet<>(Arrays.asList(p3)));
////        g5.setPupils(new HashSet<>(Arrays.asList(p4,p5)));
//
//        Set<Guardian> guardianSet = new HashSet<>(Arrays.asList(g1, g2, g3, g4, g5));
//        Set<Pupil> pupilSet = new HashSet<>(Arrays.asList(p1, p2, p3, p4, p5));
//        List<JpaEntity<Long>> entities = new ArrayList<>();
//        entities.addAll(guardianSet);
//        entities.addAll(pupilSet);
//
//        Pupil pp1 = pupilService.save(p1);
//        Pupil pp2 = pupilService.save(p2);
//        Guardian gg1 = guardianService.save(g1);
//        System.out.println("end.");
//        service.persist(entities);
//        service.persist(Collections.singletonList(p1));
//        guardianSet.stream().forEach(guardianService::save);
//        pupilSet.stream().forEach(pupilService::save);

//        List<Guardian> guardianList = guardianRepository.save(guardianSet);
//        List<Pupil> pupilList = pupilRepository.save(pupilSet);
//        guardianSet.stream().forEach(entity -> guardianService.delete(entity.getId()));
//        pupilSet.stream().forEach(entity -> guardianService.delete(entity.getId()));

//        PlatformTransactionManager tm = ctx.getBean(PlatformTransactionManager.class);
//        try {
////            transaction.begin();
//            System.out.println(em.getFlushMode());
//            for (JpaEntity<Long> entity :guardianSet) {
//                em.merge(entity);
//            }
//
//            for (JpaEntity<Long> entity :pupilSet) {
//                em.merge(entity);
//            }
//
//            em.flush();
//
////            em.persist();
//            transaction.commit();
//        } catch (Exception e) {
//            transaction.rollback();
//            e.printStackTrace();
//        }

//        System.out.println(guardianSet);
//        System.out.println(pupilSet);


//        person.setPhone(Phone.of(MOBILE, "0971396134"));
//        person.setEmail(Email.of(HOME, "cheetah@3g.ua"));

////        guardian.setId(88L);
//        guardian = guardianService.save(guardian);
//
//        System.out.println(guardian.getId());

//        PupilRepository pupilRepository = ctx.getBean(PupilRepository.class);
//        System.out.println(pupilRepository.findAll());

//        PersonService personService = ctx.getBean(PersonService.class);
//        PupilRepository pupilRepository = ctx.getBean(PupilRepository.class);
//        Pupil pupil = pupilRepository.findByPersonalId("850717-5019");
//        System.out.println(pupil);
//        AddressService addressService = ctx.getBean(AddressService.class);
//        PhoneService phoneService = ctx.getBean(PhoneService.class);
//        EmailService emailService = ctx.getBean(EmailService.class);
//        SchoolService schoolService = ctx.getBean(SchoolService.class);
//        PupilRepository pupilRepository = ctx.getBean(PupilRepository.class);
//        SchoolClassRepository schoolClassRepository = ctx.getBean(SchoolClassRepository.class);
//        AcademicYearRepository academicYearRepository = ctx.getBean(AcademicYearRepository.class);
////         = ctx.getBean();
//        Set<Address> addresses = new HashSet<>();
//
//        Address address = new Address();
//        address.setAddressType(AddressTypeEnum.RESIDENTIAL);
//        address.setStreet("ul. Pionerskaya, 52");
//        address.setCity("Kiev");
//        address.setPostalCode(10000);
//        address.setMunicipality_code(123L);
//        address = addressService.save(address);
//
//        Address address2 = new Address();
//        address2.setAddressType(AddressTypeEnum.RESIDENTIAL);
//        address2.setStreet("ul. Krasnotkatskaya, 52");
//        address2.setCity("Kiev");
//        address2.setPostalCode(10000);
//        address2.setMunicipality_code(123L);
//        address2 = addressService.save(address2);
//
//        addresses.add(address);
//        addresses.add(address2);
//
//        Set<Phone> phones = new HashSet<>();
//
//        phones.add(phoneService.save(new Phone("0505468838")));
//        phones.add(phoneService.save(new Phone("0671238586")));
//        phones.add(phoneService.save(new Phone("0633548225")));
//
//        Set<Email> emails = new HashSet<>();
//        emails.add(emailService.save(new Email("tihoha@gmail.com")));
//        emails.add(emailService.save(new Email("cheetah@3g.ua")));
//
////        Person
//        Person person = Person.fromString("Vitaly Sereda");
//        person.setAddresses(addresses);
//        person.setPhones(phones);
//        person.setEmails(emails);
//        person = personService.save(person);
//
////        School
//        School school = new School();
//        school.setName("School 1");
//
//        Set<ServiceTypeEnum> services = new HashSet<>();
//        services.add(ServiceTypeEnum.AFTER_SCHOOL_CENTER);
//        services.add(ServiceTypeEnum.SPECIAL_SCHOOL);
//
//        school.setServices(services);
//        schoolService.save(school);
//
////        SchoolClass
//        SchoolClass schoolClass = new SchoolClass("A1-1", school, new Date(0,0,0,8,0), new Date(0,0,0,18,0));
//        schoolClass = schoolClassRepository.save(schoolClass);
//
////        AcademicYear
//        AcademicYear academicYear = new AcademicYear("2008-2009");
//        academicYear = academicYearRepository.save(academicYear);
//
////        Pupil
//        Pupil pupil = new Pupil();
//        pupil.setPerson(person);
//        pupil.setAcademicYear(academicYear);
//        pupil.setSchoolClass(schoolClass);
//        pupil = pupilRepository.save(pupil);
//
//        System.out.println(person);
//
//////        ClientDetails
//////        ClientRoles
////        Set<ClientRole> clientRoles = new HashSet<ClientRole>(){{   add(new ClientRole("Read"));
////                                                                    add(new ClientRole("write"));
////                                                                    add(new ClientRole("execute"));
////                                                                    add(new ClientRole("schools"));
////                                                                    add(new ClientRole("admin"));
////        }};
////        ClientRoleRepository clientRoleRepository = ctx.getBean(ClientRoleRepository.class);
////        List<ClientRole> roles = clientRoleRepository.save(clientRoles);
////
////        ClietnDetailsRepository clientDetailsRepository = ctx.getBean(ClietnDetailsRepository.class);
//////        clientDetailsService.loadClientByClientId()
////        JpaClientDetails clientDetails = new JpaClientDetails();
////        clientDetails.setAccessTokenValiditySeconds(60);
////        clientDetails.setRefreshTokenValiditySeconds(600);
////        clientDetails.setAdditionalInformation(new HashMap<String, String>() {{
////            put("key", "value");
////            put("key2", "value2");
////            put("key3", "value3");
////        }});
////        clientDetails.setClientSecret("secret");
////        clientDetails.setAuthorities(roles);
////        clientDetails.setAuthorizedGrantTypes(Arrays.asList("code", "password"));
////        clientDetails.setRegisteredRedirectUri(new HashSet<String>() {{
////            add("http://localhost:8080/code");
////            add("http://localhost:8080/redirect");
////        }});
////        clientDetails.setResourceIds(Arrays.asList("res1", "res2", "res3"));
////        clientDetails.setScope(Arrays.asList("read,write".split(",")));
////        clientDetails = clientDetailsRepository.save(clientDetails);
////
//////        EntityManagerFactory entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
//////        AddressService addressService = ctx.getBean(AddressService.class);
//////
////////        AddressTypeRepository addressTypeRepository = ctx.getBean(AddressTypeRepository.class);
//////        AddressType addressType = new AddressType(AddressType.AddressTypeEnum.RESIDENTIAL);
////////        addressTypeRepository.save(addressType);
////////        AddressRepository addressRepository = ctx.getBean(AddressRepository.class);
//////        Address address = new Address();
//////////        address.setAddressType(new AddressType(AddressType.AddressTypeEnum.RESIDENTIAL));
//////        address.setAddressType(addressType);
//////        address = addressService.save(address);
////
////
////
//////        UserService userService = ctx.getBean(UserService.class);
//////        RoleService roleService = ctx.getBean(RoleService.class);
//////        Role roleAdmin = roleService.save(new Role("ROLE_ADMIN"));
//////        Role roleUser = roleService.save(new Role("ROLE_USER"));
//////
//////
//////        User userAdmin = new User("admin", "123", true, new HashSet<Role>(){{add(roleAdmin); add(roleUser);}});
//////        User userUser = new User("user", "123", true, new HashSet<Role>(){{add(roleUser);}});
//////        userAdmin = userService.save(userAdmin);
//////        userUser = userService.save(userUser);
//////        List<User> users = userService.findAll();
////////        User user = (User) userService.loadUserByUsername("admin");
//////        System.out.println(users);

//        MutablePropertyValues pvs = new MutablePropertyValues();
//        pvs.add("firstName", "Vitaly");
//        pvs.add("lastName", "Seresa");
//        pvs.add("personalId", "1111111");
//        pvs.add("email", "email@gmail.com");
//        pvs.add("phones[HOME]", "0971396134");
//        pvs.add("pupils", "1, 2, 3");

//        Guardian target = new Guardian();
//        DataBinder binder = new DataBinder(target);
//        DefaultConversionService conversionService = new DefaultConversionService();
//        DefaultConversionService.addDefaultConverters(conversionService);
//        conversionService.addConverter(String.class, Phone.class, (String source) -> Phone.of(HOME, source));
//        conversionService.addConverter(String.class, Email.class, (String source) -> Email.of(HOME, source));
////        conversionService.addConverter(String.class, Pupil.class, (String source) -> new Pupil(Long.parseLong(source)));
//        binder.setConversionService(conversionService);
//        binder.bind(pvs);
//        System.out.println(target);

//        ApplicationContext ctx = StaticUtils.getApplicationContext();
//        CsvLoaderController controller = new CsvLoaderController();
//        controller.setApplicationContext(ctx);
//        FileUploadOptionsForm fileUploadOptionsForm = StaticUtils.loadObjectFromFile("/home/vitaly/programs/apache-tomcat-8.0.21/bin/upload/Admin/optionForm");
//        FileOption fileOption = fileUploadOptionsForm.getFileOptionList().get(0);
//        final Path file = Paths.get("/home/vitaly/SkypeFiles/Guardians.csv");
//
//        FileSystemResource resource = new FileSystemResource(file.toFile());
//        FlatFileItemReader<Guardian> itemReader = new FlatFileItemReader<>();
//        itemReader.setResource(resource);
//
//        DefaultLineMapper<Guardian> lineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames(fileOption.getColumnNameList().toArray(new String[fileOption.getColumnNameList().size()]));
//        lineMapper.setLineTokenizer(tokenizer);
//        FieldSetMapper<Guardian> fieldMapper = new GuardianBinder();
//        lineMapper.setFieldSetMapper(fieldMapper);
//
//        itemReader.setLineMapper(lineMapper);
//        itemReader.setLinesToSkip(fileOption.getSkipRows());
//        itemReader.open(new ExecutionContext());
//        List<Guardian> result = new ArrayList<>();
//
//        Guardian g = null;
//        try {
//            while ((g = itemReader.read()) != null) {
//                result.add(g);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        DataBinder binder = new
////        List<Guardian> guardians = controller.parseFile(fileOption, file);
////        Set<String> properties = getBeanFieldsRecursively(Pupil.class);
////        System.out.println(properties);
    }

    public static <T extends JpaPersonalizedEntity> T  getPersonalizedEntity(Supplier<T> constructor, String personalId, String firstName, String lastName) {
        T entity = constructor.get();
        Person person = new Person(personalId, firstName, lastName);
        entity.setPerson(person);

        return entity;
    }

//    public static <T> void setVarargToCollection(Consumer<Collection<T>>  setter, T...vars) {
//        setter.accept();
//    }

    private static GenericXmlApplicationContext getApplicationContext() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:/spring/data.xml");
        ctx.refresh();
        return ctx;
    }

}

class GuardianBinder implements FieldSetMapper<Guardian> {

    @Override
    public Guardian mapFieldSet(FieldSet fieldSet) throws BindException {
        Guardian target = new Guardian();
        DataBinder binder = new DataBinder(target);
        PropertyValues pvs = new MutablePropertyValues(fieldSet.getProperties());
        binder.bind(pvs);
        return target;
    }
}