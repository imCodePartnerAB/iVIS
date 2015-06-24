package com.imcode.entities.listners;

import com.imcode.entities.interfaces.DatedEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by vitaly on 22.06.15.
 */
public class DatedEntityListner {
    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof DatedEntity) {
            DatedEntity datedEntity = (DatedEntity) entity;
            datedEntity.setCreateDate(new Date());
            datedEntity.setUpdateDate(new Date());
        }
    }

    @PreUpdate
    public void PreUpdate(Object entity) {
        if (entity instanceof DatedEntity) {
            DatedEntity datedEntity = (DatedEntity) entity;
            datedEntity.setUpdateDate(new Date());
        }
    }
}
