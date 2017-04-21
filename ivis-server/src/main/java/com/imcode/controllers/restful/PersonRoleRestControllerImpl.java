package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.*;
import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.services.PersonRoleService;
import com.imcode.services.UserService;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/v1/{format}/personroles")
public class PersonRoleRestControllerImpl extends AbstractRestController<PersonRole, Long, PersonRoleService> {
}
