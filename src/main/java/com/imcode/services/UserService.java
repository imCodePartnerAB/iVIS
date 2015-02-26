package com.imcode.services;

import com.imcode.entities.User;

public interface UserService extends GenericService<User, Long>{

    public User getBylogin(String login);

    public boolean checkAutorisation(String login, String pwd);
}
