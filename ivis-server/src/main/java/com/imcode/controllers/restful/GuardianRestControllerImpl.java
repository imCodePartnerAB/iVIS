package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Guardian;
import com.imcode.services.GuardianService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{format}/guardians")
public class GuardianRestControllerImpl extends AbstractRestController<Guardian, Long, GuardianService> {

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"personalId"})
    public Object getByPersonalId(@RequestParam("personalId") String personId, @RequestParam(value = "first", required = false) Boolean firstOnly) {
        return super.getByPersonalId(personId, firstOnly);
    }
}
