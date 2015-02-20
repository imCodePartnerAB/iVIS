package com.imcode.controllers;

import com.imcode.entities.SchoolClass;
import com.imcode.services.SchoolClassService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sclasses")
public class SchoolClassControllerImpl extends AbstractController<SchoolClass, Long, SchoolClassService> {
}
