package com.imcode.controllers.html;

import com.imcode.entities.SchoolClass;
import com.imcode.services.SchoolClassService;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/" + SchoolClassController.MAIN_PATH)
public class SchoolClassController {

    static final String MAIN_PATH = "schoolClasses";

    private final SchoolClassService schoolClassService;

    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") final SchoolClass entity,
                                   final ModelAndView model) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(entity, "try invoke update form for non exist school class");

        model.addObject("entity", entity);
        model.addObject("backUrl", getBackURL(entity.getSchool().getId()));

        model.setViewName(MAIN_PATH + "/edit");

        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView updateSchoolCloudEnabling(@PathVariable("id") final SchoolClass persistEntity,
                                                  @ModelAttribute("entity") final SchoolClass entity,
                                                  final ModelAndView model) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(persistEntity, "Try update non exist school");

        schoolClassService.setSchoolCloudEnabling(persistEntity, entity.getNextCloudEnabled());

        model.setViewName("redirect:" + getBackURL(persistEntity.getSchool().getId()));

        return model;
    }

    private String getBackURL(final long schoolId) {
        return "/" + SchoolController.MAIN_PATH + "/" + schoolId + "?form";
    }
}
