package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.PersonRole;
import com.imcode.services.PersonRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/personroles")
public class PersonRoleRestControllerImpl extends AbstractRestController<PersonRole, Long, PersonRoleService> {
}
