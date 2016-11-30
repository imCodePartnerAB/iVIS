package com.imcode.services.jpa;

import com.imcode.entities.Priority;
import com.imcode.repositories.PriorityRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.PriorityService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 5/10/16.
 */
@Service
public class PriorityRepoImpl extends AbstractService<Priority, Long, PriorityRepository> implements PriorityService {
}
