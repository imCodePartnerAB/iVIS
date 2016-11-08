package com.imcode.specifications.builders;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.search.SearchCriteria;
import com.imcode.search.SearchOperation;
import com.imcode.specifications.AbstractSpecification;
import org.springframework.data.jpa.domain.Specifications;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ruslan on 08.11.16.
 */
public class SpecificationBuilder {

    private static String SPECIFICATIONS_PACKAGE_PREFFIX = "com.imcode.specifications.";
    private static String SPECIFICATIONS_SUFFIX = "Specification";

    private List<SearchCriteria> criteriaList = new LinkedList<>();
    private List<Conjunction> conjunctionList = new LinkedList<>();

    private Class<? extends AbstractSpecification> specificationClass;
    private Class<? extends JpaEntity> entityClass;

    private boolean buildPermit = false;

    private SpecificationBuilder() {
    }

    @SuppressWarnings("unchecked")
    public static SpecificationBuilder from(@NotNull Class<? extends JpaEntity> clazz) {
        SpecificationBuilder builder = new SpecificationBuilder();
        builder.entityClass = clazz;
        String simpleName = clazz.getSimpleName();
        try {
            builder.specificationClass = (Class<? extends AbstractSpecification>)
                    Class.forName(SPECIFICATIONS_PACKAGE_PREFFIX + simpleName + SPECIFICATIONS_SUFFIX);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Specification for class " + simpleName + " does not exist");
        }
        return builder;
    }

    public SpecificationBuilder addStatement(String fieldName, SearchOperation so, Object value) throws NoSuchFieldException {
        entityClass.getField(fieldName);
        buildPermit = true;
        criteriaList.add(new SearchCriteria(fieldName, so, value));
        return this;
    }

    public SpecificationBuilder and() {
        buildPermit = false;
        conjunctionList.add(Conjunction.AND);
        return this;
    }

    public SpecificationBuilder or() {
        buildPermit = false;
        conjunctionList.add(Conjunction.OR);
        return this;
    }

    public AbstractSpecification build() {

        if (!buildPermit) {
            throw new IllegalArgumentException("You can't invoke build");
        }

        if (criteriaList.isEmpty()) {
            return null;
        }

        List<AbstractSpecification> specs = criteriaList.stream()
                .map(this::createNewInstance)
                .collect(Collectors.toList());

        AbstractSpecification result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {

            switch (conjunctionList.get(i-1)) {
                case AND:
                    Specifications.where(result).and(specs.get(i));
                    break;
                case OR:
                    Specifications.where(result).or(specs.get(i));
            }

        }

        return result;
    }

    private AbstractSpecification createNewInstance(SearchCriteria criteria) {
        AbstractSpecification instance = null;
        try {
            instance = specificationClass.newInstance();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Can not create instance of specification");
        }
        instance.setCriteria(criteria);
        return instance;
    }

    private enum Conjunction {AND, OR}


}
