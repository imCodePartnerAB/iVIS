package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.*;
import com.imcode.search.SearchCriteries;
import com.imcode.services.PersonRoleService;
import com.imcode.services.PersonService;
import com.imcode.services.UserService;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/{format}")
public class PersonRestControllerImpl extends AbstractRestController<Person, Long, PersonService> {

    private final UserService userService;
    private final PersonRoleService personRoleService;

    private static final String PATH_PLURAL = "/persons";
    private static final String PATH_SINGLE = "/person";

    private static final Long CURRENT_FLAG = Long.MIN_VALUE;

    @Autowired
    public PersonRestControllerImpl(UserService userService, PersonRoleService personRoleService) {
        this.userService = userService;
        this.personRoleService = personRoleService;
    }

    //Plural

    @RequestMapping(value = PATH_PLURAL, method = RequestMethod.GET, params = {"search_text", "order_by"})
    public List<Person> findByCriteria (@RequestParam(value = "search_text") String searchText,
                                  @RequestParam(value = "order_by") String orderBy,
                                        HttpServletResponse response,
                                  WebRequest webRequest) {

        if (orderBy.equals("last_name") || orderBy.equals("first_name")) {
            String validateOrderBy = orderBy.replace("_n", "N");
            return getService().findBySearchCriteria(searchText, validateOrderBy);
        }

        return null;

    }

    @Override
    @RequestMapping(value = PATH_PLURAL, method = RequestMethod.GET, params = {"personalId"})
    public List<Person> getByPersonalId(@RequestParam("personalId") String personId,
                                HttpServletResponse response) throws Exception {
        return super.getByPersonalId(personId, response);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL, method = RequestMethod.GET, params = {"personalId", "first"})
    public Person getFirstByPersonalId(@RequestParam("personalId") String personId,
                                         HttpServletResponse response) throws Exception {
        return super.getFirstByPersonalId(personId, response);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL, method = RequestMethod.GET)
    public List<Person> getAll(WebRequest webRequest, HttpServletResponse response, Model model) throws Exception {
        return super.getAll(webRequest, response, model);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Person create(@RequestBody @Valid Person entity, HttpServletResponse response, BindingResult bindingResult, WebRequest webRequest) throws Exception {
        return super.create(entity, response, bindingResult, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL + "/saveall", method = RequestMethod.POST)
    public Iterable<Person> saveAll(@RequestBody Iterable<Person> entities, HttpServletResponse response, WebRequest webRequest) throws Exception {
        return super.saveAll(entities, response, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL + "/saveall", method = RequestMethod.POST, params = {"full"})
    public List<Long> saveAllAndReturnIds(@RequestBody Iterable<Person> entities, HttpServletResponse response, WebRequest webRequest) throws Exception {
        return super.saveAllAndReturnIds(entities, response, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL + "/{id}", method = RequestMethod.PUT)
    public Person update(@PathVariable("id") Long aLong, HttpServletResponse response, Person entity, BindingResult bindingResult, WebRequest webRequest) throws Exception {
        return super.update(aLong, response, entity, bindingResult, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL + "/{id}", method = RequestMethod.DELETE)
    public Person delete(@PathVariable("id") Long aLong, HttpServletResponse response, WebRequest webRequest) throws Exception {
        return super.delete(aLong, response, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL + "/search", method = RequestMethod.POST)
    public List<Person> search(@RequestBody List<SearchCriteries.SearchCriteriaResult> criteries) throws Exception {
        return super.search(criteries);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL + "/search/first", method = RequestMethod.POST)
    public Person searchFirst(@RequestBody List<SearchCriteries.SearchCriteriaResult> criteries) throws Exception {
        return super.searchFirst(criteries);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL, method = RequestMethod.DELETE, params = {"ids"})
    public List<Person> deleteByIds(@RequestParam("ids") List<Long> longs, HttpServletResponse response, WebRequest webRequest) throws Exception {
        return super.deleteByIds(longs, response, webRequest);
    }

    //Single

    @Override
    @RequestMapping(value = PATH_SINGLE + "/{id}", method = RequestMethod.GET)
    public Person get(@PathVariable("id") Long id, HttpServletResponse response, WebRequest webRequest) throws Exception {
        return super.get(id, response, webRequest);
    }

    @RequestMapping(value = PATH_SINGLE + "/current", method = RequestMethod.GET)
    public Person getCurrentPerson(WebRequest webRequest) {
        return _getCurrentPerson(webRequest);
    }

    @RequestMapping(value =  PATH_SINGLE + "/current/personroles", method = RequestMethod.GET)
    public List<PersonRole> getPersonRolesOfCurrentPerson(WebRequest webRequest) {
        return getPersonRoles(CURRENT_FLAG, webRequest);
    }

    @RequestMapping(value =  PATH_SINGLE + "/{id}/personroles", method = RequestMethod.GET)
    public List<PersonRole> getPersonRolesByPerson(@PathVariable("id") Long id, WebRequest webRequest) {
        return getPersonRoles(id, webRequest);
    }

    @RequestMapping(value =  PATH_SINGLE + "/current/schools", method = RequestMethod.GET)
    public List<School> getSchoolsOfCurrentPerson(WebRequest webRequest) {
        return StaticUtls.mapByRuleAndGetDistinct(getPersonRoles(CURRENT_FLAG, webRequest), PersonRole::getSchool);
    }

    @RequestMapping(value =  PATH_SINGLE + "/{id}/schools", method = RequestMethod.GET)
    public List<School> getSchoolsByPerson(@PathVariable("id") Long id, WebRequest webRequest) {
        return StaticUtls.mapByRuleAndGetDistinct(getPersonRoles(id, webRequest), PersonRole::getSchool);
    }

    @RequestMapping(value =  PATH_SINGLE + "/current/schoolclasses", method = RequestMethod.GET)
    public List<SchoolClass> getSchoolClassesOfCurrentPerson(WebRequest webRequest) {
        return StaticUtls.mapByRuleAndGetDistinct(getPersonRoles(CURRENT_FLAG, webRequest), PersonRole::getSchoolClass);
    }

    @RequestMapping(value =  PATH_SINGLE + "/{id}/schoolclasses", method = RequestMethod.GET)
    public List<SchoolClass> getSchoolClassesByPerson(@PathVariable("id") Long id, WebRequest webRequest) {
        return StaticUtls.mapByRuleAndGetDistinct(getPersonRoles(id, webRequest), PersonRole::getSchoolClass);
    }

    @RequestMapping(value =  PATH_SINGLE + "/current/workroles", method = RequestMethod.GET)
    public List<WorkRole> getWorkRolesOfCurrentPerson(WebRequest webRequest) {
        return StaticUtls.mapByRuleAndGetDistinct(getPersonRoles(CURRENT_FLAG, webRequest), PersonRole::getRole);
    }

    @RequestMapping(value =  PATH_SINGLE + "/{id}/workroles", method = RequestMethod.GET)
    public List<WorkRole> getWorkRolesByPerson(@PathVariable("id") Long id, WebRequest webRequest) {
        return StaticUtls.mapByRuleAndGetDistinct(getPersonRoles(id, webRequest), PersonRole::getRole);
    }

    private Person _getCurrentPerson(WebRequest webRequest) {
        return StaticUtls.getCurrentUser(webRequest, userService).getPerson();
    }

    private List<PersonRole> getPersonRoles(Long id, WebRequest webRequest) {
        final Person person;
        if (id.equals(CURRENT_FLAG)) {
            person = _getCurrentPerson(webRequest);
        } else {
            person = getService().find(id);
        }
        return personRoleService.findByPerson(person);
    }

}
