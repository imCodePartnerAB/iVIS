package imcode.services.utils.builders;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ruslan on 28.10.16.
 */
public class MapBuilder<K, V>  {

    private Map<K, V> map;

    public static <K, V> MapBuilder<K, V> initMap() {
        MapBuilder<K, V> builder = new MapBuilder<> ();
        builder.map = new HashMap<>();
        return builder;
    }

    public static <K, V> MapBuilder<K, V> initLinkedMap() {
        MapBuilder<K, V> builder = new MapBuilder<> ();
        builder.map = new LinkedHashMap<>();
        return builder;
    }

    public static <K, V, M extends Map<K, V>> MapBuilder<K, V> initMap(Class<M> clazz) throws IllegalAccessException, InstantiationException {
        MapBuilder<K, V> builder = new MapBuilder<> ();
        builder.map = clazz.newInstance();
        return builder;
    }

    public MapBuilder<K, V> put(K k, V v) {
        this.map.put(k, v);
        return this;
    }

    public Map<K, V> build() {
        return this.map;
    }

    private MapBuilder() {
    }

}
