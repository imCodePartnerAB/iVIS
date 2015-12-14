package com.imcode.utils;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.*;

/**
 * Created by vitaly on 09.12.15.
 */
public class StaticUtils {
    public static void saveObjectToFile(Object obj, String fileName) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            stream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static<T> T loadObjectFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));) {
            return (T) inputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static GenericXmlApplicationContext getApplicationContext() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:/spring/data.xml");
        ctx.refresh();
        return ctx;
    }
}
