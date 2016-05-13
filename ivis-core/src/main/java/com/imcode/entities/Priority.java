package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by ruslan on 5/6/16.
 */
@Entity
@Table(name = "dbo_priority")
public class Priority extends AbstractNamedEntity<Long> implements Serializable{
}
