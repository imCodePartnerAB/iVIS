package com.imcode.controllers.restful;


import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.WorkRole;
import com.imcode.services.WorkRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/workroles")
public class WorkRoleRestControllerImpl extends AbstractRestController<WorkRole, Long, WorkRoleService> {
}
