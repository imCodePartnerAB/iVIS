package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.School;
import com.imcode.services.SchoolService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/{format}/schools")
public class SchoolRestControllerImpl extends AbstractRestController<School, Long, SchoolService> {

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public List<School> getByName(WebRequest webRequest, Model model,
                                       HttpServletResponse response,
                                       @RequestParam("name") String name) throws Exception {
        return super.getByName(webRequest, model, response, name);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"name", "first"})
    public School getFirstByName(WebRequest webRequest, Model model, HttpServletResponse response,
                                      @RequestParam("name") String name) throws Exception {
        return super.getFirstByName(webRequest, model, response, name);
    }
}
