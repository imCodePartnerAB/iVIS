package com.imcode.services;

import com.imcode.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends GenericService<User, Long>, UserDetailsService {

    public User findByUsername(String username);

//    public boolean checkAutorisation(String login, String pwd);
}
