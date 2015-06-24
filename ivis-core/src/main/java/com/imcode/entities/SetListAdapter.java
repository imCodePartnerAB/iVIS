package com.imcode.entities;


import java.util.*;

/**
 * Created by vitaly on 24.06.15.
 */
public class SetListAdapter<T> extends AbstractList<T> implements List<T>{

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

    @Override
    public T get(int index) {
//        nestedSet.
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    public static void main(String[] args) {
    }
}
