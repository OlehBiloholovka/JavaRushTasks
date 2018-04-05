package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        return map.values().stream().mapToInt(List::size).sum();
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        V v = null;
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            v = list.get(list.size() - 1);
            if (list.size() < repeatCount){
                list.add(value);
//                Collections.addAll(list, value);
            } else if (map.get(key).size() == repeatCount) {
                list.remove(0);
                list.add(value);
//                Collections.addAll(list, value);
            }
        } else {
            List<V> arrayList = new ArrayList<>();
            arrayList.add(value);
            map.put(key, arrayList);
        }
        return v;
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        V v = null;

        if (map.containsKey(key)){
            List<V> list = map.get(key);

            if (list.size() > 1) {
//                v = list.get(0);
                return list.remove(0);
            } else {
                map.remove(key);
                v = list.get(0);
            }
        }
        return v;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
//        map.values().stream().mato
        ArrayList<V> arrayList = new ArrayList<>();
        map.values().forEach(arrayList::addAll);
        return arrayList;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        boolean isContain = false;
        for (List<V> list : map.values()) {
            if (list.contains(value)) {
                return true;
            }
        }
        return isContain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}