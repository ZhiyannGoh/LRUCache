package model;

import business.CacheOperation;

public abstract class LRUCache implements CacheOperation {

    private int cacheAvailable;
    private int cacheSize;

    public LRUCache() {
    }

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        this.cacheAvailable = cacheSize;
    }

    public int getCacheAvailable() {
        return cacheAvailable;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void incrementCacheAvailable() {
        this.cacheAvailable++;
    }

    public void decrementCacheAvailable() {
        this.cacheAvailable--;
    }

}
