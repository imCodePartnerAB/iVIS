package com.imcode.imcms.addon.ivisclient.controllers;

import com.imcode.entities.Statement;
import com.imcode.entities.enums.StatementStatus;
import com.imcode.services.StatementService;
import imcode.server.Imcms;
import imcode.services.restful.IvisFacade;
import imcode.services.restful.IvisServiceFactory;
import imcode.services.utils.IvisOAuth2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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



    //    private final String ACCESS_TOKEN_URI = "http://localhost:8080/ivis/oauth/token";
//    private final String USER_AUTHORIZATION_URI = "http://localhost:8080/ivis/oauth/authorize";
    public static final String GRANT_TYPE = "authorization_code";
    //    public static final String REDIRECT_URI = "http://localhost:8080/";
//    todo Заинжектить значение мз placeholder
//    @Value("${AuthorizationCodeHandlerUri}")
//    public String tokenHandler = "http://localhost:8080/client/api/content/ivis/code";
    // @Value("${AuthorizationCodeHandlerUri}")
    public String tokenHandler = Imcms.getServerProperties().getProperty("AuthorizationCodeHandlerUri");


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
//        headers.add("Authorization", "Basic MmU5ZmE4OTUtZTU3Ny00MTU2LTg0NGEtMmNiYjFlYmJlMDZkOnNlY3JldA==");
        HttpEntity httpEntity = new HttpEntity(form, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OAuth2AccessToken> result = restTemplate.postForEntity(client.getAccessTokenUri(), httpEntity, OAuth2AccessToken.class);

        IvisOAuth2Utils.setAccessToken(request, result.getBody());
        response.sendRedirect(Imcms.getServerProperties().getProperty("StatementsAddress"));

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
                .endPointUrl(Imcms.getServerProperties().getProperty("ServerAddress"))
                .responseType("json")
                .version("v1").build());
        IvisServiceFactory factory = ivis.getServiceFactory(client, IvisOAuth2Utils.getAccessToken(request));
        StatementService service = factory.getStatementService();
        Statement statement = service.find(id);

        if (statement != null) {
            statement.setStatus(status);
            service.save(statement);
        }

        response.sendRedirect(Imcms.getServerProperties().getProperty("StatementsAddress"));

        return id + ":" + status.toString();
    }

    public String getTokenHandler() {
        return tokenHandler;
    }

    public void setTokenHandler(String tokenHandler) {
        this.tokenHandler = tokenHandler;
    }
}
