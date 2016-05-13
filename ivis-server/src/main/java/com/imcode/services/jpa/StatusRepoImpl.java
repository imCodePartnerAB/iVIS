package com.imcode.services.jpa;

import com.imcode.entities.Status;
import com.imcode.repositories.StatusRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.StatusService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 5/10/16.
 */
@Service
public class StatusRepoImpl extends AbstractService<Status, Long, StatusRepository> implements StatusService {
}
