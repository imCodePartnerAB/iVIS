package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Guardian;
import com.imcode.services.GuardianService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/{format}/guardians")
public class GuardianRestControllerImpl extends AbstractRestController<Guardian, Long, GuardianService> {

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
    public List<Guardian> getByPersonalId(@RequestParam("personalId") String personId,
                                         HttpServletResponse response
    ) {
        return super.getByPersonalId(personId, response);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"personalId", "first"})
    public Guardian getFirstByPersonalId(@RequestParam("personalId") String personId,
                                  HttpServletResponse response
    ) {
        return super.getFirstByPersonalId(personId, response);
    }
}
