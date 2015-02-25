package com.imcode.controllers;

import com.imcode.controllers.AbstractController;
import com.imcode.entities.AppTableKey;
import com.imcode.entities.AppTablePermission;
import com.imcode.entities.School;
import com.imcode.services.AppTablePermissionService;
import com.imcode.services.SchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permissions")
public class AppTablePermissionControllerImpl extends AbstractController<AppTablePermission, Long, AppTablePermissionService> {

}
