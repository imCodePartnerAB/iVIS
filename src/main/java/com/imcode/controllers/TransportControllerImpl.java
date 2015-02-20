package com.imcode.controllers;

import com.imcode.entities.Transport;
import com.imcode.services.TransportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transports")
public class TransportControllerImpl extends AbstractController<Transport, Long, TransportService> {
}
