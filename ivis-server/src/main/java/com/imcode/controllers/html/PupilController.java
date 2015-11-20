package com.imcode.controllers.html;

import com.imcode.controllers.html.exceptions.NotFoundException;
import com.imcode.entities.Person;
import com.imcode.entities.Pupil;
import com.imcode.entities.SchoolClass;
import com.imcode.entities.enums.ServiceTypeEnum;
import com.imcode.services.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Locale;

@Controller
@RequestMapping("/" + PupilController.MAIN_PATH)
public class PupilController {
    public static final String MAIN_PATH = "pupils";
    @Autowired
    private PupilService mainService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private PersonService personService;

    @Autowired
    private SchoolClassService schoolClassService;

    @Autowired
    private AcademicYearService academicYearService;

//    @Autowired
//    private UserValidator userValidator;

    @Autowired
    MessageSource messageSource;

    //    Shows the list of users
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model, Authentication authentication) {
        model.setViewName(MAIN_PATH + "/list");
        model.addObject("entityList", mainService.findAll());

        return model;
    }

    //    Show the UPDATE form
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") Pupil entity,
                                   ModelAndView model,
                                   WebRequest webRequest,
                                   Locale locale) {
        addSpecialObjects(model, entity);

        if (entity == null) {
            model.setViewName(MAIN_PATH + "/list");
            throw new NotFoundException();
        }

        model.setViewName(MAIN_PATH + "/edit");
        model.addObject("entity", entity);

        return model;
    }

    //    Show the CREATE form
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView model) {
        Pupil entity = new Pupil();
        addSpecialObjects(model, entity);
        model.setViewName(MAIN_PATH + "/edit");
        model.addObject("entity", entity);

        return model;
    }

    //    CREATE new user
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("entity") @Valid Pupil entity,
                               BindingResult bindingResultEntity,
                               ModelAndView model,
                               WebRequest webRequest,
                               Locale locale) {

        addSpecialObjects(model, entity);
//        ValidationUtils.invokeValidator(userValidator, entity, bindingResultPupil);
//
//        if (mainService.findByUsername(entity.getUsername()) != null) {
//            bindingResultUser.rejectValue("username", null, "User is alredy exists!");
//        }

        if (bindingResultEntity.hasErrors()) {
            model.addObject("entity", entity);
            model.setViewName(MAIN_PATH + "/edit");

            return model;
        }

        mainService.save(entity);

        model.setViewName("redirect:/" + MAIN_PATH);

        return model;
    }


//    @ModelAttribute("user")
//    public User bindUser(User user) {
//        Set<Role> roleSet = new HashSet<>();
//        Set<Role> roleNameSet = user.getAuthorities();
//
//        for (Role role :roleNameSet) {
//            Role persistRole = roleService.findByName(role.getName());
//            if (persistRole != null) {
//                roleSet.add(persistRole);
//            }
//        }
//
//        user.setAuthorities(roleSet);
//
//        return user;
//    }

        //    UPDATE exists user
        @RequestMapping(value = "/{id}", method = RequestMethod.POST)
        public ModelAndView update (@PathVariable("id") @ModelAttribute("entity") Pupil persistEntity,
                                    @ModelAttribute("entity") @Valid Pupil entity,
                                    BindingResult bindingResultEntity,
                                    ModelAndView model,
                                    WebRequest webRequest,
                                    Locale locale){
            addSpecialObjects(model, entity);

//            ValidationUtils.invokeValidator(userValidator, entity, bindingResultUser);

            if (bindingResultEntity.hasErrors()) {
                model.addObject("entity", entity);
                model.setViewName(MAIN_PATH + "/edit");

                return model;
            }

//            User persistUser = mainService.find(id);

            if (persistEntity == null) {
                throw new NotFoundException();
            }

//            String[] fieldExceptions = {"id", "person.firstName"};

//            BeanUtils.copyProperties(entity, persistEntity, fieldExceptions);
//            mainService.save(persistEntity);

            Person persistPerson = persistEntity.getPerson();
            Person entityPerson = entity.getPerson();

            persistPerson.setPersonalId(entityPerson.getPersonalId());
            persistPerson.setFirstName(entityPerson.getFirstName());
            persistPerson.setLastName(entityPerson.getLastName());

            personService.save(persistPerson);
            model.setViewName("redirect:/" + MAIN_PATH);

            return model;
        }

    //    DELETE exists user
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        if (!mainService.exist(id)) {
            throw new NotFoundException();
        }

        mainService.delete(id);

//        return "redirect:/schools";
    }

    protected void addSpecialObjects(ModelAndView model, Pupil entity) {
        model.addObject("schoolList", schoolService.findAll());
        model.addObject("schoolClassList", schoolClassService.findAll());
        model.addObject("academicYearList", academicYearService.findAll());
    }
}

