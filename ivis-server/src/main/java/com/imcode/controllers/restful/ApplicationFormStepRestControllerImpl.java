package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.ApplicationFormStep;
import com.imcode.services.ApplicationFormStepService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/applicationformsteps")
public class ApplicationFormStepRestControllerImpl extends AbstractRestController<ApplicationFormStep, Long, ApplicationFormStepService> {
//    @Override
//    public Object update(@PathVariable("id") Long aLong, @RequestBody(required = false) ApplicationFormStep entity, WebRequest webRequest) {
//        return getService().save(entity);
//    }
}
