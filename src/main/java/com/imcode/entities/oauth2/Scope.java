package com.imcode.entities.oauth2;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by vitaly on 14.05.15.
 */
@Embeddable
public class Scope {
    @Column
    private String name;

    @Column
    private Boolean autoApprove;
}
