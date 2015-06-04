package com.imcode.services;

import com.imcode.entities.Person;
import com.imcode.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends GenericService<User, Long>, UserDetailsService {

    User findByUsername(String username);

    User findByPerson(Person person);

    User findByPersonId(Long personId);

//    public boolean checkAutorisation(String login, String pwd);
}
