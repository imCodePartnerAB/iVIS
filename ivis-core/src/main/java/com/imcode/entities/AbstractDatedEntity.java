package com.imcode.entities;

import com.imcode.entities.interfaces.DatedEntity;
import com.imcode.entities.listners.DatedEntityListner;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by vitaly on 13.05.15.
 */
@MappedSuperclass
@EntityListeners({DatedEntityListner.class})
public abstract class AbstractDatedEntity<ID extends Serializable> extends AbstractIdEntity<ID> implements Serializable, DatedEntity {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    protected Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    protected Date updateDate;

    public AbstractDatedEntity() {
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
