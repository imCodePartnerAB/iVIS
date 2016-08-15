package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Person;
import com.imcode.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/v1/{format}/persons")
public class PersonRestControllerImpl extends AbstractRestController<Person, Long, PersonService> {
//    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
//    Person getPersonByPersonalId(@RequestParam("personalId") String personId,
//                           @RequestParam(value = "first", required = false) Boolean firstOnly) {
//        Person person = getService().findFirstByPersonalId(personId);
//        return person;
//    }
    @Autowired
    PersonService personService;


    @RequestMapping(method = RequestMethod.GET, params = {"search_text", "order_by"})
    public Object findByCriteria (@RequestParam(value = "search_text") String searchText,
                                  @RequestParam(value = "order_by") String orderBy,
                                  WebRequest webRequest) {

        if (orderBy.equals("last_name") || orderBy.equals("first_name")) {
            String validateOrderBy = orderBy.replace("_n", "N");
            return personService.findBySearchCriteria(searchText, validateOrderBy);
        }


        return null;

    }

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
    public Object getByPersonalId(@RequestParam("personalId") String personId, @RequestParam(value = "first", required = false) Boolean firstOnly) {
        return super.getByPersonalId(personId, firstOnly);
    }
}
