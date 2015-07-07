package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Person;
import com.imcode.entities.Application;
import com.imcode.entities.User;
import com.imcode.services.ApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/v1/{format}/applications")
public class ApplicationRestControllerImpl extends AbstractRestController<Application, Long, ApplicationService> {

    @Override
    public Object create(@RequestBody Application entity, WebRequest webRequest) {
        Person person = null;

        try {
            Authentication authentication = (Authentication) webRequest.getUserPrincipal();
            User user = (User) authentication.getPrincipal();
            person = user.getPerson();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (person != null) {
            entity.setSubmittedPerson(person);
        }

        return super.create(entity, webRequest);
    }
}
