package com.imcode.services;

import com.imcode.entities.EntityRestProviderInformation;

/**
 * Created by ruslan on 01.08.16.
 */
public interface EntityRestProviderInformationService extends GenericService<EntityRestProviderInformation, Long> {

    void deleteAll();

}
