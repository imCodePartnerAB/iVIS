package com.imcode.entities.listners;

import com.imcode.entities.interfaces.JpaDatedEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by vitaly on 22.06.15.
 */
public class DatedEntityListner {
    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof JpaDatedEntity) {
            JpaDatedEntity jpaDatedEntity = (JpaDatedEntity) entity;
            jpaDatedEntity.setCreateDate(new Date());
            jpaDatedEntity.setUpdateDate(new Date());
        }
    }

    @PreUpdate
    public void PreUpdate(Object entity) {
        if (entity instanceof JpaDatedEntity) {
            JpaDatedEntity jpaDatedEntity = (JpaDatedEntity) entity;
            jpaDatedEntity.setUpdateDate(new Date());
        }
    }
}
