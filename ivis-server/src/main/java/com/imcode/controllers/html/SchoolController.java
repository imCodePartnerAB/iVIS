package com.imcode.controllers.html;

import com.imcode.entities.School;
import com.imcode.entities.enums.ServiceTypeEnum;
import com.imcode.services.SchoolService;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GeneralValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
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

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("entity") @Valid School entity,
                               ModelAndView model) throws MethodArgumentNotValidException {

        BindingResult bindingResult = new BeanPropertyBindingResult(entity, "pupil");
        new GeneralValidator(true, "id").invoke(entity, bindingResult);

        addSpecialObjects(model, entity);

        mainService.save(entity);

        model.setViewName("redirect:/" + MAIN_PATH);

        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView update (@PathVariable("id") School persistEntity,
                                @ModelAttribute("entity") @Valid School entity,
                                ModelAndView model) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(persistEntity, "Try update non exist school");

        addSpecialObjects(model, entity);

        String[] fieldExceptions = {"id"};

        BeanUtils.copyProperties(entity, persistEntity, fieldExceptions);
        mainService.save(persistEntity);
        model.setViewName("redirect:/" + MAIN_PATH);

        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws MethodArgumentNotValidException {

        School school = mainService.find(id);

        StaticUtls.rejectNullValue(school, "Try delete non exist school");


        mainService.delete(id);

    }

    protected void addSpecialObjects(ModelAndView model, School entity) {
        model.addObject("serviceTypeList", Arrays.asList(ServiceTypeEnum.values()));
    }
}

