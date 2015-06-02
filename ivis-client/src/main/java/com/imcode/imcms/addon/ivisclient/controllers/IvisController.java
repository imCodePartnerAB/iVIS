package com.imcode.imcms.addon.ivisclient.controllers;

import com.imcode.entities.Statement;
import com.imcode.entities.enums.StatementStatus;
import com.imcode.imcms.addon.ivisclient.controllers.form.Message;
import com.imcode.imcms.addon.ivisclient.controllers.form.MessageType;
import com.imcode.services.StatementService;
import imcode.services.restful.IvisFacade;
import imcode.services.restful.IvisServiceFactory;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.net.URISyntaxException;

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
    //    todo Заинжектить значение мз placeholder
    @Value("${AuthorizationCodeHandlerUri}")
    public String tokenHandler;// = Imcms.getServerProperties().getProperty("AuthorizationCodeHandlerUri");

    @Value("${StatementsAddress}")
    private String statementsAddress;

    @Value("${ServerAddress}")
    private String serverAddress;

    @Value("${ClientAddress}")
    private String clientAddress;


    @RequestMapping(value = "/code")
    @ResponseBody
    public String getCode(WebRequest webRequest,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "code", required = false) String code,
                          @RequestParam(value = "redirect_uri", required = false) String redirectUri
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
        response.sendRedirect(statementsAddress);

        return result.getBody().toString();
    }

    @RequestMapping(value = "/token")
    @ResponseBody
    public String getToken() {
        String s = "sdfas";
        return s;
    }

    @RequestMapping(value = "/{id}", params = {"status"}, method = RequestMethod.POST)
    @ResponseBody
    public String updateStatus(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("id") Long id, @RequestParam("status") StatementStatus status) throws IOException {
        IvisFacade ivis = IvisFacade.instance(new IvisFacade.Configuration.Builder()
                .endPointUrl(serverAddress)
                .responseType("json")
                .version("v1").build());
        IvisServiceFactory factory = ivis.getServiceFactory(client, IvisOAuth2Utils.getClientContext(request));
        StatementService service = factory.getStatementService();

        if (IvisOAuth2Utils.getAccessToken(request) != null) {
            try {
                Statement statement = service.find(id);

                if (statement != null) {
                    statement.setStatus(status);

                    service.save(statement);

                }
            } catch (UserRedirectRequiredException e) {
                IvisOAuth2Utils.setAccessToken(request, null);
            }
        }

        response.sendRedirect(statementsAddress);

        return id + ":" + status.toString();
    }

    @RequestMapping(value = "/xml", method = RequestMethod.POST)
    public void importApplication(HttpServletRequest request,
                                    HttpServletResponse response,
                                  @RequestParam(value = "file", required = false) Part file,
//                                    @RequestParam("body") String body,
                                    Model model) throws IOException, URISyntaxException {
        IvisFacade ivis = IvisFacade.instance(new IvisFacade.Configuration.Builder()
                .endPointUrl(serverAddress)
                .responseType("json")
                .version("v1").build());
        IvisServiceFactory factory = ivis.getServiceFactory(client, IvisOAuth2Utils.getClientContext(request));
        StatementService statementService = factory.getStatementService();

        if (IvisOAuth2Utils.getAccessToken(request) != null) {

            try {
                Statement statement = new Statement();
                statement.setStatus(StatementStatus.created);
                statementService.save(statement);
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
    public String showImportApplicationForm() {

        return "xml/show";
    }
}
