package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.ApplicationForm;
import com.imcode.services.ApplicationFormService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/applicationforms")
public class ApplicationFormRestControllerImpl extends AbstractRestController<ApplicationForm, Long, ApplicationFormService> {
}
