package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Status;
import com.imcode.services.StatusService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ruslan on 5/10/16.
 */
@RestController
@RequestMapping("/v1/{format}/statuses")
public class StatusRestControllerImpl extends AbstractRestController<Status, Long, StatusService> {
}
