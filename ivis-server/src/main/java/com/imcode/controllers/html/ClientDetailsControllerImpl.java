package com.imcode.controllers.html;

import com.imcode.controllers.html.exceptions.NotFoundException;
import com.imcode.controllers.html.form.Message;
import com.imcode.controllers.html.form.MessageType;
import com.imcode.entities.User;
import com.imcode.entities.enums.AuthorizedGrantType;
import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.services.ClientRoleService;
import com.imcode.services.EntityRestProviderInformationService;
import com.imcode.services.MethodRestProviderForEntityService;
import com.imcode.services.UserService;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.JpaClientDetailsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
@RequestMapping("/clients")
public class ClientDetailsControllerImpl {// extends AbstractRestController<ClientDetails, Long, ApplicationService> {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IvisClientDetailsService clientDetailsService;

    @Autowired
    private ClientRoleService clientRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private JpaClientDetailsValidator clientValidator;

    @Autowired
    private EntityRestProviderInformationService entityRestProviderInformationService;

    @Autowired
    private MethodRestProviderForEntityService methodRestProviderForEntityService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(WebRequest webRequest, Model model, Authentication principal) {
        logger.info("Listing clients");
        List<JpaClientDetails> clientDetailsList = Collections.emptyList();


        if (webRequest.isUserInRole("ROLE_ADMIN")) {
            clientDetailsList = clientDetailsService.findAll();
        } else if (principal != null){
            clientDetailsList = clientDetailsService.findAllUserClients((User) principal.getPrincipal());
        }

        model.addAttribute("clients", clientDetailsList);
        logger.info("No. of clients: " + clientDetailsList.size());

        return "clients/list";
    }

//    Updating exists client
    @RequestMapping(value = "/{clientId}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("clientId") String clientId,
                         @ModelAttribute("client") @Valid JpaClientDetails client,
                         BindingResult bindingResult,
                         ModelAndView model,
                         WebRequest webRequest,
                         RedirectAttributes redirectAttributes,
                         Authentication principal) {

//        clientValidator.validate(client, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setViewName("clients/edit");
            addListsInModel(model);
            model.addObject("message", new Message(MessageType.ERROR, "Client save fail"));
            model.addObject("client", client);

            return model;
        }

        model.clear();
        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Client save success"));
        JpaClientDetails persistentClient;

        if (webRequest.isUserInRole("ROLE_ADMIN")) {
            persistentClient = clientDetailsService.findOne(clientId);
        } else if (principal != null) {
            User user = (User) principal.getPrincipal();
            persistentClient = clientDetailsService.findUserClientById(clientId, user);
        } else {
            model.setViewName("clients/edit");
            addListsInModel(model);
            model.addObject("message", new Message(MessageType.ERROR, "Client not found"));
            model.addObject("client", client);

            return model;
        }
//        BeanUtils.copyProperties(client, persistentClient, "id", "autoApproveScopes");

        try {
            StaticUtls.nullAwareBeanCopy(persistentClient, client);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        clientDetailsService.updateClientDetails(persistentClient);

        model.setViewName("redirect:/clients");


        return model;
    }


//    Invoke Update Form
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") String id, ModelAndView model, WebRequest webRequest, Authentication principal) {
        JpaClientDetails clientDetails;
        addListsInModel(model);

        if (webRequest.isUserInRole("ROLE_ADMIN")) {
            clientDetails = clientDetailsService.findOne(id);
        }else if (principal != null) {
            clientDetails = clientDetailsService.findUserClientById(id, (User) principal.getPrincipal());
        } else {
            model.addObject("message", new Message(MessageType.ERROR, "Client not found"));
            model.setViewName("clients/list");
            return model;
        }

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
    public ModelAndView permissionForm(@PathVariable("id") String id, ModelAndView model, WebRequest webRequest, Authentication principal) {

        JpaClientDetails clientDetails = clientDetailsService.findOne(id);

        if (clientDetails == null) {
            model.setViewName("clients/list");
            throw new NotFoundException();
        }

        model.addObject("client", clientDetails);
        model.setViewName("clients/permissions");
        model.addObject(methodRestProviderForEntityService.findAll());
        model.addObject(entityRestProviderInformationService.findAll());
        model.addObject("specify", "client");

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
                         BindingResult bindingResult,
                         Model uiModel,
                         RedirectAttributes redirectAttributes,
                         WebRequest webRequest,
                         Authentication principal) {

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message(MessageType.ERROR, "Client save fail"));
            uiModel.addAttribute("client", client);

            return "clients/edit";
        }

        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Client save success"));

        clientDetailsService.addClientDetails(client);

        return "redirect:/clients";
    }


    //    Invoke Delete
    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public ModelAndView deleteClient(@PathVariable("id") String id, ModelAndView model, WebRequest webRequest, Authentication principal) {
        JpaClientDetails clientDetails;
        addListsInModel(model);

        if (webRequest.isUserInRole("ROLE_ADMIN")) {
            clientDetails = clientDetailsService.findOne(id);
            clientDetailsService.removeClientDetails(id);
        }else if (principal != null) {
            clientDetails = null;
            //clientDetails = clientDetailsService.findUserClientById(id, (User) principal.getPrincipal());
            clientDetailsService.removeClientDetails(id);
        } else {
            model.addObject("message", new Message(MessageType.ERROR, "Client not found"));
            model.setViewName("clients/list");
            return model;
        }

        model.addObject("client", clientDetails);
        model.setViewName("redirect:/clients");
        return model;
    }
}
