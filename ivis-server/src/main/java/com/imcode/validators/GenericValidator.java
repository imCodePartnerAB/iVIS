package com.imcode.validators;

import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.utils.StaticUtls;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by ruslan on 17.08.16.
 */
public class GenericValidator implements Validator {

    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String URL_PATTERN =
            "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";

    public enum Constraint {NOT_NULL_OR_EMPTY, MAX, MIN, REGEX, MATCH_WITH, NULL}

    private Map<String, Map<Constraint, String>> fieldsConstraints;

//    private BindingResult bindingResult;

//    public GenericValidator() {
//    }

//    public GenericValidator(BindingResult bindingResult) {
//        this.bindingResult = bindingResult;
//    }

    public GenericValidator(Map<String, Map<Constraint, String>> fieldsConstraints) {
        this.fieldsConstraints = fieldsConstraints;
    }

    public GenericValidator(boolean isNull, String... nullFields) {

        fieldsConstraints = new HashMap<>();

        for (String nullField : nullFields) {
            buildField(fieldsConstraints, nullField, new AbstractMap.SimpleEntry<>(
                    isNull ? Constraint.NULL : Constraint.NOT_NULL_OR_EMPTY,
                    null
            ));
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
                                  AbstractMap.SimpleEntry<GenericValidator.Constraint, String>... constraints) {
        Map<GenericValidator.Constraint, String> constraintsResult = new HashMap<>();
        for (AbstractMap.SimpleEntry<Constraint, String> constraint : constraints) {
            constraintsResult.put(constraint.getKey(), constraint.getValue());
        }
        fields.put(name, constraintsResult);
    }

    public void invoke(Object entity, BindingResult bindingResult) throws MethodArgumentNotValidException {
        validate(entity, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(getMethodParameter(getClass(), "invoke", 0, Object.class, BindingResult.class), bindingResult);
        }
    }

    private void checkNotNullOrEmpty(Errors errors, String field) {
        Object fieldValue = errors.getFieldValue(field);

        if (fieldValue == null) {
            errors.rejectValue(field, null, field + " can not be null");
        } else {
            if (fieldValue instanceof Collection) {
                Collection collection = (Collection) fieldValue;
                if (collection.isEmpty()) {
                    errors.rejectValue(field, null, field + " can not be empty or null");
                }
            }

            if (fieldValue instanceof String) {
                if (((String) fieldValue).isEmpty()) {
                        errors.rejectValue(field, null, field + " can not be empty or null");
                }
            }
        }

        if (fieldValue instanceof Map) {
            Collection values = ((Map) fieldValue).values();
            if (values.stream().allMatch(o -> o.toString().isEmpty())) {
                errors.rejectValue(field, null, field + " can not be empty or null");
            }
        }
    }

    private void checkNull(Errors errors, String field) {

        Object fieldValue = errors.getFieldValue(field);

        if (fieldValue instanceof Collection) {
            if (!((Collection) fieldValue).isEmpty()) {
                String defaultMessage = field + " must be ignored";
                errors.rejectValue(field, null, defaultMessage);
            }
        } else if (fieldValue instanceof String) {
            if (!((String) fieldValue).isEmpty()) {
                String defaultMessage = field + " must be ignored";
                errors.rejectValue(field, null, defaultMessage);
            }
        } else if (fieldValue != null) {
            String defaultMessage = field + " must be ignored";
            errors.rejectValue(field, null, defaultMessage);
        }

        if (fieldValue instanceof Map) {
            Collection values = ((Map) fieldValue).values();
            if (values.stream().allMatch(o -> !o.toString().isEmpty())) {
                errors.rejectValue(field, null, field + "  must be ignored");
            }
        }

    }

    private void checkMax(Errors errors, String field, String value) {
        Object fieldValue = errors.getFieldValue(field);

        checkMap(fieldValue);

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

        if (fieldValue instanceof Collection) {
            if (((Collection) fieldValue).size() > Integer.parseInt(value)) {
                errors.rejectValue(field, null, field + " lenght can not be more than " + value);
            }
        }

    }

    private void checkMin(Errors errors, String field, String value) {
        Object fieldValue = errors.getFieldValue(field);

        checkMap(fieldValue);

        if (fieldValue instanceof Number) {
            if (value.compareTo(fieldValue.toString()) > 0) {
                errors.rejectValue(field, null, field +" can not be less than " + value);
            }
        }

        if (fieldValue instanceof String) {
            if (fieldValue.toString().length() < Integer.parseInt(value)) {
                errors.rejectValue(field, null, field + " lenght can not be less than " + value);
            }
        }

        if (fieldValue instanceof Collection) {
            if (((Collection) fieldValue).size() < Integer.parseInt(value)) {
                errors.rejectValue(field, null, field + " lenght can not be more than " + value);
            }
        }
    }

    private void checkRegex(Errors errors, String field, String value) {
        Object fieldValue = errors.getFieldValue(field);

        checkMap(fieldValue);

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

        checkMap(fieldValue);

        if (fieldValue != null) {
            Object forCheck = errors.getFieldValue(value);
            if (!(fieldValue.toString().equals(forCheck.toString()))) {
                errors.rejectValue(field, null, field + " does not match with " + value);
            }
        }
    }

    private void checkMap(Object fieldValue) {
        if (fieldValue instanceof Map) {
            Collection values = ((Map) fieldValue).values();
            Optional first = values.stream().map(Object::toString).findFirst();
            if (first.isPresent()) {
                fieldValue = first.get().toString();
            }
        }
    }

    private MethodParameter getMethodParameter(Class<?> clazz, String methodName, int index, Class<?> ... parameterTypes) {
        MethodParameter methodParameter = null;
        try {
            methodParameter = new MethodParameter(clazz.getMethod(methodName, parameterTypes), index);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return methodParameter;
    }

}
