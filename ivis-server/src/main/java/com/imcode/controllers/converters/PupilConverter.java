package com.imcode.controllers.converters;

import com.imcode.entities.Pupil;
import com.imcode.services.PersonalizedService;

/**
 * Created by vitaly on 11.12.15.
 */
public class PupilConverter extends PersonalizedConverter<Pupil>{
    public PupilConverter(PersonalizedService<Pupil> entityService) {
        super(entityService);
    }
}
