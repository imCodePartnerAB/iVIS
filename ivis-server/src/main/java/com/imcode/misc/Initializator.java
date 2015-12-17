package com.imcode.misc;

import com.imcode.entities.Application;
import com.imcode.entities.Person;
import com.imcode.entities.Role;
import com.imcode.entities.User;
import com.imcode.entities.enums.AuthorizedGrantType;
import com.imcode.entities.oauth2.ClientRole;
import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.repositories.*;
import com.imcode.repositories.oauth2.ClientRoleRepository;
import com.imcode.repositories.oauth2.ClietnDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * Created by vitaly on 23.03.15.
 */
@SuppressWarnings({"deprecation"})
//@Service
public class Initializator {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private IvisClientDetailsService clientDetailsService;

    @Autowired
    private ClietnDetailsRepository clietnDetailsRepository;

    @Autowired
    private ClientRoleRepository clientRoleRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private AcademicYearRepository academicYearRepository;
//
//    @Autowired
//    private ;
//
//    @Autowired
//    private ;
//
//    @Autowired
//    private ;
//
//    @Autowired
//    private ;
//
//    @Autowired
//    private ;
//
//    @Autowired
//    private ;

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private Aut userDetailsManager;

//    @PostConstruct
    public void init() {
//        intializeSecurityJpa();
//        intializeOAuth2Jpa();
//        initializeToken();
////        initializeStatementJpa();
//////        initializePersonJpa();
//        initializePupilJpa();

    }

    private void initializePupilJpa() {
//        List<Pupil> PupilList = pupilRepository.findAll();
//
//        if (PupilList.size() == 0) {
////            Person
//            Person person = new Person();
//            person.setId(1L);
//            person.setFirstName("Vitaliy");
//            person.setLastName("Sereda");
//            person.setPersonalId("850717-5019");
//            person = personRepository.save(person);
//
//
//
////            School
//            School school = new School();
//            school.setName("School #1");
//            school.setServices(ServiceTypeEnum.AFTER_SCHOOL_CENTER, ServiceTypeEnum.ELEMENTARY_SCHOOL, ServiceTypeEnum.SECONDARY_SCHOOL);
//            school = schoolRepository.save(school);
//
////            SchoolClass
//            SchoolClass schoolClass = new SchoolClass("A1-1", school, new Date(0, 0, 0, 8, 0), new Date(0, 0, 0, 15, 0));
//            schoolClass = schoolClassRepository.save(schoolClass);
//            SchoolClass schoolClass1 = new SchoolClass("A2-1", school, new Date(0, 0, 0, 12, 0), new Date(0, 0, 0, 18, 0));
//            schoolClass1 = schoolClassRepository.save(schoolClass1);
//
//            AcademicYear academicYear = new AcademicYear("2014-2015");
//            academicYear = academicYearRepository.save(academicYear);
//
////            Pupil
//            Pupil pupil = new Pupil();
//            pupil.setPerson(person);
//            pupil.setSchoolClass(schoolClass);
//            pupil.setAcademicYear(academicYear);
//            pupil = pupilRepository.save(pupil);
//        }
    }

    private void initializePersonJpa() {

    }

    private void initializeStatementJpa() {
        List<Application> applicationList = applicationRepository.findAll();

        if (applicationList.size() == 0) {
            Random random = new Random();

            for (int i = 0; i < 5; i++) {
                Application application = new Application();
                application.setId(Long.valueOf(i));
//                application.setStatus(StatementStatus.values()[random.nextInt(StatementStatus.values().length)]);
                applicationRepository.save(application);
            }
        }
    }

    private void initializeToken() {

    }

    private void intializeSecurity() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            String sqlCheck = "(select authority from authorities  LIMIT 0, 1)UNION (SELECT  username FROM users limit 0, 1)";
            logger.info("Check Spring security tables...");
            List<String> result = jdbcTemplate.queryForList(sqlCheck, String.class);
            if (result.size() < 2)
                throw new Exception();
            logger.info("Spring security tables is already exist.");
        } catch (Exception e) {

//            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            try {
                logger.info("Creating Spring security tables");
                JdbcTestUtils.executeSqlScript(jdbcTemplate, resourceLoader, "classpath:sql/security_schema.ddl", false);
                logger.info("Creating Spring security tables successful");

                logger.info("Filling Spring security tables");
                JdbcTestUtils.executeSqlScript(jdbcTemplate, resourceLoader, "classpath:sql/security_data.sql", false);
                logger.info("Filling Spring security tables successful");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }


    }

    private void intializeSecurityJpa() {
        logger.info("Check Spring security tables...");
        List<Role> roles = roleRepository.findAll();
        List<User> users = userRepository.findAll();

        if (users.size() == 0 || roles.size() == 0) {
            logger.info("User table has " + users.size() + " records");
            logger.info("Role table has " + roles.size() + " records");
            roleRepository.delete(roles);
            userRepository.delete(users);

//            Create basic roles
            logger.info("Creating basic 4 roles...");
            Role roleAnonymous = roleRepository.save(new Role("ROLE_ANONYMOUS"));
            Role roleAdmin = roleRepository.save(new Role("ROLE_ADMIN"));
            Role roleUser = roleRepository.save(new Role("ROLE_USER"));
            Role roleDeveloper = roleRepository.save(new Role("ROLE_DEVELOPER"));

//            Create new persons
            Person adminPerson = personRepository.save(new Person("1111", "John", "Admin"));
            Person ivisPerson = personRepository.save(new Person("2222", "Henry", "Ivis"));
            Person userPerson = personRepository.save(new Person("3333", "Sergey", "User"));
            Person vitalyPerson = personRepository.save(new Person("4444", "Vitaly", "Orlov"));

//            Create basic users
            logger.info("Creating basic 4 users...");
            users = new LinkedList<>();
            User user = new User("admin", "pass", roleAdmin, roleUser, roleDeveloper);
            user.setPerson(adminPerson);
            users.add(user);

            user = new User("ivis", "111", roleUser, roleDeveloper);
            user.setPerson(ivisPerson);
            users.add(user);

            user = new User("user", "111", roleUser);
            user.setPerson(userPerson);
            users.add(user);

            user = new User("vitaly", "", roleDeveloper);
            user.setPerson(vitalyPerson);
            users.add(user);


            userRepository.save(users);

        }
    }

    private void intializeOAuth2() {
        try {
            logger.info("Check OAuth2 tables...");
            clientDetailsService.listClientDetails();
            logger.info("OAuth2 tables is already exist.");
        } catch (Exception e) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            try {
                logger.info("Creating OAuth2 tables");
                JdbcTestUtils.executeSqlScript(jdbcTemplate, resourceLoader, "classpath:sql/oauth2_schema.ddl", false);
                logger.info("Creating OAuth2 tables successful");

                logger.info("Filling OAuth2 tables");
                JdbcTestUtils.executeSqlScript(jdbcTemplate, resourceLoader, "classpath:sql/oauth2_data.sql", false);
                logger.info("Filling OAuth2 tables successful");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void intializeOAuth2Jpa() {
        logger.info("Check OAuth2 tables...");
        List<ClientRole> roles = clientRoleRepository.findAll();
        List<JpaClientDetails> clientDetailsList = clientDetailsService.findAll();

        if (roles.size() == 0 || clientDetailsList.size() == 0) {
            clientRoleRepository.delete(roles);
            clietnDetailsRepository.delete(clientDetailsList);

            logger.info("ClientRole table has " + roles.size() + " records");
            logger.info("ClientDetails table has " + clientDetailsList.size() + " records");
            logger.info("Creating basic 2 roles...");

//            ClientRoles
            ClientRole role_admin = clientRoleRepository.save(new ClientRole("ROLE_ADMIN"));
            ClientRole role_user = clientRoleRepository.save(new ClientRole("ROLE_USER"));
            logger.info("Creating ClientRoles successful");

//            ClientDetails
            logger.info("Creating basic 4 ClientDetails...");
            JpaClientDetails clientDetails;

            clientDetails = new JpaClientDetails();
            clientDetails.setName("ivis");

//            clientDetails.setClientId("b4251265-409d-43b3-928d-a290228a2b59");
            clientDetails.setClientSecret("secret");
            clientDetails.setScope("read", "write", "execute");
            clientDetails.setResourceIds("ivis");
//            clientDetails.setRegisteredRedirectUri("http://localhost:8083/web2/ivis/redirect");
            clientDetails.setOwner(userRepository.findByUsername("ivis"));
            clientDetails.setAuthoritiesOverload(clientRoleRepository.findAll());
            clientDetails.setAuthorizedGrantTypes(AuthorizedGrantType.authorization_code);
            clientDetails.setAccessTokenValiditySeconds(60);
            clientDetails.setRefreshTokenValiditySeconds(600);
            clietnDetailsRepository.save(clientDetails);
//            clientDetails.setClientId("ee4b69d1-e375-4612-a22f-812a5ecacd35");
//            clietnDetailsRepository.save(clientDetails);

            clientDetails = new JpaClientDetails();
            clientDetails.setName("admin");
//            clientDetails.setClientId("b4251265-409d-43b3-928d-a290228a2b59");
            clientDetails.setClientSecret("secret");
            clientDetails.setScope("read", "write", "execute");
            clientDetails.setResourceIds("ivis");
//            clientDetails.setRegisteredRedirectUri("http://localhost:8083/web2/ivis/redirect");
            clientDetails.setOwner(userRepository.findByUsername("admin"));
            clientDetails.setAuthoritiesOverload(clientRoleRepository.findAll());
            clientDetails.setAuthorizedGrantTypes(
                    AuthorizedGrantType.authorization_code,
                    AuthorizedGrantType.client_credentials,
                    AuthorizedGrantType.implicit,
                    AuthorizedGrantType.password,
                    AuthorizedGrantType.refresh_token);
            clientDetails.setAccessTokenValiditySeconds(600);
            clientDetails.setRefreshTokenValiditySeconds(6000);
            clietnDetailsRepository.save(clientDetails);
//            clientDetails.setClientId("b4251265-409d-43b3-928d-a290228a2b59");
//            clietnDetailsRepository.save(clientDetails);

            logger.info("Creating ClientDetails successful");
        }
    }

}

