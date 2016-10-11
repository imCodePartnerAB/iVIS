package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Incident;
import com.imcode.entities.Issue;
import com.imcode.services.IncidentService;
import com.imcode.services.IssueService;
import com.imcode.services.UserService;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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

        Set<Incident> incidentsMerged = mergeIncidents(entity.getIncidents());

        entity.setIncidents(incidentsMerged);

        entity.setReportedDate(new Date());
        entity.setReportedBy(StaticUtls.getCurrentUser(webRequest, userService).getPerson());

        Issue issue = issueService.save(entity);

        saveIncidents(incidentsMerged, issue);

        return issue;
    }

    @Override
    public Object update(@PathVariable("id") Long aLong, HttpServletResponse response, @RequestBody(required = false) @Valid Issue entity, BindingResult bindingResult, WebRequest webRequest) throws Exception {
        if (entity.getReportedBy() != null && entity.getReportedDate() != null) {
            entity.setModifiedBy(StaticUtls.getCurrentUser(webRequest, userService).getPerson());
            entity.setModifiedDate(new Date());
        }
        Issue updated = (Issue) super.update(aLong, response, entity, bindingResult, webRequest);
        Set<Incident> incidents = entity.getIncidents();
        if (incidents != null && !incidents.isEmpty()) {
            Set<Incident> incidentsMerged = mergeIncidents(incidents);
            updated.setIncidents(incidentsMerged);
            saveIncidents(incidentsMerged, updated);
        }
        return updated;
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
//        GeneralValidator.buildField(fieldsConstraints, "status",
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null)
//        );
//
//        GeneralValidator.buildField(fieldsConstraints, "responsiblePerson",
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null)
//        );
//
//        GeneralValidator.buildField(fieldsConstraints, "authorizedPersons",
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null)
//        );
//
//        GeneralValidator.buildField(fieldsConstraints, "incidents",
//                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null)
//        );
//
//        return fieldsConstraints;
//    }
}
