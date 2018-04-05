package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);

        //напишите тут ваш код
        if (softReference != null){
            return softReference.get();
        } else {
            return null;
        }
    }

    public AnyObject put(Long key, AnyObject value) {

        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));

        //напишите тут ваш код
        AnyObject result;
        if (softReference != null) {
            result = softReference.get();
            softReference.clear();
        } else {
            result = null;
        }

        return result;
    }

    public AnyObject remove(Long key) {

        SoftReference<AnyObject> softReference = cacheMap.remove(key);

        AnyObject result;
        if (softReference != null){
            result = softReference.get();
            softReference.clear();
        }else {
            result = null;
        }

        return result;
    }
}