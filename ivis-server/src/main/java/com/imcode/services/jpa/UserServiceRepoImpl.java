package com.imcode.services.jpa;

import com.imcode.entities.Person;
import com.imcode.entities.Role;
import com.imcode.entities.User;
import com.imcode.repositories.UserRepository;
import com.imcode.services.AbstractPersonalizedEntityService;
import com.imcode.services.AbstractService;
import com.imcode.services.NamedService;
import com.imcode.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceRepoImpl extends AbstractPersonalizedEntityService<User, UserRepository> implements UserService {

    @Override
    public User findByUsername(String username) {
        return getRepo().findByUsername(username);
    }

//    @Override
//    public User findByPerson(Person person) {
//        return getRepo().findByPerson(person);
//    }
//
//    @Override
//    public User findByPersonId(Long personId) {
//        throw new UnsupportedOperationException();
////        return null;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getRepo().findByUsername(username);
        if (user == null) {
            getLogger().debug("Query returned no results for user '" + username + "'");

            throw new UsernameNotFoundException(
                    getMessageSource().getMessage("UserService.notFound", new Object[]{username}, "Username {0} not found", null));
        }

        Set<Role> roles = user.getAuthorities();
        if (roles == null || roles.size() == 0) {
            getLogger().debug("User '" + username + "' has no authorities and will be treated as 'not found'");

            throw new UsernameNotFoundException(
                    getMessageSource().getMessage("UserService.noAuthority",
                            new Object[] {username}, "User {0} has no GrantedAuthority", null));
        }

        return user;
    }

//    @Override
//    public User findByPersonalId(String personalId) {
//        return repo.findByPersonalId(personalId);
//    }

//    @Override
//    public boolean checkAutorisation(String login, String pwd) {
//        return getRepo().checkAutorisation(login, pwd);
//    }

}
