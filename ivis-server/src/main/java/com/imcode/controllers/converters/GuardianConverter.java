package com.imcode.controllers.converters;

import com.imcode.entities.Guardian;
import com.imcode.services.PersonalizedService;

/**
 * Created by vitaly on 11.12.15.
 */
public class GuardianConverter extends PersonalizedConverter<Guardian>{
    public GuardianConverter(PersonalizedService<Guardian> entityService) {
        super(entityService);
    }
}
