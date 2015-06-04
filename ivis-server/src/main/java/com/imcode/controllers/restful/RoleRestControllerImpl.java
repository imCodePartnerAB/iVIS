package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Role;
import com.imcode.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/v1/{format}/pupils")
public class RoleRestControllerImpl extends AbstractRestController<Role, Long, RoleService> {
}
