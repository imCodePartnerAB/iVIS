package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Issue;
import com.imcode.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by ruslan on 5/11/16.
 */
@RestController
@RequestMapping("/v1/{format}/issues")
public class IssueRestControllerImpl extends AbstractRestController<Issue, Long, IssueService> {

    @Autowired
    IssueService issueService;

    @RequestMapping(method = RequestMethod.GET, params = {"search_text", "order_by"})
    public Object findByCriteria (@RequestParam(value = "search_text") String searchText,
                                  @RequestParam(value = "order_by") String orderBy,
                                  WebRequest webRequest) {

        if (orderBy.equals("title"))
            return issueService.findBySearchCriteria(searchText, orderBy);

        return null;
    }


}
