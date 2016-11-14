package com.imcode.controllers.html;

import com.imcode.entities.User;
import com.imcode.entities.enums.AuthorizedGrantType;
import com.imcode.entities.enums.Scope;
import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.services.ClientRoleService;
import com.imcode.services.UserService;
import com.imcode.utils.CollectionTransferUtil;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GeneralValidator;
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

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        logger.info("Listing clients");
        List<JpaClientDetails> clientDetailsList = Collections.emptyList();

        clientDetailsList = clientDetailsService.findAll();

        model.addAttribute("clients", clientDetailsList);
        logger.info("No. of clients: " + clientDetailsList.size());

        return "clients/list";
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("clientId") String clientId,
                         @ModelAttribute("client") @Valid JpaClientDetails client,
                         BindingResult bindingResult,
                         ModelAndView model) throws MethodArgumentNotValidException {

        JpaClientDetails persistentClient = clientDetailsService.findOne(clientId);

        StaticUtls.rejectNullValue(persistentClient, "Try update non exist client");

        new GeneralValidator(true, "autoApproveScopes").invoke(client, bindingResult);

        BeanUtils.copyProperties(client, persistentClient, "id", "autoApproveScopes");

        client.addAuthorizedGrantType(AuthorizedGrantType.refresh_token);

        clientDetailsService.updateClientDetails(persistentClient);

        model.setViewName("redirect:/clients");

        return model;
    }


    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") String id, ModelAndView model) throws MethodArgumentNotValidException {

        JpaClientDetails clientDetails = clientDetailsService.findOne(id);

        StaticUtls.rejectNullValue(clientDetails, "Try invoke update form for non exist client");

        addListsInModel(model);

        model.addObject("client", clientDetails);
        model.setViewName("clients/edit");

        return model;
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView model, Authentication authentication) {
        addListsInModel(model);
        JpaClientDetails client = new JpaClientDetails();
        client.setOwner((User) authentication.getPrincipal());
        model.addObject("client", client);
        model.setViewName("clients/edit");

        return model;
    }

    private void addListsInModel(ModelAndView model) {
        model.addObject(userService.findAll());
        model.addObject(clientRoleService.findAll());
        model.addObject("scopeList", Scope.getValues());
        model.addObject("grantTypes", Arrays.asList(AuthorizedGrantType.getRepresentations()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute("client") @Valid JpaClientDetails client,
                         Model uiModel,
                         RedirectAttributes redirectAttributes) {

        client.addAuthorizedGrantType(AuthorizedGrantType.refresh_token);

        clientDetailsService.addClientDetails(client);

        return "redirect:/clients";
    }


    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public ModelAndView deleteClient(@PathVariable("id") String id, ModelAndView model) throws MethodArgumentNotValidException {

        JpaClientDetails clientDetails = clientDetailsService.findOne(id);

        StaticUtls.rejectNullValue(clientDetails, "Try delete non exist client");

        clientDetailsService.removeClientDetails(id);

        model.setViewName("redirect:/clients");

        return model;
    }

}
