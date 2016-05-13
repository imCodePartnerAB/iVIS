package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Application;
import com.imcode.entities.Incident;
import com.imcode.entities.User;
import com.imcode.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by ruslan on 5/4/16.
 */
@RestController
@RequestMapping("/v1/{format}/incidents")
public class IncidentRestControllerImpl extends AbstractRestController<Incident, Long, IncidentService> {

    @Autowired
    IncidentService incidentService;

    @Override
    public Object create(@RequestBody Incident entity, WebRequest webRequest) {
        entity.setReportDay(new Date());
        return super.create(entity, webRequest);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"search_text", "order_by"})
    public Object findByCriteria (@RequestParam(value = "search_text") String searchText,
                                  @RequestParam(value = "order_by") String orderBy,
                                  WebRequest webRequest) {

        if (orderBy.equals("title"))
            return incidentService.findBySearchCriteria(searchText, orderBy);

        return null;
    }



}
