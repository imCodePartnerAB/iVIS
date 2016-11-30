package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.EntityVersion;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.services.EntityVersionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/{format}/entityversions")
public class EntityVersionRestControllerImpl extends AbstractRestController<EntityVersion, Long, EntityVersionService> {

    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.GET, params = {"entityId"})
    public List<EntityVersion> getByEntityId(@RequestParam("entityClassName") String entityClassName, @RequestParam("entityId") Long entityID) {
        try {
            Class<JpaEntity> entityClass;
            JpaEntity<Long> entity;
            entityClass = (Class<JpaEntity>) Class.forName(entityClassName);
            entity = entityClass.newInstance();
            entity.setId(entityID);
            return getService().findByEntity(entity);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class \"" + entityClassName + "\" not found!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

