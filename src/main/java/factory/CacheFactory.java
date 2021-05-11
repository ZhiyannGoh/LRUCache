package factory;

import model.HashMapCache;
import model.LRUCache;

public class CacheFactory {

    public LRUCache getCache(String cacheImp, int cacheSize) {
        if (cacheImp == null)
            throw new IllegalArgumentException("Cache should not be empty");

        if (cacheImp.equalsIgnoreCase("HashMapImp")) {
            return new HashMapCache(cacheSize);
        } else if (cacheImp.equalsIgnoreCase("LinkedListImp")) {
            return new HashMapCache(cacheSize);
        } else
            throw new IllegalArgumentException("Cache selection is not implemented");
    }
}
