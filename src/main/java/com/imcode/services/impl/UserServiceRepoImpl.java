package com.imcode.services.impl;

import com.imcode.entities.User;
import com.imcode.repositories.UserRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceRepoImpl extends AbstractService<User, Long, UserRepository> implements UserService {
}
