package model;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private int cacheAvailable;
    private int cacheSize;
    private Map<Integer, Node> cacheMap = new HashMap<>();
    private Node head;
    private Node tail;

    public LRUCache(int cacheSize) {
        this.cacheAvailable = cacheSize;
        this.cacheSize = cacheSize;
        this.head = new Node();
        this.tail = new Node();
    }

    public Map<Integer, Node> getCacheMap() {
        return cacheMap;
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

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

}
