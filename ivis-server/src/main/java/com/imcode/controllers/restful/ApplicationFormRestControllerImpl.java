package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.ApplicationForm;
import com.imcode.services.ApplicationFormService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/v1/{format}/applicationforms")
public class ApplicationFormRestControllerImpl extends AbstractRestController<ApplicationForm, Long, ApplicationFormService> {
//    @Override
//    public Object update(@PathVariable("id") Long aLong, @RequestBody(required = false) ApplicationForm entity, WebRequest webRequest) {
//        return getService().save(entity);
//    }
}
