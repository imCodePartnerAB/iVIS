package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Person;
import com.imcode.entities.PersonRole;
import com.imcode.entities.School;
import com.imcode.search.SearchCriteries;
import com.imcode.services.PersonRoleService;
import com.imcode.services.SchoolService;
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
public class SchoolRestControllerImpl extends AbstractRestController<School, Long, SchoolService> {

    private static final String PATH_PLURAL = "/schools";
    private static final String PATH_SINGLE = "/school";
    private final PersonRoleService personRoleService;

    @Autowired
    public SchoolRestControllerImpl(PersonRoleService personRoleService) {
        this.personRoleService = personRoleService;
    }

    @Override
    @RequestMapping(value = PATH_PLURAL, method = RequestMethod.GET, params = {"name"})
    public List<School> getByName(WebRequest webRequest, Model model,
                                       HttpServletResponse response,
                                       @RequestParam("name") String name) throws Exception {
        return super.getByName(webRequest, model, response, name);
    }

    @Override
    @RequestMapping(value = PATH_SINGLE, method = RequestMethod.GET, params = {"name", "first"})
    public School getFirstByName(WebRequest webRequest, Model model, HttpServletResponse response,
                                      @RequestParam("name") String name) throws Exception {
        return super.getFirstByName(webRequest, model, response, name);
    }

    @Override
    @RequestMapping(value = PATH_SINGLE + "/{id}", method = RequestMethod.GET)
    public School get(@PathVariable("id") Long aLong, HttpServletResponse response, WebRequest webRequest) throws Exception {
        return super.get(aLong, response, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL, method = RequestMethod.GET)
    public List<School> getAll(WebRequest webRequest, HttpServletResponse response, Model model) throws Exception {
        return super.getAll(webRequest, response, model);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = PATH_SINGLE, method = RequestMethod.POST)
    public @ResponseBody School create(@RequestBody @Valid School entity, HttpServletResponse response, BindingResult bindingResult, WebRequest webRequest) throws Exception {
        return super.create(entity, response, bindingResult, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL + "/saveall", method = RequestMethod.POST)
    public @ResponseBody Iterable<School> saveAll(@RequestBody Iterable<School> entities, HttpServletResponse response, WebRequest webRequest) throws Exception {
        return super.saveAll(entities, response, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL + "/saveall", method = RequestMethod.POST, params = {"full"})
    public @ResponseBody List<Long> saveAllAndReturnIds(@RequestBody Iterable<School> entities, HttpServletResponse response, WebRequest webRequest) throws Exception {
        return super.saveAllAndReturnIds(entities, response, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_SINGLE + "/{id}", method = RequestMethod.PUT)
    public School update(@PathVariable("id") Long aLong, HttpServletResponse response, School entity, BindingResult bindingResult, WebRequest webRequest) throws Exception {
        return super.update(aLong, response, entity, bindingResult, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_SINGLE + "/{id}", method = RequestMethod.DELETE)
    public School delete(@PathVariable("id") Long aLong, HttpServletResponse response, WebRequest webRequest) throws Exception {
        return super.delete(aLong, response, webRequest);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL + "/search", method = RequestMethod.POST)
    public @ResponseBody List<School> search(@RequestBody List<SearchCriteries.SearchCriteriaResult> criteries) throws Exception {
        return super.search(criteries);
    }

    @Override
    @RequestMapping(value = PATH_SINGLE + "/search", method = RequestMethod.POST)
    public School searchFirst(@RequestBody List<SearchCriteries.SearchCriteriaResult> criteries) throws Exception {
        return super.searchFirst(criteries);
    }

    @Override
    @RequestMapping(value = PATH_PLURAL, method = RequestMethod.DELETE, params = {"ids"})
    public @ResponseBody List<School> deleteByIds(@RequestParam("ids") List<Long> longs, HttpServletResponse response, WebRequest webRequest) throws Exception {
        return super.deleteByIds(longs, response, webRequest);
    }

    @RequestMapping(value = PATH_SINGLE + "/{id}/persons", method = RequestMethod.GET)
    public List<Person> getPersonsBySchool(@PathVariable("id") Long aLong, HttpServletResponse response, WebRequest webRequest) throws Exception {
        List<PersonRole> workRoles = personRoleService.findBySchool(super.get(aLong, response, webRequest));
        return StaticUtls.mapByRuleAndGetDistinct(workRoles, PersonRole::getPerson);
    }

}
