package com.imcode.entities.embed;

import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.entities.superclasses.AbstractNamedEntity;
import org.codehaus.jackson.map.util.Comparators;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Comparator;

import static java.util.Comparator.*;
import static java.util.Comparator.comparing;

/**
 * Created by vitaly on 15.10.15.
 */
@Embeddable
public class ApplicationFormQuestion implements Comparable<ApplicationFormQuestion>{
    private static Comparator<ApplicationFormQuestion> naturalComparator = nullsFirst(comparing(ApplicationFormQuestion::getSortOrder));

    @Column
    private Integer sortOrder;

    @Column
    private String xsdElementName;

    @Column
    private String stepName;

    @Column
    private Integer stepSortOrder;

    @Column
    private String text;

    @Column
    private String value;

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getXsdElementName() {
        return xsdElementName;
    }

    public void setXsdElementName(String xsdElementName) {
        this.xsdElementName = xsdElementName;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Integer getStepSortOrder() {
        return stepSortOrder;
    }

    public void setStepSortOrder(Integer stepSortOrder) {
        this.stepSortOrder = stepSortOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationFormQuestion that = (ApplicationFormQuestion) o;

        return !(xsdElementName != null ? !xsdElementName.equals(that.xsdElementName) : that.xsdElementName != null);

    }

    @Override
    public int hashCode() {
        return xsdElementName != null ? xsdElementName.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder()
                .append(xsdElementName)
                .append(": ")
                .append(value);

        return sb.toString();
    }

    @Override
    public int compareTo(ApplicationFormQuestion o) {
        return naturalComparator.compare(this, o);
    }
}
