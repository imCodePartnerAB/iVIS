package com.imcode.entities.observer;

import com.imcode.entities.interfaces.JpaAuditableEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by vitaly on 12.02.16.
 */
public class LogUtils {
    public static List<String> ignoredFields = Arrays.asList("willbesaved", "id", "createDate", "updateDate");

    public static Set<Difference> getDifferences(Map<String, Object> oldValues, Map<String, Object> newValues) {
        Set<Difference> differences = new HashSet<>();
        for (Map.Entry<String, Object> entry : newValues.entrySet()) {
            Object oldForNew = oldValues.get(entry.getKey());
            if (oldForNew == null) {
                if (entry.getValue() != null) {
                    differences.add(new Difference(null, entry));
                }
            } else {
                if (!oldForNew.equals(entry.getValue())) {
                    differences.add(new Difference(oldForNew, entry));
                }
            }
        }
        return differences;
    }

    public static Map<String, Object> getValues(JpaAuditableEntity model) {
        Class<?> clazz = model.getClass();
        Map<String, Object> map = new HashMap<>();
        Field[] fields = FieldUtils.getAllFields(clazz);
        for (Field field : fields) {

            // Skip fields
            if (field.getAnnotation(Transient.class) != null ||
                    ignoredFields.contains(field.getName())) {
                continue;
            }


//            String capitalizedField = JavaExtensions.capitalizeWords(field.getName().toLowerCase());
            String capitalizedField = StringUtils.capitalize(field.getName());
            String getterName = "get" + capitalizedField;
            String isName = "is" + capitalizedField;
            MethodResult methodResult = runMethod(getterName, model);
            if (methodResult.run) {
                map.put(field.getName(), methodResult.value);
                continue;
            }

            methodResult = runMethod(isName, model);
            if (methodResult.run) {
                map.put(field.getName(), methodResult.value);
                continue;
            }

            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(model));
            } catch (IllegalArgumentException e) {
                // Does nothing in this case
            } catch (IllegalAccessException e) {
                // Does nothing in this case
            }
        }

        return map;
    }

    private static MethodResult runMethod(String name, Object model) {
        return invokeMethod(getMethod(model.getClass(), name), model);
    }

    private static MethodResult invokeMethod(Method method, Object object) {
        if (method == null) {
            return MethodResult.didntRun();
        }
        try {
            return new MethodResult(true, method.invoke(object));
        } catch (IllegalArgumentException e) {
            return MethodResult.didntRun();
        } catch (IllegalAccessException e) {
            return MethodResult.didntRun();
        } catch (InvocationTargetException e) {
            return MethodResult.didntRun();
        }

    }

    private static Method getMethod(Class<?> clazz, String getterName) {
        Method method = null;
        try {
            method = clazz.getMethod(getterName);
        } catch (SecurityException e) {
            // Does nothing will try other thing
            method = null;
        } catch (NoSuchMethodException e) {
            // Does nothing will try other thing
            method = null;
        }
        return method;
    }

    public static class MethodResult {
        private boolean run;
        private Object value = null;

        public MethodResult(boolean run, Object value) {
            this.run = run;
            this.value = value;
        }

        public static MethodResult didntRun() {
            return new MethodResult(false, null);
        }

    }

    public static class Difference {
        public Object oldValue;
        public Object newValue;
        public String field;

        public Difference(Object oldValue, Map.Entry<String, Object> newValue) {
            this.oldValue = oldValue;
            this.newValue = newValue.getValue();
            this.field = newValue.getKey();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((field == null) ? 0 : field.hashCode());
            result = prime * result + ((newValue == null) ? 0 : newValue.hashCode());
            result = prime * result + ((oldValue == null) ? 0 : oldValue.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Difference other = (Difference) obj;
            if (field == null) {
                if (other.field != null) return false;
            } else if (!field.equals(other.field)) return false;
            if (newValue == null) {
                if (other.newValue != null) return false;
            } else if (!newValue.equals(other.newValue)) return false;
            if (oldValue == null) {
                if (other.oldValue != null) return false;
            } else if (!oldValue.equals(other.oldValue)) return false;
            return true;
        }


    }

}
