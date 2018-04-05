package com.javarush.task.task37.task3707;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {

    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }



    public AmigoSet(Collection<? extends E> collection) {
//        Вычисли свою Capacity по такой формуле: максимальное из 16
// и округленного в большую сторону значения (collection.size()/.75f)

        int capacity = (int) Math.max (16, Math.ceil(collection.size()/.75f));
        map = new HashMap<>(capacity);
        collection.forEach(o -> map.put(o, PRESENT));
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(E e) {
        if (!map.containsKey(e)) {
            map.put(e, PRESENT);
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        if (map.containsKey(o)){
            map.remove(o);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AmigoSet<E> clone() {
        try {
            AmigoSet copy = (AmigoSet)super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();

        oos.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        oos.writeFloat(HashMapReflectionHelper.callHiddenMethod(map,"loadFactor"));
        oos.writeInt(map.size());

        for (E e : map.keySet()) oos.writeObject(e);



    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

        int capacity = ois.readInt();
        float loadFactor = ois.readFloat();
        int size = ois.readInt();

        map = new HashMap<>(capacity,loadFactor);

        for (int i = 0; i < size; i++)
        {
            E e = (E) ois.readObject();
            map.put(e,PRESENT);
        }

    }



//    private void writeObject(ObjectOutputStream outputStream) throws IOException {
//        int buckets = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
//        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
//        outputStream.defaultWriteObject();
//        outputStream.writeInt(buckets);
//        outputStream.writeFloat(loadFactor);
//        outputStream.writeInt(size());
//        for (E e : map.keySet()) outputStream.writeObject(e);
//    }
//
//    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
//        inputStream.defaultReadObject();
//
//        int bucket = inputStream.readInt();
//        float loadFactor = inputStream.readFloat();
//        HashMap<E, Object> map = new HashMap<>(bucket, loadFactor);
//        int size = inputStream.readInt();
//
//
//        for (int i = 0; i < size; i++)
//        {
//            E e = (E) inputStream.readObject();
//            map.put(e,PRESENT);
//        }
//    }
}
