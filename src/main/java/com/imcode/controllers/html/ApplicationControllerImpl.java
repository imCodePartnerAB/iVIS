package com.imcode.controllers.html;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
@RequestMapping("/applications")
public class ApplicationControllerImpl {// extends AbstractRestController<ClientDetails, Long, ApplicationService> {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcClientDetailsService clientDetailsService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(WebRequest webRequest, Model model) {
        logger.info("Listing applications");

        List<ClientDetails> applications = clientDetailsService.listClientDetails();
        model.addAttribute("applications", applications);
        logger.info("No. of applications: " + applications.size());

        return "applications/list";
    }
}
