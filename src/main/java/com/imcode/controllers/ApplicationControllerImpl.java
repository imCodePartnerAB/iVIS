package com.imcode.controllers;

import com.imcode.entities.Application;
import com.imcode.entities.Role;
import com.imcode.services.ApplicationService;
import com.imcode.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applications")
public class ApplicationControllerImpl extends AbstractController<Application, Long, ApplicationService> {
}
