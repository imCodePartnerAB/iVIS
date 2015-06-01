package com.imcode.controllers.html;

import com.imcode.controllers.html.form.Message;
import com.imcode.controllers.html.form.MessageType;
import com.imcode.entities.User;
import com.imcode.entities.enums.AuthorizedGrantType;
import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.services.ClientRoleService;
import com.imcode.services.UserService;
import com.imcode.validators.JpaClientDetailsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@PathVariable("id") String id,
                         JpaClientDetails client,
                         BindingResult bindingResult,
                         Model uiModel,
                         WebRequest webRequest,
                         RedirectAttributes redirectAttributes,
                         Authentication principal) {

//        clientValidator.validate(client, bindingResult);

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message(MessageType.ERROR, "Client save fail"));
            uiModel.addAttribute("client", client);

            return "clients/edit";
        }

        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Client save success"));
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        JpaClientDetails persistentClient;

        if (webRequest.isUserInRole("ROLE_ADMIN")) {
            persistentClient = clientDetailsService.findOne(id);
        } else if (principal != null) {
            User user = (User) principal.getPrincipal();
            persistentClient = clientDetailsService.findUserClientById(id, user);
        } else {
            uiModel.addAttribute("message", new Message(MessageType.ERROR, "Client not found"));
            uiModel.addAttribute("client", client);
            return "clients/edit";
        }
        BeanUtils.copyProperties(client, persistentClient, "id");
        clientDetailsService.updateClientDetails(persistentClient);

//        appOld.setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());
//        appOld.setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
//        appOld.setAuthorizedGrantTypes(client.getAuthorizedGrantTypes());
//        appOld.setClientSecret(client.getClientSecret());
//        appOld.setRegisteredRedirectUri(client.getRegisteredRedirectUri());
//        appOld.setScope(client.getScope());
////        application.setAuthorities(grantedAuthorities);
////        application.setClientId(id);
//        applicationService.update(id, appOld);

        return "redirect:/clients";
    }


//    Invoke Update Form
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") String id, ModelAndView model, WebRequest webRequest, Authentication principal) {
        JpaClientDetails clientDetails;
        model.addObject(userService.findAll());
        model.addObject(clientRoleService.findAll());
        model.addObject("scopeList", "read,write,execute".split(","));
        model.addObject("grantTypes", Arrays.asList(AuthorizedGrantType.getRepresentations()));

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
        model.addObject(userService.findAll());
        model.addObject(clientRoleService.findAll());
        model.addObject("scopeList", "read,write,execute".split(","));
        model.addObject("grantTypes", Arrays.asList(AuthorizedGrantType.getRepresentations()));
        JpaClientDetails client = new JpaClientDetails();
        client.setOwner((User) authentication.getPrincipal());
        model.addObject("client", client);
        model.setViewName("clients/edit");

        return model;
    }

//    Create new Client
    @RequestMapping(method = RequestMethod.POST)
    public String create(JpaClientDetails client,
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

//        client.setClientId(UUID.randomUUID().toString());
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
//        client.setAuthorities(grantedAuthorities);
//        Collection<String> resourceIds = Arrays.asList("ivis");
//        client.setResourceIds("ivis");
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Client save success"));

        clientDetailsService.addClientDetails(client);

        return "redirect:/clients";
    }
}
