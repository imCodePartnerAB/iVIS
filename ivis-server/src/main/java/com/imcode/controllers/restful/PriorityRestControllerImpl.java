package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Priority;
import com.imcode.services.PriorityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ruslan on 5/10/16.
 */
@RestController
@RequestMapping("/v1/{format}/priorities")
public class PriorityRestControllerImpl extends AbstractRestController<Priority, Long, PriorityService> {
}
