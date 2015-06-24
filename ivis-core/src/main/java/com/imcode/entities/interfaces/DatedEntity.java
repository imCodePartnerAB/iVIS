package com.imcode.entities.interfaces;

import java.util.Date;

/**
 * Created by vitaly on 22.06.15.
 */
public interface DatedEntity {
    Date getCreateDate();

    void setCreateDate(Date date);

    Date getUpdateDate();

    void setUpdateDate(Date date);
}
