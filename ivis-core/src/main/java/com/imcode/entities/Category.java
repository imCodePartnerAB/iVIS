package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ruslan on 5/6/16.
 */
@Entity
@Table(name = "dbo_category")
public class Category extends AbstractNamedEntity<Long> {
}
