package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.superclasses.AbstractSortableNamedEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by vitaly on 15.10.15.
 */
@Entity
@Table(name = "dbo_application_form_question")
public class ApplicationFormQuestion extends AbstractSortableNamedEntity<Long> implements Serializable {
    public ApplicationFormQuestion() {
    }

    public ApplicationFormQuestion(String name, String text, String value, String questionType) {
        this(name, null, text, value, false, Collections.singletonList(value), String.class, false, Collections.emptyList(), questionType, null);
    }

    public ApplicationFormQuestion(String name, Integer sortOrder, String text, String value, Boolean multiValues, List<String> values, Class<? extends Serializable> valueType, Boolean multiVariants, List<String> variants, String questionType, ApplicationFormQuestionGroup questionGroup) {
        super(null, name, sortOrder);
        this.text = text;
        this.value = value;
        this.multiValues = multiValues;
        this.values = values;
//        this.valueType = valueType;
        this.multiVariants = multiVariants;
        this.variants = variants;
        this.questionType = questionType;
        this.questionGroup = questionGroup;
    }

    @JsonIgnoreProperties(value = "questions")
    public ApplicationFormQuestionGroup getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(ApplicationFormQuestionGroup questionGroup) {
        this.questionGroup = questionGroup;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @SuppressWarnings("unchecked")
    public class Helper<T extends Serializable> {
        private Helper() {}

        public T getValue() {
            return (T) value;
        }

        public List<T> getValues() {
            return (List<T>) values;
        }

        public List<T> getVariants() {
            return (List<T>) variants;
        }
    }

//    public <T extends Serializable> Helper<T> getHelper(Class<T> valueType) {
//        if (valueType != this.valueType) {
//            throw new IllegalArgumentException("Value type '" + valueType + "' of argument and value type of question '" + this.valueType + "' isn't equals");
//        }
//
//        return new Helper<>();
//    }

    public static class ListToStringConverter implements AttributeConverter<List, String> {
//                private static final ObjectMapper mapper = new ObjectMapper();
        @Override
        public String convertToDatabaseColumn(List attribute) {
            List<String> stringList = attribute;
            return stringList.stream().collect(Collectors.joining(","));
        }

        @Override
        public List convertToEntityAttribute(String dbData) {
            return new ArrayList<>(Arrays.asList(dbData.split(",")));
        }
    }

    @Column
    private String text;

    @Column
    private String value;

    @Column(name = "multi_values")
    private Boolean multiValues = Boolean.FALSE;

    @Column(name = "valuez")
    @Convert(converter = ListToStringConverter.class)
    private List<String> values = new ArrayList<>();

//    @Column(nullable = false)
//    private Class<? extends Serializable> valueType = String.class;

    @Column(name = "multi_variants", nullable = false)
    private Boolean multiVariants = Boolean.FALSE;

    @Convert(converter = ListToStringConverter.class)
    private List<String> variants = new ArrayList<>();

    @Column(name = "question_type")
    private String questionType;

    @ManyToOne
    @JoinColumn(name = "question_group_id")
    private ApplicationFormQuestionGroup questionGroup;

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

    public Boolean getMultiValues() {
        return multiValues;
    }

    public void setMultiValues(Boolean multiValues) {
        this.multiValues = multiValues;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

//    public Class<? extends Serializable> getValueType() {
//        return valueType;
//    }
//
//    public void setValueType(Class<? extends Serializable> valueType) {
//        this.valueType = valueType;
//    }

    public Boolean getMultiVariants() {
        return multiVariants;
    }

    public void setMultiVariants(Boolean multiVariants) {
        this.multiVariants = multiVariants;
    }

    public List<String> getVariants() {
        return variants;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }



    @Override
    public boolean deepEquals(JpaEntity entity) {
        if (!super.deepEquals(entity)) {
            return false;
        }

        ApplicationFormQuestion that = (ApplicationFormQuestion) entity;

        return Objects.equals(this.text, that.text)
                && Objects.equals(this.questionType, that.questionType)
                && Objects.equals(this.value, that.value)
                && Objects.equals(this.multiValues, that.multiValues)
                && Objects.equals(this.values, that.values)
//                && Objects.equals(this.valueType, that.valueType)
                && Objects.equals(this.multiVariants, that.multiVariants)
                && Objects.equals(this.variants, that.variants);
    }

    //    private static Comparator<ApplicationFormQuestion> naturalComparator = nullsLast(comparing(ApplicationFormQuestion::getSortOrder, nullsLast(comparingInt(Integer::intValue))));

//    @Column
//    private Integer sortOrder;

//    @Column
//    private String xsdElementName;

//    @Column
//    private String subStepName;
//
//    @Column
//    private String stepName;
//
//    @Column
//    private Integer stepSortOrder;
//
//    @Column
//    private String text;
//
//    @Column
//    private String value;
//
//    public Integer getSortOrder() {
//        return sortOrder;
//    }
//
//    public void setSortOrder(Integer sortOrder) {
//        this.sortOrder = sortOrder;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
//
//    public String getXsdElementName() {
//        return xsdElementName;
//    }
//
//    public void setXsdElementName(String xsdElementName) {
//        this.xsdElementName = xsdElementName;
//    }
//
//    public String getStepName() {
//        return stepName;
//    }
//
//    public void setStepName(String stepName) {
//        this.stepName = stepName;
//    }
//
//    public Integer getStepSortOrder() {
//        return stepSortOrder;
//    }
//
//    public void setStepSortOrder(Integer stepSortOrder) {
//        this.stepSortOrder = stepSortOrder;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        ApplicationFormQuestion that = (ApplicationFormQuestion) o;
//
//        return !(xsdElementName != null ? !xsdElementName.equals(that.xsdElementName) : that.xsdElementName != null);
//
//    }
//
//    @Override
//    public int hashCode() {
//        return xsdElementName != null ? xsdElementName.hashCode() : 0;
//    }

//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder()
//                .append(xsdElementName)
//                .append(": ")
//                .append(value);
//
//        return sb.toString();
//    }

//    @Override
//    public int compareTo(ApplicationFormQuestion o) {
//        return naturalComparator.compare(this, o);
//    }

//    public static void main(String[] args) {
////        Comparator<ApplicationFormQuestion> comparator = new Comparator<ApplicationFormQuestion>() {
////            @Override
////            public int compare(ApplicationFormQuestion o1, ApplicationFormQuestion o2) {
////                Integer sortOrder1 = o1.getSortOrder();
////                Integer sortOrder2 = o2.getSortOrder();
////
////                if (sortOrder1 == null || sortOrder2 == null) {
////
////                }
////
////                return 0;
////            }
////        };
//
//        ApplicationFormQuestion q1 = new ApplicationFormQuestion();
//        ApplicationFormQuestion q2 = new ApplicationFormQuestion();
//        q1.setSortOrder(1);
//        q2.setSortOrder(3);
//        System.out.println(naturalComparator.compare(q1, q2));
//
//    }

//    public String getSubStepName() {
//        return subStepName;
//    }
//
//    public void setSubStepName(String subStepName) {
//        this.subStepName = subStepName;
//    }
}
