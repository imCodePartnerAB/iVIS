package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Pupil;
import com.imcode.entities.Statement;
import com.imcode.services.PupilService;
import com.imcode.services.StatementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/pupils")
public class PupilRestControllerImpl extends AbstractRestController<Pupil, Long, PupilService> {

    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
    Pupil getByPersonalId(@RequestParam("personalId") String personId) {
        Pupil pupil = getService().findByPersonalId(personId);
        return pupil;
    }

}
