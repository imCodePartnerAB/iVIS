package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.SchoolClass;
import com.imcode.services.SchoolClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/{format}/schoolclasses")
public class SchoolClassRestControllerImpl extends AbstractRestController<SchoolClass, Long, SchoolClassService> {

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public List<SchoolClass> getByName(WebRequest webRequest, Model model,
                                        HttpServletResponse response,
                                        @RequestParam("name") String name) throws Exception {
        return super.getByName(webRequest, model, response, name);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"name", "first"})
    public SchoolClass getFirstByName(WebRequest webRequest, Model model, HttpServletResponse response,
                                       @RequestParam("name") String name) throws Exception {
        return super.getFirstByName(webRequest, model, response, name);
    }
}

