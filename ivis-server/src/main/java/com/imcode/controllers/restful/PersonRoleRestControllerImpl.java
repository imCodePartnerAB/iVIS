package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.*;
import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.services.PersonRoleService;
import com.imcode.services.UserService;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/v1/{format}/personroles")
public class PersonRoleRestControllerImpl extends AbstractRestController<PersonRole, Long, PersonRoleService> {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/ofcurrentuser", method = RequestMethod.GET)
    public List<PersonRole> getPersonRolesOfCurrentUser(WebRequest webRequest) {
        return getService().findByPerson(StaticUtls.getCurrentUser(webRequest, userService).getPerson());
    }

    @RequestMapping(value = "/schools/ofcurrentuser", method = RequestMethod.GET)
    public List<School> getDistinctSchoolsOfCurrentUser(WebRequest webRequest) {
        return getDistinctItemsOfCurrentUser(webRequest, PersonRole::getSchool);
    }

    @RequestMapping(value = "/schoolclasses/ofcurrentuser", method = RequestMethod.GET)
    public List<SchoolClass> getDistinctSchoolClassesOfCurrentUser(WebRequest webRequest) {
        return getDistinctItemsOfCurrentUser(webRequest, PersonRole::getSchoolClass);
    }

    @RequestMapping(value = "/workroles/ofcurrentuser", method = RequestMethod.GET)
    public List<WorkRole> getDistinctWorkRolesOfCurrentUser(WebRequest webRequest) {
        return getDistinctItemsOfCurrentUser(webRequest, PersonRole::getRole);
    }

    private <T extends AbstractIdEntity<Long>> List<T> getDistinctItemsOfCurrentUser(WebRequest webRequest, Function<PersonRole, T> t) {
        final Map<Long, T> distinct = new HashMap<>();
        getPersonRolesOfCurrentUser(webRequest)
                .stream()
                .map(t)
                .forEach(item -> distinct.putIfAbsent(item.getId(), item));
        return new ArrayList<>(distinct.values());
    }

}
