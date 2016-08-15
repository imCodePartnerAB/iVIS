package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.SchoolClass;
import com.imcode.services.SchoolClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/v1/{format}/schoolclasses")
public class SchoolClassRestControllerImpl extends AbstractRestController<SchoolClass, Long, SchoolClassService> {

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public Object getByName(WebRequest webRequest, Model model, @RequestParam("name") String name, @RequestParam(value = "first", required = false) Boolean firstOnly) {
        return super.getByName(webRequest, model, name, firstOnly);
    }
}

