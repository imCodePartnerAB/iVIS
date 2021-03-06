package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.AcademicYear;
import com.imcode.services.AcademicYearService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/{format}/academicyears")
public class AcademicYearRestControllerImpl extends AbstractRestController<AcademicYear, Long, AcademicYearService> {

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public List<AcademicYear> getByName(WebRequest webRequest, Model model,
                                        HttpServletResponse response,
                                        @RequestParam("name") String name) {
        return super.getByName(webRequest, model, response, name);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"name", "first"})
    public AcademicYear getFirstByName(WebRequest webRequest, Model model, HttpServletResponse response,
                                       @RequestParam("name") String name) {
        return super.getFirstByName(webRequest, model, response, name);
    }
}
