package com.imcode.services;

import com.imcode.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends GenericService<User, Long>, UserDetailsService, PersonalizedService<User> {

    User findByUsername(String username);

//    User findByPerson(Person person);

//    User findByPersonId(Long personId);

//    default User findByPersonalId(String personalId){return null;}

    default User getCurrentUser() { return null; }

    default User findByEmail(String email) {
        return null;
    }

//    public boolean checkAutorisation(String login, String pwd);
}
