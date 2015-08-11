package com.imcode.imcms.addon.ivisclient.controllers;

import com.imcode.entities.Application;
import com.imcode.entities.Guardian;
import com.imcode.entities.Person;
import com.imcode.entities.Pupil;
import com.imcode.entities.enums.StatementStatus;
import com.imcode.imcms.addon.ivisclient.controllers.form.Message;
import com.imcode.imcms.addon.ivisclient.controllers.form.MessageType;
import com.imcode.services.ApplicationService;
import com.imcode.services.GuardianService;
import com.imcode.services.PersonService;
import com.imcode.services.PupilService;
import imcode.services.IvisServiceFactory;
import imcode.services.utils.IvisOAuth2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.LinkedList;

/**
 * Created by vitaly on 26.05.15.
 */
@Controller
@RequestMapping(value = "/ivis")
public class IvisController {
    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private OAuth2ProtectedResourceDetails client;


    public static final String GRANT_TYPE = "authorization_code";
    @Value("${AuthorizationCodeHandlerUri}")
    public String tokenHandler;// = Imcms.getServerProperties().getProperty("AuthorizationCodeHandlerUri");

    @Value("${StatementsAddress}")
    private String statementsAddress;

    @Value("${ServerAddress}")
    private String serverAddress;

    @Value("${ClientAddress}")
    private String clientAddress;

    @Autowired
    private IvisServiceFactory ivisServiceFactory;


//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder, HttpServletRequest servletRequest, WebRequest webRequest) {
////        System.out.println("sdfas");
//    }

    @RequestMapping(value = "/code")
    @ResponseBody
    public String getCode(WebRequest webRequest,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "code", required = false) String code,
                          @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                          @RequestParam(value = "docId", required = false) String docId
    ) throws URISyntaxException, IOException {

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", GRANT_TYPE);
        form.add("code", code);
        form.add("redirect_uri", tokenHandler);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",
                String.format("Basic %s", new String(Base64.encode(String.format("%s:%s", client.getClientId(), client.getClientSecret()).getBytes("UTF-8")), "UTF-8")));
        HttpEntity httpEntity = new HttpEntity(form, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OAuth2AccessToken> result = restTemplate.postForEntity(client.getAccessTokenUri(), httpEntity, OAuth2AccessToken.class);

        IvisOAuth2Utils.setAccessToken(request, result.getBody());

//        IvisOAuth2Utils.createServiceFactory(request.getSession(), client, serverAddress);
//        response.sendRedirect(statementsAddress);
        response.sendRedirect(clientAddress + "/show.jsp");

        return result.getBody().toString();
    }

    @RequestMapping(value = "/token")
    @ResponseBody
    public String getToken(HttpServletResponse response) throws IOException {
        String s = "sdfas";
        response.sendRedirect(clientAddress + "/show.jsp");
        return s;
    }

    @RequestMapping(value = "/{id}", params = {"status"}, method = RequestMethod.GET)
    public void updateStatus(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("id") Application application, @RequestParam("status") StatementStatus status) throws IOException {
//        IvisFacade ivis = IvisFacade.instance(new IvisFacade.Configuration.Builder()
//                .endPointUrl(serverAddress)
//                .responseType("json")
//                .version("v1").build());
//        DefaultIvisServiceFactory factory = ivis.getServiceFactory(client, IvisOAuth2Utils.getClientContext(request));
//        ApplicationService service = factory.getStatementService();

        ApplicationService service = ivisServiceFactory.getService(ApplicationService.class);
//
        if (IvisOAuth2Utils.getAccessToken(request) != null) {
            try {
//                Application application = service.find(id);
//
                if (application != null) {
                    application.setStatus(status);

                    service.save(application);

                }
            } catch (UserRedirectRequiredException e) {
                IvisOAuth2Utils.setAccessToken(request, null);
            }
        }

        response.sendRedirect(getRequestReferer(request));

//        return id + ":" + status.toString();
    }

    @RequestMapping(value = "/xml", method = RequestMethod.POST)
    public void importApplication(HttpServletRequest request,
                                    HttpServletResponse response,
                                  @RequestParam(value = "file", required = false) MultipartFile file,
//                                    @RequestParam("body") String body,
                                    Model model) throws IOException, URISyntaxException {

        InputStream inputStream = file.getInputStream();
        Application application = pharseXml(inputStream);

        if (application == null) {
            throw new RuntimeException("Unknown xml format");
        }

        if (IvisOAuth2Utils.getAccessToken(request) != null) {

//            IvisFacade ivis = IvisFacade.instance(new IvisFacade.Configuration.Builder()
//                    .endPointUrl(serverAddress)
//                    .responseType("json")
//                    .version("v1").build());
//            DefaultIvisServiceFactory factory = ivis.getServiceFactory(client, IvisOAuth2Utils.getClientContext(request));
            ApplicationService applicationService = ivisServiceFactory.getService(ApplicationService.class);
            PupilService pupilService = ivisServiceFactory.getService(PupilService.class);

            try {
//                application = new Application();
//                application.setStatus(StatementStatus.created);
                if (application.getPupil() != null) {
                    Pupil pupil = pupilService.findByPersonalId(application.getPupil().getPerson().getPersonalId());
                    application.setPupil(pupil);
                }
                applicationService.save(application);
                model.asMap().clear();
                model.addAttribute("message", new Message(MessageType.SUCCESS, "SUCCESS"));
            } catch (Exception e) {
                model.addAttribute("message", new Message(MessageType.ERROR, "ERROR"));
            }
        } else {
            response.sendRedirect(IvisOAuth2Utils.getOAuth2AuthirizationUrl((AuthorizationCodeResourceDetails) client, tokenHandler));
            return;
        }

        response.sendRedirect(clientAddress + "/servlet/AdminDoc?meta_id=1005");
//        servlet/AdminDoc?meta_id=1005
//        return "xml/show";
    }

    @RequestMapping(value = "/xml", method = RequestMethod.GET)
    @ResponseBody
    public String showImportApplicationForm() {

        return "xml/show";
    }

    @RequestMapping(value = "/pupils", method = RequestMethod.POST)
//    @ResponseBody
    public void updatePupil(@ModelAttribute("pupil") Pupil pupil,
//                              @PathVariable("pupilId") Pupil persistedPupil,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        PupilService pupilService = ivisServiceFactory.getService(PupilService.class);
        PersonService personService = ivisServiceFactory.getService(PersonService.class);
        GuardianService guardianService = ivisServiceFactory.getService(GuardianService.class);

        if (pupil.getPerson() != null) {
            personService.save(pupil.getPerson());
        }

        if (pupil.getContactPerson() != null) {
            personService.save(pupil.getContactPerson());
        }

        if (pupil.getGuardians() != null) {
            for (Guardian guardian :pupil.getGuardians()) {
                if (guardian != null) {
//                    if (guardian.getPerson() != null) {
//                        personService.save(guardian.getPerson());
//                    }
                    guardianService.save(guardian);
                }
            }

        }

        pupilService.save(pupil);
        String returnToUri = getRequestReferer(request);
        response.sendRedirect(returnToUri);

//        return "OK";
    }
    @RequestMapping(value = "/applications", method = RequestMethod.POST)
//    @ResponseBody
    public void updateApplication(@ModelAttribute("app") Application application,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

//        PupilService pupilService = ivisServiceFactory.getService(PupilService.class);
//        PersonService personService = ivisServiceFactory.getService(PersonService.class);
//        GuardianService guardianService = ivisServiceFactory.getService(GuardianService.class);
        ApplicationService applicationService = ivisServiceFactory.getService(ApplicationService.class);

//        if (application.getPerson() != null) {
//            personService.save(application.getPerson());
//        }
//
//        if (application.getContactPerson() != null) {
//            personService.save(application.getContactPerson());
//        }
//
//        if (application.getGuardians() != null) {
//            for (Guardian guardian : application.getGuardians()) {
//                if (guardian != null) {
////                    if (guardian.getPerson() != null) {
////                        personService.save(guardian.getPerson());
////                    }
//                    guardianService.save(guardian);
//                }
//            }
//
//        }

        applicationService.save(application);
        String returnToUri = getRequestReferer(request);
        response.sendRedirect(returnToUri);

//        return "OK";
    }

    private String getRequestReferer(HttpServletRequest request) {
        return request.getHeader("referer");
    }

    private static Application pharseXml(InputStream inputStream) {

        StatmentHandler handler = new StatmentHandler();

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(inputStream, handler);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return handler.getApplication();
    }

   private static class StatmentHandler extends DefaultHandler {
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
            application.setStatus(StatementStatus.created);
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
                application.setPupil(pupil);
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
}
