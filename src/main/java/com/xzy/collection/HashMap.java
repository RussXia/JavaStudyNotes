package com.xzy.collection;

import lombok.Getter;
import lombok.Setter;

public class HashMap<K,V> implements Map<K,V> {

    private static final int DEFAULT_LENGTH = 16;

    private static final float DEFAULT_LOADER = 0.75f;

    private transient Entry<K,V> table = null;

    public HashMap(Entry<K, V> table) {
        this.table = table;
    }

    @Override
    public V put(K k, V v) {
        return null;
    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Getter
    @Setter
    class Entry<K,V> implements Map.Entry<K,V>{
        K k;
        V v;
        Entry<K,V> next;

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
