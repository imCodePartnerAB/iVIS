package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Guardian;
import com.imcode.entities.Person;
import com.imcode.entities.Pupil;
import com.imcode.entities.User;
import com.imcode.services.GuardianService;
import com.imcode.services.PersonService;
import com.imcode.services.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("/v1/{format}/pupils")
public class PupilRestControllerImpl extends AbstractRestController<Pupil, Long, PupilService> {
    @Autowired
    private GuardianService guardianService;

    @Autowired
    private PersonService personService;

    @Autowired
    private PupilService pupilService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Object getMultiplePupils(WebRequest webRequest, Model model) {
        return pupilService.findAll();
    }

    @Override
    public Object getMultiple(WebRequest webRequest, HttpServletResponse response, Model model) {
        Object pupilList = new ArrayList<>();
        User user = getUser(webRequest);

        if (user == null) {
            throw new RuntimeException("User unauthorized!");
        } else if (user.hasRoles("ROLE_GUARDIAN")) {
            Person person = user.getPerson();
            if (person != null) {
                Guardian guardian = guardianService.findFirstByPersonalId(person.getPersonalId());
                if (guardian != null) {
                    pupilList = guardian.getPupils();
                }
            }
        }

        return pupilList;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
    public Object getSingleOrMultipleByPersonalId(@RequestParam("personalId") String personId,
                                                  @RequestParam(value = "first", required = false) Boolean firstOnly,
                                                  HttpServletResponse response
    ) {
        return super.getSingleOrMultipleByPersonalId(personId, firstOnly, response);
    }

    private Person getPerson(WebRequest webRequest) {
        Person person = null;

            User user = getUser(webRequest);

            if (user != null) {
                person = user.getPerson();
            }

        return person;
    }

    private User getUser(WebRequest webRequest) {
        User user = null;

        try {
            Authentication authentication = (Authentication) webRequest.getUserPrincipal();
            user = (User) authentication.getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

}
