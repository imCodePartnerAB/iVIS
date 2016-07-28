package com.imcode.entities.superclasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imcode.entities.interfaces.JpaDatedEntity;
import com.imcode.entities.listners.DatedEntityListner;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by vitaly on 13.05.15.
 */
@MappedSuperclass
@EntityListeners({DatedEntityListner.class})
public abstract class AbstractJpaDatedEntity<ID extends Serializable> extends AbstractIdEntity<ID> implements Serializable, JpaDatedEntity<ID> {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    protected Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", insertable = false)
    protected Date updateDate;

    public AbstractJpaDatedEntity() {
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date date) {
        createDate = date;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date date) {
        updateDate = date;
    }
}
