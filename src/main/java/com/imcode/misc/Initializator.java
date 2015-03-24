package com.imcode.misc;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
import java.util.List;


/**
 * Created by vitaly on 23.03.15.
 */
@Service
public class Initializator {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcClientDetailsService clientDetailsService;

    @Autowired
    private ResourceLoader resourceLoader;

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private Aut userDetailsManager;

    @PostConstruct
    public void init() {
        intializeSecurity();
        intializeOAuth2();
    }

    ;

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
}

