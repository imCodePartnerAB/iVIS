package com.imcode.controllers.html;

import com.imcode.entities.OnceTimeAccessToken;
import com.imcode.entities.EntityServiceMethod;
import com.imcode.services.EntityServiceInformationService;
import com.imcode.services.OnceTimeAccessTokenService;
import com.imcode.services.EntityServiceMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by ruslan on 01.08.16.
 */
@Controller
public class OnStartupController {

    public static final String ENTITY_PACKAGE = "com.imcode.entities";
    public static final String SERVICE_PACKAGE = "com.imcode.services";
    public static final String REST_PACKAGE= "com.imcode.controllers.restful";

    @Autowired
    public OnceTimeAccessTokenService onceTimeAccessTokenService;

    @Autowired
    public EntityServiceMethodService EntityServiceMethodService;

    @Autowired
    public EntityServiceInformationService informationService;

    @PostConstruct
    public void deleteExpiredOrUsedOnceTimeAccessTokens() {
        List<OnceTimeAccessToken> onceTimeAccessTokens = onceTimeAccessTokenService.selectExpiredOrUsedTokens();
        onceTimeAccessTokenService.delete(onceTimeAccessTokens);
    }
    

}
