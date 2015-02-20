package com.imcode.controllers;

import com.imcode.entities.User;
import com.imcode.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserControllerImpl extends AbstractController<User, Long, UserService> {
}
