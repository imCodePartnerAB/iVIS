package com.imcode.entities.embed;

import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.entities.superclasses.AbstractNamedEntity;
import org.codehaus.jackson.map.util.Comparators;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.function.Function;

import static java.util.Comparator.*;
import static java.util.Comparator.comparing;

/**
 * Created by vitaly on 15.10.15.
 */
@Embeddable
public class ApplicationFormQuestion implements Comparable<ApplicationFormQuestion>{
    private static Comparator<ApplicationFormQuestion> naturalComparator = nullsLast(comparing(ApplicationFormQuestion::getSortOrder, nullsLast(comparingInt(Integer::intValue))));
//    private static Comparator<ApplicationFormQuestion> naturalComparator = new Comparator<ApplicationFormQuestion>() {
//        @Override
//        public int compare(ApplicationFormQuestion o1, ApplicationFormQuestion o2) {
//            if (o1 == o2) {
//                return 0;
//            }
//
//            if (o1 == null) {
//                return -1;
//            }
//
//            if (o2 == null) {
//                return 1;
//            }
//
//
//
//            return 0;
//        }
//    };

    @Column
    private Integer sortOrder;

    @Column
    private String xsdElementName;

    @Column
    private String subStepName;

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

    public static void main(String[] args) {
//        Comparator<ApplicationFormQuestion> comparator = new Comparator<ApplicationFormQuestion>() {
//            @Override
//            public int compare(ApplicationFormQuestion o1, ApplicationFormQuestion o2) {
//                Integer sortOrder1 = o1.getSortOrder();
//                Integer sortOrder2 = o2.getSortOrder();
//
//                if (sortOrder1 == null || sortOrder2 == null) {
//
//                }
//
//                return 0;
//            }
//        };

        ApplicationFormQuestion q1 = new ApplicationFormQuestion();
        ApplicationFormQuestion q2 = new ApplicationFormQuestion();
        q1.setSortOrder(1);
        q2.setSortOrder(3);
        System.out.println(naturalComparator.compare(q1, q2));

    }

    public String getSubStepName() {
        return subStepName;
    }

    public void setSubStepName(String subStepName) {
        this.subStepName = subStepName;
    }
}
