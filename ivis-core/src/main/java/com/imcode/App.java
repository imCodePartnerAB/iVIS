package com.imcode;

import com.imcode.entities.Role;
import org.springframework.security.oauth2.common.util.SerializationUtils;

import java.util.HashSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "ADF"));
        roles.add(new Role(2L, "GFGD"));
        roles.add(new Role(3L, "HHH"));
        byte[] bytes = SerializationUtils.serialize(roles);
        HashSet<Role> role1 = SerializationUtils.deserialize(bytes);
        System.out.println(role1);
        System.out.println("Hello World!");
    }
}
