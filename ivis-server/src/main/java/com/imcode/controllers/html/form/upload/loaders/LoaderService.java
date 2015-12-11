package com.imcode.controllers.html.form.upload.loaders;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by vitaly on 11.12.15.
 */
public class LoaderService implements Iterable<Map.Entry<Class<?>, EntityLoader<?>>>{
    private Map<Class<?>, EntityLoader<?>> loaders = new HashMap<>();

    public EntityLoader<?> getLoader(Class<?> clazz) {
        return loaders.get(clazz);
    }

    public Collection<Class<?>> getManagedClasses() {
        return loaders.keySet();
    }

    public boolean hasLoader(Class<?> clazz) {
        return loaders.get(clazz) != null;
    }

    public Map<Class<?>, EntityLoader<?>> getLoaders() {
        return loaders;
    }

    public void setLoaders(Map<Class<?>, EntityLoader<?>> loaders) {
        this.loaders = loaders;
    }

    @Override
    public Iterator<Map.Entry<Class<?>, EntityLoader<?>>> iterator() {
        return loaders.entrySet().iterator();
    }

    public Stream<Map.Entry<Class<?>, EntityLoader<?>>> stream() {
        return loaders.entrySet().stream();
    }
}
