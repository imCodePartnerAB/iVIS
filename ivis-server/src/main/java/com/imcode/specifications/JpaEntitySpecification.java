package com.imcode.specifications;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.search.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by ruslan on 08.11.16.
 */
public class JpaEntitySpecification<T extends JpaEntity> implements Specification<T> {

    private SearchCriteria criteria;

    public JpaEntitySpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALS:
                return builder.equal(root.get(criteria.getFieldName()), criteria.getValue());
            case NOT_EQUALS:
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

}
