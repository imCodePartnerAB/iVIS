package com.imcode.controllers.html;

import com.imcode.entities.Person;
import com.imcode.entities.Pupil;
import com.imcode.services.*;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GeneralValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/" + PupilController.MAIN_PATH)
public class PupilController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String MAIN_PATH = "pupils";
    @Autowired
    private PupilService mainService;

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.setViewName(MAIN_PATH + "/list");
        model.addObject("entityList", mainService.findAll());

        return model;
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") Pupil entity,
                                   ModelAndView model) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(entity, "try invoke update form for non exist pupil");

        model.setViewName(MAIN_PATH + "/edit");
        model.addObject("entity", entity);

        return model;
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView model) {
        Pupil entity = new Pupil();
        model.setViewName(MAIN_PATH + "/edit");
        model.addObject("entity", entity);

        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("entity") @Valid Pupil entity,
                               ModelAndView model) throws MethodArgumentNotValidException {

        BindingResult bindingResult = new BeanPropertyBindingResult(entity, "pupil");
        new GeneralValidator(true, "id").invoke(entity, bindingResult);

        mainService.save(entity);

        model.setViewName("redirect:/" + MAIN_PATH);

        return model;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") @ModelAttribute("entity") Pupil persistEntity,
                               @ModelAttribute("entity") @Valid Pupil entity,
                               ModelAndView model) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(persistEntity, "Try update non exist pupil");

        Person persistPerson = persistEntity.getPerson();
        Person entityPerson = entity.getPerson();

        persistPerson.setPersonalId(entityPerson.getPersonalId());
        persistPerson.setFirstName(entityPerson.getFirstName());
        persistPerson.setLastName(entityPerson.getLastName());

        personService.save(persistPerson);
        model.setViewName("redirect:/" + MAIN_PATH);

        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws MethodArgumentNotValidException {

        Pupil pupil = mainService.find(id);

        StaticUtls.rejectNullValue(pupil, "Try update non exist pupil");

        mainService.delete(id);

    }

}