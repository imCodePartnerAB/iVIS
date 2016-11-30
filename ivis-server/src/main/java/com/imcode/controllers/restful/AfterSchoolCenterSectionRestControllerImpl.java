package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.AfterSchoolCenterSection;
import com.imcode.services.AfterSchoolCenterSectionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/afterschoolcentersections")
public class AfterSchoolCenterSectionRestControllerImpl extends AbstractRestController<AfterSchoolCenterSection, Long, AfterSchoolCenterSectionService> {

}
