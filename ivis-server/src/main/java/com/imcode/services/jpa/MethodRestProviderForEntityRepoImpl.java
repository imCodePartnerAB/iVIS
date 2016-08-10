package com.imcode.services.jpa;

import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.repositories.MethodRestProviderForEntityRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.AbstractService;
import com.imcode.services.MethodRestProviderForEntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ruslan on 01.08.16.
 */
@Service
public class MethodRestProviderForEntityRepoImpl extends AbstractNamedService<MethodRestProviderForEntity,
        Long, MethodRestProviderForEntityRepository> implements MethodRestProviderForEntityService{

    @Override
    public void deleteAll() {
        getRepo().deleteAll();
    }

}
