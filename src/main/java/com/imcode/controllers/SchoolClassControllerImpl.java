package com.imcode.controllers;

import com.imcode.entities.SchoolClass;
import com.imcode.services.SchoolClassService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/sclasses")
public class SchoolClassControllerImpl extends AbstractController<SchoolClass, Long, SchoolClassService> {

    @RequestMapping("/test")
    @ResponseBody
    public Object test(@RequestParam("param") String param, WebRequest webRequest) throws Exception {
        if ("kill".equals(param))
            throw new Exception();
        return param;
    }
}

