package com.imcode.repositories;


import com.imcode.entities.Person;
import com.imcode.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PersonalizedRepository<User> {
    @Query("select u from User u where u.name = :username")
    User findByUsername(@Param("username") String username);

    User findBySaml2Id(String saml2Id);

    @Query(value = "SELECT * FROM dbo_user u " +
            "INNER JOIN dbo_person p ON u.person_id = p.id " +
            "INNER JOIN dbo_person_email e ON e.owner_id = p.id " +
            "WHERE e.value = ?1",
            nativeQuery = true)
    User findByEmail(String email);

//    User findByPerson(Person person);

//    @Query("select u from User u where u.person.personalId = :id")
//    User findByPersonalId(String personalId);
//    @Query("select u from _User u where u.login = :login")
//    _User findByLogin(@Param("login") String login);

//    @Query("select case when count(e) > 0 then true else false end from _User e where e.login = :login and e.pwd = :pwd")
//    boolean checkAutorisation(@Param("login") String login, @Param("pwd") String pwd);

}

