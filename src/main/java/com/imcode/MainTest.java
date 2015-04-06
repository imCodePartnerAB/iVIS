package com.imcode;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.UUID;

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

    public static void main(String[] args) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            String result = restTemplate.getForObject("http://client:8083/web2/ivis/hastoken", String.class);
            System.out.println(result);
//            if (result) {
//
//            } else {
//
//            }
        } catch (Exception ignore) { }
    }
}
