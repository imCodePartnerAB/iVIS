package com.imcode.services.jpa;

import com.imcode.entities.Activity;
import com.imcode.repositories.ActivityRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.ActivityService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 5/12/16.
 */
@Service
public class ActivityRepoImpl extends AbstractService<Activity, Long, ActivityRepository> implements ActivityService {

}
