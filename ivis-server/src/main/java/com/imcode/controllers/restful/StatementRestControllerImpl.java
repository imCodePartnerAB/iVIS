package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.School;
import com.imcode.entities.Statement;
import com.imcode.services.SchoolService;
import com.imcode.services.StatementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/statements")
public class StatementRestControllerImpl extends AbstractRestController<Statement, Long, StatementService> {

}
