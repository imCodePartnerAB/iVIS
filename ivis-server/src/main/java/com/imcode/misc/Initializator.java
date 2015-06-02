package com.imcode.misc;

import com.imcode.entities.Role;
import com.imcode.entities.Statement;
import com.imcode.entities.User;
import com.imcode.entities.enums.AuthorizedGrantType;
import com.imcode.entities.enums.StatementStatus;
import com.imcode.entities.oauth2.ClientRole;
import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.repositories.RoleRepository;
import com.imcode.repositories.StatementRepository;
import com.imcode.repositories.UserRepository;
import com.imcode.repositories.oauth2.ClientRoleRepository;
import com.imcode.repositories.oauth2.ClietnDetailsRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * Created by vitaly on 23.03.15.
 */
@Service
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
    private StatementRepository statementRepository;

    @Value("${Hibernate.dialect}")
    private String test;

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private Aut userDetailsManager;

    @PostConstruct
    public void init() {
        intializeSecurityJpa();
        intializeOAuth2Jpa();
        initializeToken();
        initializeStatementJpa();

    }

    private void initializeStatementJpa() {
        List<Statement> statementList = statementRepository.findAll();

        if (statementList.size() == 0) {
            Random random = new Random();

            for (int i = 0; i < 5; i++) {
                Statement statement = new Statement();
                statement.setId(Long.valueOf(i));
                statement.setStatus(StatementStatus.values()[random.nextInt(StatementStatus.values().length)]);
                statementRepository.save(statement);
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

//            Create basic users
            logger.info("Creating basic 4 users...");
            users = new LinkedList<>();

            users.add(new User("admin", "pass", roleAdmin, roleUser, roleDeveloper));
            users.add(new User("ivis", "111", roleUser, roleDeveloper));
            users.add(new User("user", "111", roleUser));
            users.add(new User("vitaly", "", roleDeveloper));

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
//            clientDetails.setClientId("ee4b69d1-e375-4612-a22f-812a5ecacd35");
            clientDetails.setClientSecret("secret");
            clientDetails.setScope("read", "write", "execute");
            clientDetails.setResourceIds("ivis");
//            clientDetails.setRegisteredRedirectUri("http://localhost:8083/web2/ivis/redirect");
            clientDetails.setOwner(userRepository.findByUsername("ivis"));
            clientDetails.setAuthorities(clientRoleRepository.findAll());
            clientDetails.setAuthorizedGrantTypes(AuthorizedGrantType.AUTHORIZATION_CODE);
            clientDetails.setAccessTokenValiditySeconds(60);
            clientDetails.setRefreshTokenValiditySeconds(600);
            clietnDetailsRepository.save(clientDetails);

            clientDetails = new JpaClientDetails();
            clientDetails.setName("admin");
//            clientDetails.setClientId("477263aa-2193-49ca-ac2e-1a2f043c48a7");
            clientDetails.setClientSecret("secret");
            clientDetails.setScope("read", "write", "execute");
            clientDetails.setResourceIds("ivis");
//            clientDetails.setRegisteredRedirectUri("http://localhost:8083/web2/ivis/redirect");
            clientDetails.setOwner(userRepository.findByUsername("admin"));
            clientDetails.setAuthorities(clientRoleRepository.findAll());
            clientDetails.setAuthorizedGrantTypes(
                    AuthorizedGrantType.AUTHORIZATION_CODE,
                    AuthorizedGrantType.CLIENT_CREDENTIALS,
                    AuthorizedGrantType.IMPLICIT,
                    AuthorizedGrantType.PASSWORD,
                    AuthorizedGrantType.REFRESH_TOKEN);
            clientDetails.setAccessTokenValiditySeconds(600);
            clientDetails.setRefreshTokenValiditySeconds(6000);
            clietnDetailsRepository.save(clientDetails);
            logger.info("Creating ClientDetails successful");
        }
    }

}

