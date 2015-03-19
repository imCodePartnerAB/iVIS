package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.TransportType;
import com.imcode.services.TransportTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ttypes")
public class TransportTypeRestControllerImpl extends AbstractRestController<TransportType, Long, TransportTypeService> {
}
