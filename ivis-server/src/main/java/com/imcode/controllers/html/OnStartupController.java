package com.imcode.controllers.html;

import com.imcode.entities.EntityProviderInformation;
import com.imcode.entities.OnceTimeAccessToken;
import com.imcode.entities.EntityProviderMethod;
import com.imcode.entities.embed.RestMethod;
import com.imcode.services.EntityProviderInformationService;
import com.imcode.services.OnceTimeAccessTokenService;
import com.imcode.services.EntityProviderMethodService;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by ruslan on 01.08.16.
 */
@Controller
public class OnStartupController {


    @Autowired
    public OnceTimeAccessTokenService onceTimeAccessTokenService;

    @PostConstruct
    public void deleteExpiredOrUsedOnceTimeAccessTokens() {
        List<OnceTimeAccessToken> onceTimeAccessTokens = onceTimeAccessTokenService.selectExpiredOrUsedTokens();
        onceTimeAccessTokenService.delete(onceTimeAccessTokens);
    }

    @PostConstruct
    public void persistAllEntityServicesAndMethods() {

    }




}
