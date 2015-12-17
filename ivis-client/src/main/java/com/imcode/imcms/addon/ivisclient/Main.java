package com.imcode.imcms.addon.ivisclient;

import com.imcode.entities.Application;
import com.imcode.entities.Person;
import com.imcode.entities.Pupil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;

/**
 * Created by vitaly on 26.05.15.
 */
public class Main {

    private static StatmentHandler getStatementHandler() {
        return new StatmentHandler();
    }

    public static void main(String[] args) {
//        String fileName = "/home/vitaly/SkypeFiles/795.xml";
//
//    try {
//
//        SAXParserFactory factory = SAXParserFactory.newInstance();
//        SAXParser saxParser = factory.newSAXParser();
//
//        StatmentHandler handler = getStatementHandler();
//
//        saxParser.parse(fileName, handler);
//
//        System.out.println(handler.getApplication());
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
        int n = 98;
        for (int i = n; i > 0; i--) {
//            System.out.println(n%i);
            if (n % i == 0)
                System.out.println(i);
        }

    }
//    public static void main(String[] args) {
//        String fileName = "/home/vitaly/SkypeFiles/0.xml";
////        AuthorizationCodeResourceDetails
//    }

//    DefaultHandler handler = new DefaultHandler() {
//
//        boolean bfname = false;
//        boolean blname = false;
//        boolean bnname = false;
//        boolean bsalary = false;
//
//        public void startElement(String uri, String localName,String qName,
//                                 Attributes attributes) throws SAXException {
//
//            System.out.println("Start Element :" + qName);
//
//            if (qName.equalsIgnoreCase("FIRSTNAME")) {
//                bfname = true;
//            }
//
//            if (qName.equalsIgnoreCase("LASTNAME")) {
//                blname = true;
//            }
//
//            if (qName.equalsIgnoreCase("NICKNAME")) {
//                bnname = true;
//            }
//
//            if (qName.equalsIgnoreCase("SALARY")) {
//                bsalary = true;
//            }
//
//        }
//
//        public void endElement(String uri, String localName,
//                               String qName) throws SAXException {
//
//            System.out.println("End Element :" + qName);
//
//        }
//
//        public void characters(char ch[], int start, int length) throws SAXException {
//
//            if (bfname) {
//                System.out.println("First Name : " + new String(ch, start, length));
//                bfname = false;
//            }
//
//            if (blname) {
//                System.out.println("Last Name : " + new String(ch, start, length));
//                blname = false;
//            }
//
//            if (bnname) {
//                System.out.println("Nick Name : " + new String(ch, start, length));
//                bnname = false;
//            }
//
//            if (bsalary) {
//                System.out.println("Salary : " + new String(ch, start, length));
//                bsalary = false;
//            }
//
//        }
//
//    };
}

class StatmentHandler extends DefaultHandler {
    private Application application;
    private LinkedList<String> nodes = new LinkedList<>();
    private String fullElementName;
    private String elementName;


    @Override
    public void startDocument() throws SAXException {
        if (application != null) {
            throw new SAXException("This application handler " + this + " is allredy used, please create a new one.");
        }

        application = new Application();
//        application.setStatus(StatementStatus.created);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodes.add(qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String currentNode = nodes.getLast();
        String value = new String(ch, start, length);
        System.out.println(ch);

        if (nodes.contains("student") && "Personnummer".equalsIgnoreCase(currentNode)) {
            Person person = new Person(value, null, null);
            Pupil pupil = new Pupil();
            pupil.setPerson(person);
//            application.setPupil(pupil);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        nodes.removeLast();
    }

    public Application getApplication() {
        return application;
    }
}
