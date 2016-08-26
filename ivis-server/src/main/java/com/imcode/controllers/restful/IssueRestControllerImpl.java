package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Incident;
import com.imcode.entities.Issue;
import com.imcode.services.IncidentService;
import com.imcode.services.IssueService;
import com.imcode.services.UserService;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ruslan on 5/11/16.
 */
@RestController
@RequestMapping("/v1/{format}/issues")
public class IssueRestControllerImpl extends AbstractRestController<Issue, Long, IssueService> {

    @Autowired
    IssueService issueService;

    @Autowired
    IncidentService incidentService;

    @Autowired
    UserService userService;

    @Override
    public Object create(@RequestBody @Valid Issue entity,
                         HttpServletResponse response,
                         BindingResult bindingResult, WebRequest webRequest) throws MethodArgumentNotValidException {

        new GenericValidator(true, "reportDay", "reportedBy").invoke(entity, bindingResult);

        Set<Incident> incidentsMerged = mergeIncidents(entity.getIncidents());

        entity.setIncidents(incidentsMerged);

        entity.setReportDay(new Date());
        entity.setReportedBy(StaticUtls.getCurrentUser(webRequest, userService).getPerson());

        Issue issue = issueService.save(entity);

        saveIncidents(incidentsMerged, issue);

        return issue;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"search_text", "order_by"})
    public Object findByCriteria (@RequestParam(value = "search_text") String searchText,
                                  @RequestParam(value = "order_by") String orderBy,
                                  WebRequest webRequest) {

        if (orderBy.equals("title"))
            return issueService.findBySearchCriteria(searchText, orderBy);

        return null;
    }

    private Set<Incident> mergeIncidents(Set<Incident> incidents) {
        return incidents.stream().map(incident -> {
            Incident incidentSaved = incidentService.find(incident.getId());
            try {
                StaticUtls.nullAwareBeanCopy(incidentSaved, incident);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return incidentSaved;
        }).collect(Collectors.toSet());
    }

    private void saveIncidents(Set<Incident> incidents, Issue issue) {
        incidentService.save(
                incidents.stream()
                .peek(incident -> incident.setIssue(issue))
                .collect(Collectors.toSet())
        );
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

        GenericValidator.buildField(fieldsConstraints, "status",
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.NOT_NULL_OR_EMPTY, null)
        );

        GenericValidator.buildField(fieldsConstraints, "responsiblePerson",
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.NOT_NULL_OR_EMPTY, null)
        );

        GenericValidator.buildField(fieldsConstraints, "authorizedPersons",
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.NOT_NULL_OR_EMPTY, null)
        );

        GenericValidator.buildField(fieldsConstraints, "incidents",
                new AbstractMap.SimpleEntry<>(GenericValidator.Constraint.NOT_NULL_OR_EMPTY, null)
        );

        return fieldsConstraints;
    }
}
