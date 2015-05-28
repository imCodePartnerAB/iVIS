package com.imcode.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_statement_form")
public class StatementForm extends AbstractNamedEntity implements Serializable {
    @Column
    private String type;
}
