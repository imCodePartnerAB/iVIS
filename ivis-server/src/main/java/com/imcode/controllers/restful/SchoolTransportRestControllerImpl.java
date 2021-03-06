package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.SchoolTransport;
import com.imcode.services.SchoolTransportService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/{format}/schooltransports")
public class SchoolTransportRestControllerImpl extends AbstractRestController<SchoolTransport, Long, SchoolTransportService> {

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public List<SchoolTransport> getByName(WebRequest webRequest, Model model,
                                  HttpServletResponse response,
                                  @RequestParam("name") String name) {
        return super.getByName(webRequest, model, response, name);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"name", "first"})
    public SchoolTransport getFirstByName(WebRequest webRequest, Model model, HttpServletResponse response,
                                 @RequestParam("name") String name) {
        return super.getFirstByName(webRequest, model, response, name);
    }
}
