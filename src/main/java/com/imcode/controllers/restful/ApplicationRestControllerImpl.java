package com.imcode.controllers.restful;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.imcode.controllers.CrudController;
import com.imcode.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/{format}/applications")
//@RequestMapping("/test/applications")
//public class ApplicationRestControllerImpl extends AbstractRestController<BaseClientDetails, String, ApplicationService>{
public class ApplicationRestControllerImpl implements CrudController<BaseClientDetails, String> {
    @Autowired
    private ApplicationService applicationService;

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") String id, WebRequest webRequest) {
        Principal user = webRequest.getUserPrincipal();

        return getService().findUserApplication(id, user.getName());
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public Object getAll(WebRequest webRequest, Model model) {
        Principal user = webRequest.getUserPrincipal();

        return getService().findAllUserApplications(user.getName());
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public Object create(@RequestBody BaseClientDetails entity, WebRequest webRequest) {
        Map<String, String> aditionalInfo = new HashMap<>();
        aditionalInfo.put("user", webRequest.getUserPrincipal().getName());
        entity.setAdditionalInformation(aditionalInfo);
        ClientDetails created = applicationService.save(entity);

        return created;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") String id, @RequestBody BaseClientDetails entity, WebRequest webRequest) {
        entity.setClientId(id);
        BaseClientDetails oldEntity = (BaseClientDetails) applicationService.find(id);
        entity.setAdditionalInformation(oldEntity.getAdditionalInformation());
        applicationService.delete(id);

        ClientDetails saved = applicationService.save(entity);

        return saved;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id, WebRequest webRequest) {
        applicationService.delete(id);
    }

    //Getters & Setters
    public ApplicationService getService() {
        return applicationService;
    }

    public void setService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
}
