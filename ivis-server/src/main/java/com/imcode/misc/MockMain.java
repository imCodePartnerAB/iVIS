package com.imcode.misc;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.JdbcTemplate;
//import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;

/**
 * Created by vitaly on 24.03.15.
 */
public class MockMain {
    public static void main(String[] args) throws IOException {
//        String s = new MockMain().getFileWithUtil("sql/oauth2_data.sql");
//        System.out.println(s);
    }


    public static String readSqlFile(String path) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        while (reader.ready()) {
            stringBuilder.append(reader.readLine());
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    public String getFileWithUtil(String fileName) {

        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
