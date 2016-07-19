package com.imcode.entities.superclasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.interfaces.JpaNamedEntity;
import com.imcode.entities.interfaces.JpaSortableEntity;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vitaly on 13.05.15.
 */
@MappedSuperclass
public abstract class AbstractSortableNamedEntity<ID extends Serializable> extends AbstractNamedEntity<ID> implements JpaSortableEntity<AbstractSortableNamedEntity>{
    @Column(name = "sort_order")
    @JsonProperty("sort_order")
    protected Integer sortOrder;

    public AbstractSortableNamedEntity() {
    }

    public AbstractSortableNamedEntity(ID id, String name, Integer sortOrder) {
        super(id, name);
        this.sortOrder = sortOrder;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Integer getSortOrder() {
        return sortOrder;
    }


    @Override
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int compareTo(AbstractSortableNamedEntity o) {
        int result = JpaSortableEntity.DEFAULT_COMPARATOR.compare(this, o);
        return result == 0 ? ObjectUtils.compare(this.getName(), o.getName(), true) : result;
    }

    @Override
    public boolean deepEquals(JpaEntity entity) {
        if (!super.deepEquals(entity)) {
            return false;
        }

        AbstractSortableNamedEntity that = (AbstractSortableNamedEntity) entity;

        return Objects.equals(this.sortOrder, that.sortOrder)
//               && Objects.equals(this., that.)
                ;
    }
}
