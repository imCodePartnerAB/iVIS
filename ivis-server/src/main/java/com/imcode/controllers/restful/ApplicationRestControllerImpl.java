package com.imcode.controllers.restful;

import com.imcode.App;
import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Person;
import com.imcode.entities.Application;
import com.imcode.entities.User;

import com.imcode.services.ApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

@RestController
@RequestMapping("/v1/{format}/applications")
public class ApplicationRestControllerImpl extends AbstractRestController<Application, Long, ApplicationService> {

    @Override
    public Object create(@RequestBody @Valid Application entity,
                         HttpServletResponse response,
                         BindingResult bindingResult, WebRequest webRequest) throws MethodArgumentNotValidException {
        Principal principal = webRequest.getUserPrincipal();

        if (principal instanceof User) {
            entity.setSubmittedUser((User) principal);
        } else if (principal instanceof OAuth2Authentication) {
            entity.setSubmittedUser((User) ((OAuth2Authentication) principal).getPrincipal());
        }

//        entity.setSubmitDate(new Date());

        return super.create(entity, response, bindingResult, webRequest);
    }

    @Override
    public Object update(@PathVariable("id") Long aLong,
                         HttpServletResponse response,
                         @RequestBody(required = false) Application entity, WebRequest webRequest) {
        if (entity.getId()!=null && entity.getLoadedValues() == null) {
            Application attachetEntity = getService().find(entity.getId());
                entity.setLoadedValues(attachetEntity.getLoadedValues());
        }

        return super.update(aLong, response, entity, webRequest);
    }
}
