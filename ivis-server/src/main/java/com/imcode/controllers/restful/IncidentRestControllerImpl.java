package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Incident;
import com.imcode.entities.Status;
import com.imcode.services.IncidentService;
import com.imcode.services.StatusService;
import com.imcode.services.UserService;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        new GenericValidator(true, "reportDay", "reportedBy", "status").invoke(entity, bindingResult);
        entity.setReportDay(new Date());
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

    @RequestMapping(method = RequestMethod.GET, params = {"search_text", "order_by"})
    public Object findByCriteria (@RequestParam(value = "search_text") String searchText,
                                  @RequestParam(value = "order_by") String orderBy,
                                  WebRequest webRequest) {

        if (orderBy.equals("title")) {
            return incidentService.findBySearchCriteria(searchText, orderBy);
        }

        return null;
    }

    @Override
    protected Map<String, Map<GenericValidator.Constraint, String>> getFieldsConstraints() {
        Map<String, Map<GenericValidator.Constraint, String>> fieldsConstraints = super.getFieldsConstraints();

        GenericValidator.buildField(fieldsConstraints, "title",
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.NOT_NULL_OR_EMPTY, null),
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.MIN, "4")
        );

        GenericValidator.buildField(fieldsConstraints, "description",
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.NOT_NULL_OR_EMPTY, null),
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.MIN, "4")
        );

        GenericValidator.buildField(fieldsConstraints, "categories",
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.NOT_NULL_OR_EMPTY, null)
        );

        GenericValidator.buildField(fieldsConstraints, "pupils",
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.NOT_NULL_OR_EMPTY, null)
        );

        GenericValidator.buildField(fieldsConstraints, "priority",
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.NOT_NULL_OR_EMPTY, null)
        );

        return fieldsConstraints;
    }
}
