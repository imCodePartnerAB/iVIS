package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Transport;
import com.imcode.services.TransportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transports")
public class TransportRestControllerImpl extends AbstractRestController<Transport, Long, TransportService> {
}
