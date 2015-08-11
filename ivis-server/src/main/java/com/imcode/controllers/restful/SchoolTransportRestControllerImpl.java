package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.SchoolTransport;
import com.imcode.services.AbstractNamedEntityService;
import com.imcode.services.SchoolTransportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/schooltransports")
public class SchoolTransportRestControllerImpl extends AbstractRestController<SchoolTransport, Long, SchoolTransportService> {

}
