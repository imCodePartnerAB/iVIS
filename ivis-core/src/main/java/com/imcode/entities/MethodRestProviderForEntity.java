package com.imcode.entities;

import com.imcode.entities.embed.RestMethod;
import com.imcode.entities.superclasses.AbstractNamedEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by ruslan on 01.08.16.
 */
@Entity
@Table(name = "dbo_method_rest_provider_for_entity")
public class MethodRestProviderForEntity extends AbstractNamedEntity<Long> implements Serializable {

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name="dbo_method_in_parameters")
    @MapKeyColumn(name="")
    Map<String, Object> inParameters;

}
