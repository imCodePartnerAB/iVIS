package com.imcode.utils;

import java.util.Collection;

/**
 * Created by ruslan on 11.08.16.
 */
public class CollectionTransferUtil<T> {

    private Collection<T> collection;

    public CollectionTransferUtil() {
    }

    public CollectionTransferUtil(Collection<T> collection) {
        this.collection = collection;
    }

    public Collection<T> getCollection() {
        return collection;
    }

    public void setCollection(Collection<T> collection) {
        this.collection = collection;
    }
}
