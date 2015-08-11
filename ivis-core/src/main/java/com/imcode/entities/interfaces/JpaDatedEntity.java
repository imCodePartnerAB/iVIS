package com.imcode.entities.interfaces;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vitaly on 22.06.15.
 */
public interface JpaDatedEntity<ID extends Serializable> extends JpaEntity<ID> {
    Date getCreateDate();

    void setCreateDate(Date date);

    Date getUpdateDate();

    void setUpdateDate(Date date);
}
