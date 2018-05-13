package com.company.ArrayBasedMap;
import com.company.ArrayListIterator.ArrayList;

import java.util.*;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private List<Pair> values = new com.company.ArrayListIterator.ArrayList();

    @Override
    public int size() {

        return values.size();

    }

    @Override
    public boolean isEmpty() {

        return size() == 0;

    }

    @Override
    public boolean containsKey(Object key) {
        for (K thisKey: keySet()){
            if (key == null ? thisKey == null : key.equals(thisKey)) return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (V thisValue: values()){
            if (value == null ? thisValue == null : value.equals(thisValue)) return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {

        for(Entry<K, V> entry: entrySet())
            if (key != null ? key.equals(entry.getKey()) : entry.getKey() == null)
                return entry.getValue();
        return null;
    }
    @Override
    public V put(K key, V value) {

        if(!containsKey(key)) {
            values.add(new Pair(key, value));
            return null;

        }else {
            V oldValue = null;
            for (Pair pair1 : values){

                if (key == null ? pair1.getKey() == null : key.equals(pair1.getKey())){
                    oldValue = pair1.getValue();
                    pair1.setValue(value);
                    break;
                }
            }
            return oldValue;
        }

    }

    @Override
    public V remove(Object key) {
        for (Pair pair : values){
            if (key == null ? pair.getKey() == null : key.equals(pair.getKey())){
                V valueToRemove = pair.getValue();
                values.remove(pair);
                return valueToRemove;
            }
        }
        return null;

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Map.Entry<K, V>>)(Set)m.entrySet())
            put(e.getKey(), e.getValue());
    }

    @Override
    public void clear() {

        values.clear();

    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();
        for (Pair p : values) keys.add(p.getKey());
        return keys;
    }

    @Override
    public Collection<V> values() {

        Collection<V> collection = new ArrayList();
        if(!isEmpty()) {

            for (Pair p : values) {
                collection.add(p.getValue());
            }
        }
        return collection;

    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return (Set<Entry<K, V>>)(Set)new HashSet<>(values);
    }

    private class Pair implements Map.Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) return false;
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }
    }
}

