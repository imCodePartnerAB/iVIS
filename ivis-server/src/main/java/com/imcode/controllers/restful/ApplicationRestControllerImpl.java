package com.imcode.controllers.restful;

import com.imcode.controllers.CrudController;
import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.oauth2.IvisClientDetails;
import com.imcode.oauth2.IvisClientDetailsService;
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
@Deprecated
@RestController
@RequestMapping("/v1/{format}/applications")
//@RequestMapping("/test/applications")
//public class ApplicationRestControllerImpl extends AbstractRestController<BaseClientDetails, String, ApplicationService>{
public class ApplicationRestControllerImpl implements CrudController<JpaClientDetails, String> {
    @Autowired
    private IvisClientDetailsService clietnDetailsService;

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") String id, WebRequest webRequest) {
        Principal user = webRequest.getUserPrincipal();

        return null;
//        return getService().findAllUserClients(id, user.getName());
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public Object getAll(WebRequest webRequest, Model model) {
        Principal user = webRequest.getUserPrincipal();

        return null;
//        return getService().findAllUserApplications(user.getName());
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public Object create(@RequestBody JpaClientDetails entity, WebRequest webRequest) {
        Map<String, String> aditionalInfo = new HashMap<>();
        aditionalInfo.put("user", webRequest.getUserPrincipal().getName());
        entity.setAdditionalInformation(aditionalInfo);
        clietnDetailsService.addClientDetails(entity);

        return "OK";
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") String id, @RequestBody JpaClientDetails entity, WebRequest webRequest) {
        entity.setClientId(id);
        ClientDetails oldEntity = clietnDetailsService.loadClientByClientId(id);
        entity.setAdditionalInformation(oldEntity.getAdditionalInformation());
        clietnDetailsService.removeClientDetails(id);

        clietnDetailsService.updateClientDetails(entity);

        return "OK";
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id, WebRequest webRequest) {
        clietnDetailsService.removeClientDetails(id);
    }

    //Getters & Setters
    public IvisClientDetailsService getService() {
        return clietnDetailsService;
    }

    public void setService(IvisClientDetailsService clietnDetailsService) {
        this.clietnDetailsService = clietnDetailsService;
    }
}
