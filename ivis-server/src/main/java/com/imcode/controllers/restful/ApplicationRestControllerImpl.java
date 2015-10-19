package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Person;
import com.imcode.entities.Application;
import com.imcode.entities.User;
import com.imcode.services.ApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;
import java.util.Date;

@RestController
@RequestMapping("/v1/{format}/applications")
public class ApplicationRestControllerImpl extends AbstractRestController<Application, Long, ApplicationService> {

    @Override
    public Object create(@RequestBody Application entity, WebRequest webRequest) {
        Principal principal = webRequest.getUserPrincipal();

        if (principal instanceof User) {
            entity.setSubmittedUser((User) principal);
        } else if (principal instanceof OAuth2Authentication) {
            entity.setSubmittedUser((User) ((OAuth2Authentication) principal).getPrincipal());
        }

//        entity.setSubmitDate(new Date());

        return super.create(entity, webRequest);
    }
}
