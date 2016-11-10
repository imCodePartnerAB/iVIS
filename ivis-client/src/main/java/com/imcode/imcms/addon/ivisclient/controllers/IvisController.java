package com.imcode.imcms.addon.ivisclient.controllers;

import com.imcode.entities.*;
import com.imcode.entities.embed.Decision;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.imcms.addon.ivisclient.controllers.form.ApplicationFormCmd;
import com.imcode.search.SearchCriteries;
import com.imcode.search.SearchOperation;
import com.imcode.services.*;
import imcode.server.Imcms;
import imcode.services.IvisServiceFactory;
import imcode.services.exceptionhandling.GeneralException;
import imcode.services.utils.IvisOAuth2Utils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by vitaly on 26.05.15.
 */
@Controller
@RequestMapping(value = "/ivis")
public class IvisController {
    Logger log = Logger.getLogger(IvisController.class.getName());
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

//    @Autowired
//    private IvisServiceFactory ivisServiceFactory;


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
                             @PathVariable("id") Long applicationId, @RequestParam("status") Decision.Status status) throws IOException {
//        IvisFacade ivis = IvisFacade.instance(new IvisFacade.Configuration.Builder()
//                .endPointUrl(serverAddress)
//                .responseType("json")
//                .version("v1").build());
//        DefaultIvisServiceFactory factory = ivis.getServiceFactory(client, IvisOAuth2Utils.getClientContext(request));
//        ApplicationService service = factory.getStatementService();

        ApplicationService service = getIvisServiceFactory(request).getService(ApplicationService.class);
        EntityVersionService entityVersionService = getIvisServiceFactory(request).getService(EntityVersionService.class);
        LogEventService logEventService = getIvisServiceFactory(request).getService(LogEventService.class);
//
        if (IvisOAuth2Utils.getAccessToken(request) != null) {
            try {
                Application application = service.find(applicationId);
//
                if (application != null && application.getDecision() != null) {
                    if (!application.getDecision().getStatus().equals(status)){
                        Date dateNow = new Date();
                        application.getDecision().setStatus(status);
                        application.getDecision().setDate(dateNow);
                        application.setUpdateDate(dateNow);

                        EntityVersion version = new EntityVersion(application);
                        entityVersionService.save(version);

                        service.save(application);
                    }

                }
            } catch (UserRedirectRequiredException e) {
                IvisOAuth2Utils.setAccessToken(request, null);
            }
        }

        response.sendRedirect(getRequestReferer(request));

//        return id + ":" + status.toString();
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public void editApplication(HttpServletRequest request,
                                HttpServletResponse response,
                                @PathVariable("id") Long applicationId, @ModelAttribute("applicationForm") ApplicationFormCmd applicationFormCmd, BindingResult bindingResult) throws IOException {

        final IvisServiceFactory ivisServiceFactory = getIvisServiceFactory(request);
        ApplicationService service = ivisServiceFactory.getService(ApplicationService.class);
        ApplicationFormQuestionService questionService = ivisServiceFactory.getService(ApplicationFormQuestionService.class);
//        ApplicationFormService formService = getIvisServiceFactory(request).getService(ApplicationFormService.class);
        EntityVersionService versionService = ivisServiceFactory.getService(EntityVersionService.class);
        LogEventService logEventService = ivisServiceFactory.getService(LogEventService.class);

        Application application = service.find(applicationId);

        List<ApplicationFormQuestion> persistedQuestions = application.getQuestionList();
        Collections.sort(persistedQuestions, JpaEntity.BY_ID_COMPARATOR);


        if (IvisOAuth2Utils.getAccessToken(request) != null) {
            try {
                boolean changed = false;
                List<ApplicationFormQuestion> questions = new ArrayList<>();
                for (ApplicationFormQuestion question :applicationFormCmd.getQuestions()) {
//                    ApplicationFormQuestion realQuestion = questionService.find(question.getId());
                    int questionIndex = Collections.binarySearch(persistedQuestions, question, JpaEntity.BY_ID_COMPARATOR);
                    if (questionIndex >= 0) {
                        ApplicationFormQuestion realQuestion = persistedQuestions.get(questionIndex);
                        if (realQuestion.getMultiValues() && !Objects.equals(realQuestion.getValues(), question.getValues())) {
                            realQuestion.setValues(question.getValues());
                            realQuestion.setValue(question.getValues().stream().collect(Collectors.joining()));
                            questionService.save(realQuestion);
                            changed = true;
                        } else if (!realQuestion.getMultiValues() && !Objects.equals(realQuestion.getValue(), question.getValue())) {
                            realQuestion.setValue(question.getValue());
                            realQuestion.setValues(Collections.singletonList(question.getValue()));
                            questions.add(realQuestion);
                            changed = true;
                        }
                    }
//                    if (question.getValue())
                }


                if (changed) {
                    Date date = new Date();
                    application.setUpdateDate(date);

                    Decision decision = application.getDecision();
                    decision.setDate(date);
                    decision.setStatus(Decision.Status.HANDLED);
                    application.setDecision(decision);

                    EntityVersion version = new EntityVersion(application);
                    version = versionService.save(version);

                    LogEvent log = logEventService.findByEntity(application).get(0);
                    log.setTimestamp(version.getTimestamp());
                    log.setAction(LogEvent.Action.MODIFY);

                    log.setId(null);

                    logEventService.save(log);


                    service.save(application);
                    Iterable<ApplicationFormQuestion> savedQuestions = questionService.save(questions);
//                    int count = 1; // dummy :)
//                    ApplicationForm applicationForm = application.getApplicationForm();
//                    formService.save(applicationForm);
                }
            } catch (UserRedirectRequiredException e) {
                IvisOAuth2Utils.setAccessToken(request, null);
            }
        }

        response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/applications/show?id=" + applicationId);
//
//        return id + ":" + status.toString();
    }

    @RequestMapping(value = "commentDecision/{id}", method = RequestMethod.POST)
    public void addDecisionComment(HttpServletRequest request,
                                HttpServletResponse response,
                                @PathVariable("id") Long applicationId) throws IOException {


        if (IvisOAuth2Utils.getAccessToken(request) != null) {
            String commentTextarea = request.getParameter("commentTextarea");
             if (!isEmptyOrNull(commentTextarea)) {
                 final IvisServiceFactory ivisServiceFactory = getIvisServiceFactory(request);
                 ApplicationService service = ivisServiceFactory.getService(ApplicationService.class);
                 Application application = service.find(applicationId);
                 Decision decision = application.getDecision();
                 decision.setComment(!decision.getComment().isEmpty() ? decision.getComment() + "<br/>" + commentTextarea : commentTextarea);
                 application.setDecision(decision);
                 service.save(application);
             }
        }

        response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/applications/show?id=" + applicationId);





    }

    private boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }

        @Deprecated
    @RequestMapping(value = "/xml", method = RequestMethod.POST)
    public void importApplication(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam(value = "file", required = false) MultipartFile file,
//                                    @RequestParam("body") String body,
                                  Model model) throws IOException, URISyntaxException {

//        InputStream inputStream = file.getInputStream();
//        Application application = pharseXml(inputStream);
//
//        if (application == null) {
//            throw new RuntimeException("Unknown xml format");
//        }
//
//        if (IvisOAuth2Utils.getAccessToken(request) != null) {
//
////            IvisFacade ivis = IvisFacade.instance(new IvisFacade.Configuration.Builder()
////                    .endPointUrl(serverAddress)
////                    .responseType("json")
////                    .version("v1").build());
////            DefaultIvisServiceFactory factory = ivis.getServiceFactory(client, IvisOAuth2Utils.getClientContext(request));
//            ApplicationService applicationService = getIvisServiceFactory(request).getService(ApplicationService.class);
//            PupilService pupilService = getIvisServiceFactory(request).getService(PupilService.class);
//
//            try {
////                application = new Application();
////                application.setStatus(StatementStatus.created);
//                if (application.getPupil() != null) {
//                    Pupil pupil = pupilService.findByPersonalId(application.getPupil().getPerson().getPersonalId());
//                    application.setPupil(pupil);
//                }
//                applicationService.save(application);
//                model.asMap().clear();
//                model.addAttribute("message", new Message(MessageType.SUCCESS, "SUCCESS"));
//            } catch (Exception e) {
//                model.addAttribute("message", new Message(MessageType.ERROR, "ERROR"));
//            }
//        } else {
//            response.sendRedirect(IvisOAuth2Utils.getOAuth2AuthirizationUrl((AuthorizationCodeResourceDetails) client, tokenHandler));
//            return;
//        }
//
//        response.sendRedirect(clientAddress + "/servlet/AdminDoc?meta_id=1005");
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
        PupilService pupilService = getIvisServiceFactory(request).getService(PupilService.class);
        PersonService personService = getIvisServiceFactory(request).getService(PersonService.class);
        GuardianService guardianService = getIvisServiceFactory(request).getService(GuardianService.class);

        if (pupil.getPerson() != null) {
            personService.save(pupil.getPerson());
        }

        if (pupil.getContactPerson() != null) {
            personService.save(pupil.getContactPerson());
        }

        if (pupil.getGuardians() != null) {
            for (Guardian guardian : pupil.getGuardians()) {
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
    public void updateApplication(@ModelAttribute("app") Application application,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {

//        PupilService pupilService = getIvisServiceFactory(request).getService(PupilService.class);
//        PersonService personService = getIvisServiceFactory(request).getService(PersonService.class);
//        GuardianService guardianService = getIvisServiceFactory(request).getService(GuardianService.class);
        ApplicationService applicationService = getIvisServiceFactory(request).getService(ApplicationService.class);

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

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        IncidentService incidentService = getIvisServiceFactory(request).getService(IncidentService.class);
//
//        List<Incident> personList = incidentService.search(
//                SearchCriteries.orderedSelect("title", SearchCriteries.Order.ASC)
//                .whereOr("description", SearchOperation.STARTS_WITH, "Vivamus")
//                .whereOr("title", SearchOperation.CONTAINS, "ning")
//                .where("title", SearchOperation.ENDS_WITH, "jer")
//        );
//        response.sendRedirect(request.getContextPath());
//    }

    @RequestMapping(value = "/errorhandler")
    public void handleError(HttpServletRequest request, HttpServletResponse response) {

        Object error = request.getAttribute("javax.servlet.error.exception");

        if (error instanceof GeneralException) {
            try {
                GeneralException generalException = (GeneralException) error;
                URIBuilder uriBuilder = new URIBuilder(serverAddress + "/errorhandler");
                uriBuilder.addParameter("body", null);
                uriBuilder.addParameter("error_message", generalException.getErrorMessage());
                uriBuilder.addParameter("error_code", generalException.getErrorCode().toString());
                String collect = generalException.getErrorDescription().stream().collect(Collectors.joining(","));
                uriBuilder.addParameter("error_description", collect);
                response.sendRedirect(uriBuilder.build().toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }


    }

    private String getRequestReferer(HttpServletRequest request) {
        return request.getHeader("referer");
    }

    private void checkServiceFactory(HttpServletRequest request) {
        if (getIvisServiceFactory(request) == null) {

        }
    }

    private IvisServiceFactory getIvisServiceFactory(HttpServletRequest request) {
        return IvisOAuth2Utils.getServiceFactory(request);
    }

    private void saveApplication(Application application, IvisServiceFactory ivisServiceFactory) {
//        ivisServiceFactory = ApplicationPopulator.localIvisServiceFactory();
        ApplicationService applicationService = ivisServiceFactory.getService(ApplicationService.class);
        ApplicationFormService formService = ivisServiceFactory.getService(ApplicationFormService.class);
        ApplicationFormStepService stepService = ivisServiceFactory.getService(ApplicationFormStepService.class);
        ApplicationFormQuestionGroupService groupService = ivisServiceFactory.getService(ApplicationFormQuestionGroupService.class);
        ApplicationFormQuestionService questionService = ivisServiceFactory.getService(ApplicationFormQuestionService.class);
        ApplicationForm form = application.getApplicationForm();

        List<ApplicationFormStep> steps = form.getSteps();
        for (int s = 0; s < steps.size(); s++) {
            ApplicationFormStep step = steps.get(s);
            List<ApplicationFormQuestionGroup> questionGroups = step.getQuestionGroups();
            for (int g = 0; g < questionGroups.size(); g++) {
                ApplicationFormQuestionGroup questionGroup = questionGroups.get(g);
                List<ApplicationFormQuestion> questions = questionGroup.getQuestions();
                for (int q = 0; q < questions.size(); q++) {
                    ApplicationFormQuestion question = questions.get(q);
                    question = questionService.save(question);
                    questions.set(q, question);
                }
                questionGroup = groupService.save(questionGroup);
                questionGroups.set(g, questionGroup);
            }
            step = stepService.save(step);
            steps.set(s, step);
        }
        form = formService.save(form);
        application.setApplicationForm(form);
        application = applicationService.save(application);
        log.info("Application id = " + application.getId() + " created.");
    }

}
