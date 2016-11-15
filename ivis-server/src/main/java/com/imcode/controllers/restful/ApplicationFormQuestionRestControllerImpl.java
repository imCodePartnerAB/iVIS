package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.ApplicationFormQuestion;
import com.imcode.services.ApplicationFormQuestionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/applicationformquestions")
public class ApplicationFormQuestionRestControllerImpl extends AbstractRestController<ApplicationFormQuestion, Long, ApplicationFormQuestionService> {
//    @Override
//    public Object updateSingle(@PathVariable("id") Long aLong, @RequestBody(required = false) ApplicationFormQuestion entity, WebRequest webRequest) {
//        return getService().save(entity);
//    }
}
