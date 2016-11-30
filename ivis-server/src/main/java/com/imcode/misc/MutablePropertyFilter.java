package com.imcode.misc;

import java.lang.reflect.Method;
import java.util.function.Predicate;

/**
 * Created by vitaly on 08.12.15.
 */
class MutablePropertyFilter implements Predicate<Method> {
    private final Class<?> beanType;

    MutablePropertyFilter(Class<?> beanType) {
        this.beanType = beanType;
    }

    @Override
    public boolean test(Method method) {
        String methodName = method.getName();

        //Check for bean property "getX"
        if (!methodName.startsWith("get")) {
            return false;
        }

        //Check bean property for returning correct value (not void)
        Class<?> returnType = method.getReturnType();
        if (returnType == Void.class) {
            return false;
        }

        String setterName = "s" + methodName.substring(1);
        try {
            beanType.getMethod(setterName, returnType);
        } catch (NoSuchMethodException e) {
            return false;
        }

        return true;
    }


}
