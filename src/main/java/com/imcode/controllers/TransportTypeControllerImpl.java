package com.imcode.controllers;

import com.imcode.entities.TransportType;
import com.imcode.services.TransportTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ttypes")
public class TransportTypeControllerImpl extends AbstractController<TransportType, Long, TransportTypeService> {
}
