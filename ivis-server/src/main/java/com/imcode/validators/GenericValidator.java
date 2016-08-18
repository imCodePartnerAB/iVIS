package com.imcode.validators;

import com.imcode.entities.superclasses.AbstractIdEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.*;


/**
 * Created by ruslan on 17.08.16.
 */
public class GenericValidator implements Validator {

    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String URL_PATTERN =
            "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";

    public enum Constraint { NOT_NULL_OR_EMPTY, MAX, MIN, REGEX, MATCH_WITH, NULL}

    private Map<String, Map<Constraint, String>> fieldsConstraints;

    public GenericValidator(Map<String, Map<Constraint, String>> fieldsConstraints) {
        this.fieldsConstraints = fieldsConstraints;
    }

    public GenericValidator(String ... nullFields) {

        fieldsConstraints = new HashMap<>();

        for (String nullField : nullFields) {
            buildField(fieldsConstraints, nullField, new AbstractMap.SimpleEntry<>(Constraint.NULL, null));
        }

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AbstractIdEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Set<String> fields = fieldsConstraints.keySet();

        for (String field : fields) {
            Map<Constraint, String> constraints = fieldsConstraints.get(field);

            for (Constraint constraintName : constraints.keySet()) {

                String value = constraints.get(constraintName);

                switch (constraintName) {

                    case NOT_NULL_OR_EMPTY:
                        checkNotNullOrEmpty(errors, field);
                        break;

                    case NULL:
                        checkNull(errors, field);
                        break;

                    case MAX:
                        checkMax(errors, field, value);
                        break;

                    case MIN:
                        checkMin(errors, field, value);
                        break;

                    case REGEX:
                        checkRegex(errors, field, value);
                        break;

                    case MATCH_WITH:
                        checkMatchWith(errors, field, value);
                        break;

                }


            }
        }

    }

    @SafeVarargs
    public static void buildField(Map<String, Map<GenericValidator.Constraint, String>> fields,
                                  String name,
                                  AbstractMap.SimpleEntry<GenericValidator.Constraint, String> ... constraints) {
        Map<GenericValidator.Constraint, String> constraintsResult = new HashMap<>();
        for (AbstractMap.SimpleEntry<Constraint, String> constraint : constraints) {
            constraintsResult.put(constraint.getKey(), constraint.getValue());
        }
        fields.put(name, constraintsResult);
    }

    private void checkNotNullOrEmpty(Errors errors, String field) {
        Object fieldValue = errors.getFieldValue(field);

        if (fieldValue == null) {
            errors.rejectValue(field, null, field + " can not be null");
        } else {
            if (fieldValue instanceof Collection) {
                if (((Collection) fieldValue).isEmpty()) {
                    errors.rejectValue(field, null, field + " can not be empty or null");
                }
            }
        }
    }

    private void checkNull(Errors errors, String field) {

        Object fieldValue = errors.getFieldValue(field);

        if (fieldValue != null) {
            errors.rejectValue(field, null, field + " must be ignored");
        }

    }

    private void checkMax(Errors errors, String field, String value) {
        Object fieldValue = errors.getFieldValue(field);
        if (fieldValue instanceof Number) {
            if (value.compareTo(fieldValue.toString()) < 0) {
                errors.rejectValue(field, null, field + " can not be more than " + value);
            }
        }

        if (fieldValue instanceof String) {
            if (fieldValue.toString().length() > Integer.parseInt(value)) {
                errors.rejectValue(field, null, field + " lenght can not be more than " + value);
            }
        }
    }

    private void checkMin(Errors errors, String field, String value) {
        Object fieldValue = errors.getFieldValue(field);
        if (fieldValue instanceof Number) {
            if (value.compareTo(fieldValue.toString()) > 0) {
                errors.rejectValue(field, null, field + " can not be less than " + value);
            }
        }

        if (fieldValue instanceof String) {
            if (fieldValue.toString().length() < Integer.parseInt(value)) {
                errors.rejectValue(field, null, field + " lenght can not be less than " + value);
            }
        }
    }

    private void checkRegex(Errors errors, String field, String value) {
        Object fieldValue = errors.getFieldValue(field);

        if (fieldValue != null) {
            String determiner = "";

            switch (value) {

                case URL_PATTERN:
                    determiner = "URL";
                    break;

                case EMAIL_PATTERN:
                    determiner = "email";
                    break;

            }

            if (!(fieldValue.toString().matches(value))) {
                errors.rejectValue(field, null, field + " does not match with " + determiner + " pattern");
            }
        }

    }

    private void checkMatchWith(Errors errors, String field, String value) {
        Object fieldValue = errors.getFieldValue(field);

        if (fieldValue != null) {
            Object forCheck = errors.getFieldValue(value);
            if ( !(fieldValue.toString().equals(forCheck.toString())) ) {
                errors.rejectValue(field, null, field + " does not match with " + value);
            }
        }
    }

}
