package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Incident;
import com.imcode.entities.Status;
import com.imcode.services.IncidentService;
import com.imcode.services.StatusService;
import com.imcode.services.UserService;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ruslan on 5/4/16.
 */
@RestController
@RequestMapping("/v1/{format}/incidents")
public class IncidentRestControllerImpl extends AbstractRestController<Incident, Long, IncidentService> {

    @Autowired
    IncidentService incidentService;

    @Autowired
    UserService userService;

    @Autowired
    StatusService statusService;

    @Override
    public Object create(@RequestBody @Valid Incident entity,
                         HttpServletResponse response,
                         BindingResult bindingResult, WebRequest webRequest) throws Exception {
        entity.setReportedDate(new Date());
        entity.setReportedBy(StaticUtls.getCurrentUser(webRequest, userService).getPerson());
        List<Status> statuses = statusService.findAll();
        entity.setStatus(
                statuses.stream()
                        .filter(status -> status.getName().equals(Status.State.NEW))
                        .findFirst()
                        .get()
        );
        return super.create(entity, response, bindingResult, webRequest);
    }

    @Override
    public Object update(@PathVariable("id") Long aLong, HttpServletResponse response, @RequestBody(required = false) @Valid Incident entity, BindingResult bindingResult, WebRequest webRequest) throws Exception {
        if (entity.getReportedDate() != null && entity.getReportedBy() != null) {
            entity.setModifiedBy(StaticUtls.getCurrentUser(webRequest, userService).getPerson());
            entity.setModifiedDate(new Date());
        }
        return super.update(aLong, response, entity, bindingResult, webRequest);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"search_text", "order_by"})
    public Object findByCriteria (@RequestParam(value = "search_text") String searchText,
                                  @RequestParam(value = "order_by") String orderBy,
                                  WebRequest webRequest) {

        if (orderBy.equals("title")) {
            return incidentService.findBySearchCriteria(searchText, orderBy);
        }

        return null;
    }

//    @Override
//    protected Map<String, Map<GeneralValidator.Constraint, String>> getFieldsConstraints() {
//        Map<String, Map<GeneralValidator.Constraint, String>> fieldsConstraints = super.getFieldsConstraints();
//
//        GeneralValidator.buildField(fieldsConstraints, "title",
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.MIN, "4")
//        );
//
//        GeneralValidator.buildField(fieldsConstraints, "description",
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.MIN, "4")
//        );
//
//        GeneralValidator.buildField(fieldsConstraints, "categories",
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null)
//        );
//
//        GeneralValidator.buildField(fieldsConstraints, "pupils",
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null)
//        );
//
//        GeneralValidator.buildField(fieldsConstraints, "priority",
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null)
//        );
//
//        return fieldsConstraints;
//    }
}
