package com.imcode.entities.oauth2;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by vitaly on 14.05.15.
 */
@Embeddable
public class Scope implements Serializable {
    @Column
    private String name;

    @Column
    private Boolean autoApprove;
}
