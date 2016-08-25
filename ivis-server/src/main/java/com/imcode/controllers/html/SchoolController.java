package com.imcode.controllers.html;

import com.imcode.controllers.html.exceptions.NotFoundException;
import com.imcode.entities.School;
import com.imcode.entities.enums.ServiceTypeEnum;
import com.imcode.services.SchoolService;
import com.imcode.utils.StaticUtls;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Locale;
import org.slf4j.Logger;

@Controller
@RequestMapping("/" + SchoolController.MAIN_PATH)
public class SchoolController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String MAIN_PATH = "schools";
    @Autowired
    private SchoolService mainService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.setViewName(MAIN_PATH + "/list");
        model.addObject("entityList", mainService.findAll());

        return model;
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") School entity,
                                   ModelAndView model) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(entity, "try invoke update form for non exist school");

        addSpecialObjects(model, entity);

        model.setViewName(MAIN_PATH + "/edit");
        model.addObject("entity", entity);

        return model;
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView model) {
        School entity = new School();
        addSpecialObjects(model, entity);
        model.setViewName(MAIN_PATH + "/edit");
        model.addObject("entity", entity);

        return model;
    }

    //    CREATE new user
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("entity") @Valid School entity,
                               BindingResult bindingResultEntity,
                               ModelAndView model,
                               WebRequest webRequest,
                               Locale locale) {

        addSpecialObjects(model, entity);
//        ValidationUtils.invokeValidator(userValidator, entity, bindingResultSchool);
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
        public ModelAndView update (@PathVariable("id") School persistEntity,
                                    @ModelAttribute("entity") @Valid School entity,
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

            String[] fieldExceptions = {"id"};

            BeanUtils.copyProperties(entity, persistEntity, fieldExceptions);
            mainService.save(persistEntity);
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

    protected void addSpecialObjects(ModelAndView model, School entity) {
        model.addObject("serviceTypeList", Arrays.asList(ServiceTypeEnum.values()));
    }
}

