package com.imcode.controllers.html;

import com.imcode.controllers.html.exceptions.NotFoundException;
import com.imcode.controllers.html.form.Message;
import com.imcode.controllers.html.form.MessageType;
import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.entities.User;
import com.imcode.entities.enums.AuthorizedGrantType;
import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.exceptions.factories.ErrorBuilder;
import com.imcode.exceptions.wrappers.GeneralError;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.services.ClientRoleService;
import com.imcode.services.EntityRestProviderInformationService;
import com.imcode.services.MethodRestProviderForEntityService;
import com.imcode.services.UserService;
import com.imcode.utils.CollectionTransferUtil;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GenericValidator;
import com.imcode.validators.JpaClientDetailsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/clients")
public class ClientDetailsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IvisClientDetailsService clientDetailsService;

    @Autowired
    private ClientRoleService clientRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityRestProviderInformationService entityRestProviderInformationService;

    @Autowired
    private MethodRestProviderForEntityService methodRestProviderForEntityService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        logger.info("Listing clients");
        List<JpaClientDetails> clientDetailsList = Collections.emptyList();

        clientDetailsList = clientDetailsService.findAll();

        model.addAttribute("clients", clientDetailsList);
        logger.info("No. of clients: " + clientDetailsList.size());

        return "clients/list";
    }

//    Updating exists client
    @RequestMapping(value = "/{clientId}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("clientId") String clientId,
                         @ModelAttribute("client") @Valid JpaClientDetails client,
                         BindingResult bindingResult,
                         ModelAndView model) throws MethodArgumentNotValidException {

        JpaClientDetails persistentClient = clientDetailsService.findOne(clientId);

        StaticUtls.rejectNullValue(persistentClient, "Try update non exist client");

        new GenericValidator(true, "clientId", "autoApproveScopes").invoke(client, bindingResult);

        BeanUtils.copyProperties(client, persistentClient, "id", "autoApproveScopes");

        clientDetailsService.updateClientDetails(persistentClient);

        model.setViewName("redirect:/clients");

        return model;
    }


//    Invoke Update Form
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") String id, ModelAndView model) throws MethodArgumentNotValidException {

        JpaClientDetails clientDetails = clientDetailsService.findOne(id);

        StaticUtls.rejectNullValue(clientDetails, "Try invoke update form for non exist client");

        addListsInModel(model);

        model.addObject("client", clientDetails);
        model.setViewName("clients/edit");

        return model;
    }

    //    Invoke create form
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView model, Authentication authentication) {
        addListsInModel(model);
        JpaClientDetails client = new JpaClientDetails();
        client.setOwner((User) authentication.getPrincipal());
        model.addObject("client", client);
        model.setViewName("clients/edit");

        return model;
    }

    //    Show the PERMISSION form
    @RequestMapping(value = "/{id}", params = "perm", method = RequestMethod.GET)
    public ModelAndView permissionForm(@PathVariable("id") String id, ModelAndView model) throws MethodArgumentNotValidException {

        JpaClientDetails clientDetails = clientDetailsService.findOne(id);

        StaticUtls.rejectNullValue(clientDetails, "Try invoke permission form for non exist client");

        model.addObject("identifier", id);
        model.setViewName("clients/permissions");
        model.addObject(methodRestProviderForEntityService.findAll());
        model.addObject(entityRestProviderInformationService.findAll());
        model.addObject("specify", "client");
        model.addObject("allowedMethods",
                new CollectionTransferUtil<>(methodRestProviderForEntityService.findAllowedMethodsByClientId(id)));
        return model;
    }

    private void addListsInModel(ModelAndView model) {
        model.addObject(userService.findAll());
        model.addObject(clientRoleService.findAll());
        model.addObject("scopeList", "read,write,execute".split(","));
        model.addObject("grantTypes", Arrays.asList(AuthorizedGrantType.getRepresentations()));
    }

    //    Create new Client
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute("client") @Valid JpaClientDetails client,
                         Model uiModel,
                         RedirectAttributes redirectAttributes) {

        clientDetailsService.addClientDetails(client);

        return "redirect:/clients";
    }


    //    Invoke Delete
    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public ModelAndView deleteClient(@PathVariable("id") String id, ModelAndView model) throws MethodArgumentNotValidException {

        JpaClientDetails clientDetails = clientDetailsService.findOne(id);

        StaticUtls.rejectNullValue(clientDetails, "Try delete non exist client");

        clientDetailsService.removeClientDetails(id);

        model.setViewName("redirect:/clients");

        return model;
    }

    @RequestMapping(value = "/{id}", params = "permit", method = RequestMethod.POST)
    public ModelAndView permitMethods(@ModelAttribute("allowedMethods") CollectionTransferUtil<String> allowedMethods,
                                      @PathVariable("id") String clientId,
                                      ModelAndView model) throws MethodArgumentNotValidException {

        JpaClientDetails client = clientDetailsService.findOne(clientId);

        StaticUtls.rejectNullValue(client, "Try set permissions for non exist client");

        Collection<String> idOfMethods = allowedMethods.getCollection();
        List<MethodRestProviderForEntity> allowedMethodsByUserId = methodRestProviderForEntityService.findAllowedMethodsByClientId(clientId);

        allowedMethodsByUserId.stream()
                .peek(methodRestProviderForEntity -> methodRestProviderForEntity.deleteClient(clientId))
                .forEach(methodRestProviderForEntityService::save);

        if (idOfMethods != null) {
            idOfMethods.stream()
                    .map(Long::parseLong)
                    .map(methodRestProviderForEntityService::find)
                    .peek(methodRestProviderForEntity -> methodRestProviderForEntity.addClient(client))
                    .forEach(methodRestProviderForEntityService::save);
        }

        model.setViewName("redirect:/clients");
        return model;
    }




}
