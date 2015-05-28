package com.imcode.imcms.addon.ivisclient.controllers;

import com.imcode.imcms.addon.ivisclient.utils.IvisOAuth2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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


    private final String ACCESS_TOKEN_URI = "http://localhost:8080/ivis/oauth/token";
    private final String USER_AUTHORIZATION_URI = "http://localhost:8080/ivis/oauth/authorize";
    public static final String GRANT_TYPE = "authorization_code";
    public static final String REDIRECT_URI = "http://localhost:8080/";
    public static final String TOKEN_HANDLER = "http://localhost:8080/client/api/content/ivis/code";


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
        form.add("redirect_uri", TOKEN_HANDLER);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic MmU5ZmE4OTUtZTU3Ny00MTU2LTg0NGEtMmNiYjFlYmJlMDZkOnNlY3JldA==");
        HttpEntity httpEntity = new HttpEntity(form, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OAuth2AccessToken> result = restTemplate.postForEntity(ACCESS_TOKEN_URI, httpEntity, OAuth2AccessToken.class);

        IvisOAuth2Utils.setAccessToken(request, result.getBody());
        response.sendRedirect("http://localhost:8080/client");

        return result.getBody().toString();
    }

    @RequestMapping(value = "/token")
    @ResponseBody
    public String getToken() {
        String s = "sdfas";
        return s;
    }
}
