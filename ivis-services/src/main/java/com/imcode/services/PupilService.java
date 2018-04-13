package com.imcode.services;

import com.imcode.entities.Person;
import com.imcode.entities.Pupil;

/**
 * Created by vitaly on 17.02.15.
 */
public interface PupilService extends GenericService<Pupil, Long>, PersonalizedService<Pupil> {

    Pupil getPupilByPerson(Person person);
}
