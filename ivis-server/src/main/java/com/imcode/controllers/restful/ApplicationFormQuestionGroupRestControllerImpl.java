package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.ApplicationFormQuestionGroup;
import com.imcode.services.ApplicationFormQuestionGroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/applicationformquestiongroups")
public class ApplicationFormQuestionGroupRestControllerImpl extends AbstractRestController<ApplicationFormQuestionGroup, Long, ApplicationFormQuestionGroupService> {
//    @Override
//    public Object update(@PathVariable("id") Long aLong, @RequestBody(required = false) ApplicationFormQuestionGroup entity, WebRequest webRequest) {
//        return getService().save(entity);
//    }
}
