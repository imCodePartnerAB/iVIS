package com.imcode;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.UUID;

/**
 * Created by vitaly on 17.02.15.
 */
public class MainTest {
    public static void main(String[] args) {
//        System.out.println("Hello!");
//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("src/main/webapp/WEB-INF/spring/app-context-xml.xml");
//        ctx.refresh();
        UUID uuid = UUID.randomUUID();
        printInfo(uuid);
        uuid = UUID.nameUUIDFromBytes("Я ходил на улицу и ловил жуков".getBytes());
        printInfo(uuid);
        uuid = new UUID(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
        printInfo(uuid);
        uuid = new UUID(16 * 16 - 1, 16 * 16 - 1);
        printInfo(uuid);
    }

    private static void printInfo(UUID uuid) {
        System.out.println(uuid);
        System.out.println(uuid.version());
        System.out.println(uuid.variant());
    }

}
