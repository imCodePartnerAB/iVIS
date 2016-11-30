package com.imcode.entities.interfaces;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vitaly on 10.07.15.
 */
public interface JpaTimeStampVersionedEntity<ID extends Serializable> extends JpaVersionedEntity<Date, ID> {
}
