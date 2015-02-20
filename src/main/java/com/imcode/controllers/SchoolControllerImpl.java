package com.imcode.controllers;

import com.imcode.entities.School;
import com.imcode.services.SchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schools")
public class SchoolControllerImpl extends AbstractController<School, Long, SchoolService> {

}
