package com.imcode.specifications;

import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.search.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by ruslan on 08.11.16.
 */
public abstract class AbstractSpecification<T extends AbstractIdEntity> implements Specification<T> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getFieldName()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getFieldName()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(
                        criteria.getFieldName()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(
                        criteria.getFieldName()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(
                        criteria.getFieldName()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.get(criteria.getFieldName()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getFieldName()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.get(
                        criteria.getFieldName()), "%" + criteria.getValue() + "%");
            default:
                return null;
        }
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }

}
