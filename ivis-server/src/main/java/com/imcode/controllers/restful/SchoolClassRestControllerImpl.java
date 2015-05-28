package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.SchoolClass;
import com.imcode.services.SchoolClassService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/v1/{format}/classes")
public class SchoolClassRestControllerImpl extends AbstractRestController<SchoolClass, Long, SchoolClassService> {

    @RequestMapping("/test")
    @ResponseBody
    public Object test(@RequestParam("param") String param, WebRequest webRequest) throws Exception {
        if ("kill".equals(param))
            throw new Exception();
        return param;
    }
}

