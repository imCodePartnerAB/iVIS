package imcode.services.utils.builders;

import java.util.*;
import java.util.function.IntFunction;

/**
 * Created by ruslan on 28.10.16.
 */
public class CollectionBuilder<T> {

    private Collection<T> collection;

    private Class<?> clazz;
    private boolean needAdd = true;

    public static <T> List<T> asList(T ... elems) {
        return (List<T>) initList().addAll(elems);
    }

    public static <T> List<T> asLinkedList(T ... elems) {
        return (List<T>) initLinkedList().addAll(elems);
    }

    public static <T> Set<T> asSet(T ... elems) {
        return (Set<T>) initSet().addAll(elems);
    }

    public static <T> Set<T> asLinkedSet(T ... elems) {
        return (Set<T>) initLinkedSet().addAll(elems);
    }

    public static <T> CollectionBuilder<T> initList() {
        return initCol(new ArrayList<T>());
    }

    public static <T> CollectionBuilder<T> initLinkedList() {
        return initCol(new LinkedList<T>());
    }

    public static <T> CollectionBuilder<T> initSet() {
        return initCol(new HashSet<T>());
    }

    public static <T> CollectionBuilder<T> initLinkedSet() {
        return initCol(new LinkedHashSet<T>());
    }

    public CollectionBuilder<T> add(T e) {
        if (needAdd) {
            this.collection.add(e);
        } else {
            needAdd = true;
        }
        return this;
    }

    public CollectionBuilder<T> needNext(boolean flag) {
        this.needAdd = flag;
        return this;
    }

    public Collection<T> build() {
        return this.collection;
    }

    public List<T> buildList() {
        assert collection instanceof List : "It is not a list";
        return (List<T>) this.collection;
    }

    public Set<T> buildSet() {
        assert collection instanceof Set : "It is not a set";
        return (Set<T>) this.collection;
    }

    public CollectionBuilder<T> setClass(Class<?> clazz) {
        assert clazz == null : "Class was set.";
        this.clazz = clazz;
        return this;
    }

    public CollectionBuilder<T> constructAndAdd(Object ... initArgs) {
        if (needAdd) {
            Class<?>[] classArgs = initList()
                    .addAll(initArgs)
                    .stream()
                    .map(Object::getClass)
                    .toArray((IntFunction<Class<?>[]>) Class[]::new);
            T instance;
            try {
                instance = (T) this.clazz.getConstructor(classArgs).newInstance(initArgs);
            } catch (Exception e) {
                throw new IllegalArgumentException("Can not construct object. Arguments not valid");
            }

            collection.add(instance);
        } else {
            needAdd = true;
        }
        return this;
    }

    private CollectionBuilder() {
    }

    private static <T> CollectionBuilder<T> initCol(Collection<T> collection) {
        CollectionBuilder<T> builder = new CollectionBuilder<> ();
        builder.collection = collection;
        return builder;
    }

    private Collection<T> addAll(T ... elems) {
        Collections.addAll(collection, elems);
        return collection;
    }

}
