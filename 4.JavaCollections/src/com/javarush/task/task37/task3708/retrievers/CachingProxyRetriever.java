package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
//    Storage storage;
    private LRUCache lruCache = new LRUCache(20);
    private OriginalRetriever originalRetriever;

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {

        Object result = lruCache.find(id);

        if (result == null){
            result = originalRetriever.retrieve(id );
            lruCache.set(id, result);
        }
        return result;
//        if (!lruCache.containsKey(id)){
//            lruCache.set(id, originalRetriever.retrieve(id));
//        }
//        return lruCache.find(id);
    }
}
