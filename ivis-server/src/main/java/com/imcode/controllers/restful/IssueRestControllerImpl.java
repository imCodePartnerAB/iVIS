package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Incident;
import com.imcode.entities.Issue;
import com.imcode.services.IncidentService;
import com.imcode.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.criteria.CriteriaBuilder;
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

    @Override
    public Object create(@RequestBody Issue entity, WebRequest webRequest) {
        Issue issue = issueService.save(entity);

        Set<Incident> incidents = entity.getIncidents();

        incidents = saveIssueToIncidents(incidents, issue);

        issue.setIncidents(incidents);

        return issueService.save(issue);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"search_text", "order_by"})
    public Object findByCriteria (@RequestParam(value = "search_text") String searchText,
                                  @RequestParam(value = "order_by") String orderBy,
                                  WebRequest webRequest) {

        if (orderBy.equals("title"))
            return issueService.findBySearchCriteria(searchText, orderBy);

        return null;
    }

    private Set<Incident> saveIssueToIncidents(Set<Incident> incidents, Issue issue) {
        return incidents.stream()
                .map(incident -> incidentService.find(incident.getId()))
                .peek(incident -> incident.setIssue(issue))
                .map(incident -> incidentService.save(incident))
                .collect(Collectors.toSet());
    }


}
