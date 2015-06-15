package imcode.services;

import com.imcode.entities.AbstractIdEntity;
import com.imcode.services.GenericService;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class GenericServiceProxy<T extends AbstractIdEntity, ID extends Serializable> implements InvocationHandler {
    private GenericService<T, ID> service;


    private GenericServiceProxy(GenericService<T, ID> service) {

        this.service = service;

    }

    @SuppressWarnings("unchecked")
    public static <T extends AbstractIdEntity, ID extends Serializable> GenericService<T, ID> newInstance(GenericService<T, ID> service, Class<?>... interf) {

        return (GenericService<T, ID>) Proxy.newProxyInstance(service.getClass().getClassLoader(), interf, new GenericServiceProxy<>(service));

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

//        Class<?> declaringClass = method.getDeclaringClass();
//
//        for (Class<?> interf : service.getClass().getInterfaces()) {
//            if (declaringClass.isAssignableFrom(interf)) {
//                try {
//                    return method.invoke(service, args);
//                } catch (InvocationTargetException e) {
//                    throw e.getTargetException();
//                }
//            }
//        }

        try {
            return method.invoke(service, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
}