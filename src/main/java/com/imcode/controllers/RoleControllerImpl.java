package com.imcode.controllers;

import com.imcode.entities.Role;
import com.imcode.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleControllerImpl extends AbstractController<Role, Long, RoleService> {
}
