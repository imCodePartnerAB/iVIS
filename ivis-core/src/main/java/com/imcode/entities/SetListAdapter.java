package com.imcode.entities;


import java.util.*;

/**
 * Created by vitaly on 24.06.15.
 */
public class SetListAdapter<T> implements List<T>{

    private final LinkedHashSet<T> nestedSet;
    private final Map<Integer, T> indexMap;

    public SetListAdapter(LinkedHashSet<T> nestedSet) {
        this.nestedSet = nestedSet;
        this.indexMap = new HashMap<>();
        fillIndexMap(nestedSet);
    }

    private void fillIndexMap(LinkedHashSet<T> nestedSet) {
        int i = 0;
        for (T value : nestedSet) {
            indexMap.put(i++, value);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) indexMap.get(index);
    }

    @Override
    public T set(int index, T element) {
        rangeCheck(index);
        T oldValue = get(index);
        if (oldValue != null) {
            T[] oldOrder = (T[]) nestedSet.toArray();

            for (int i = 0; i < oldOrder.length; i++) {
                T entity = oldOrder[i];
                if (oldValue.equals(entity)) {
                    oldOrder[i] = element;
                }
            }

            nestedSet.clear();
            nestedSet.addAll(Arrays.asList(oldOrder));

            for (Map.Entry<Integer, T> entry : indexMap.entrySet()) {
                T value = entry.getValue();
                if (oldValue.equals(value))
                    entry.setValue(element);
            }
        }

        return null;
    }

    @Override
    public int size() {
        return indexMap.size();
    }

    @Override
    public boolean isEmpty() {
        return nestedSet.isEmpty();
    }

    @Override
    public boolean add(T t) {
        for (Map.Entry<Integer, T> entry : indexMap.entrySet()) {
            T value = entry.getValue();

            if (value == null) {
                if (t == null) {
                    indexMap.put(indexMap.size(), value);
                    return true;
                }
            }else if (value.equals(t)) {
                indexMap.put(indexMap.size(), value);
                return true;
            }
        }

        indexMap.put(indexMap.size(), t);
        nestedSet.add(t);

        return true;
    }

    @Override
    public String toString() {
        return indexMap.values().toString();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        LinkedHashSet set = new LinkedHashSet();
        Entity e0 = new Entity(0);
        Entity e1 = new Entity(1);
        Entity e2 = new Entity(2);
        Entity e3 = new Entity(4);
        set.add(e0);
        set.add(e1);
        set.add(e2);
        set.add(e3);
        SetListAdapter<Entity> listAdapter = new SetListAdapter(set);
        listAdapter.add(new Entity(5));
        listAdapter.add(e2);
        e2.setId(20);
        Entity e = listAdapter.get(5);
        e.setId(200);
        listAdapter.add(null);
        listAdapter.add(null);
        System.out.println(listAdapter.indexMap);
        System.out.println(set);
        System.out.println("----------------------------------------------");
        listAdapter.set(5, new Entity(6));
        System.out.println(listAdapter.indexMap);
        System.out.println(set);

    }
}

class Entity extends AbstractIdEntity<Integer> {
    public Entity() {
    }

    public Entity(Integer integer) {
        super(integer);
    }
}
