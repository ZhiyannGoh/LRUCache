package impl;

import business.NodeOperation;
import model.HashMapCache;
import model.Node;

import java.util.List;
import java.util.Map;

public class HashMapImpl{

    private NodeOperation nodeOperation = new NodeOperation();

    public void printCache(Object o) {
        HashMapCache cache = (HashMapCache) o;
        nodeOperation.printNodeList(cache.getHead().getNext());
    }

    public void assignCache(Object o1, Object o2) {
        HashMapCache cache = (HashMapCache) o1;
        List<Integer> node = (List<Integer>) o2;

        node.forEach(i -> {
            Node currNode = Node.builder().key(i).build();
            if (cache.getCacheMap().containsKey(currNode.getKey())) {
                if (cache.getHead().getNext().getKey() == currNode.getKey()) {
                    Node prevHead = nodeOperation.setNewHead(cache.getHead(),currNode);
                    nodeOperation.setLinkNull(prevHead);
                    nodeOperation.setNewTail(cache.getTail(), prevHead);
                } else if (cache.getTail().getPrev().getKey() != currNode.getKey()) {
                    Node midNode = cache.getCacheMap().get(currNode.getKey());
                    nodeOperation.removeMidLink(midNode);
                    nodeOperation.setLinkNull(currNode);
                    nodeOperation.setNewTail(cache.getTail(), currNode);
                }
            } else {
                if (cache.getCacheAvailable() > 0 && cache.getCacheSize() >= cache.getCacheAvailable()) {
                    insertCache(cache, currNode);
                } else {
                    freeCache(cache);
                    insertCache(cache, currNode);
                }
            }
        });
    }

    public void freeCache(Object o) {
        HashMapCache cache = (HashMapCache) o;
        Map<Integer,Node> currCacheItem = cache.getCacheMap();
        Node prevHead = nodeOperation.setNewHead(cache.getHead(),new Node());
        currCacheItem.remove(prevHead.getKey());
        cache.incrementCacheAvailable();
    }

    public void insertCache(Object o1, Object o2) {
        HashMapCache cache = (HashMapCache) o1;
        Map<Integer,Node> currCacheItem = cache.getCacheMap();
        Node newNode = (Node) o2;
        if (cache.getHead().getNext() == null) {
            cache.getHead().setNext(newNode);
        }
        nodeOperation.setNewTail(cache.getTail(), newNode);
        currCacheItem.put(newNode.getKey(), newNode);
        cache.decrementCacheAvailable();
    }
}
