package business;

import model.LRUCache;
import model.Node;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CacheOperation {

    private NodeOperation nodeOperation = new NodeOperation();

    public void printCache(LRUCache cache) {
        nodeOperation.printNodeList(cache.getHead().getNext());
    }

    public void assignCache(LRUCache cache, List<Integer> node) {

        node.forEach(i -> {
            Node currNode = new Node(i);
            if (cache.getCacheMap().containsKey(currNode.getKey())) {
                if (cache.getHead().getNext().getKey() == currNode.getKey()) {
                    Node prevHead = nodeOperation.setNewHead(cache.getHead());
                    nodeOperation.setLinkNull(prevHead);
                    nodeOperation.setNewTail(cache.getTail(), prevHead);
                } else if (cache.getTail().getKey() != currNode.getKey()) {
                    Node midNode = cache.getCacheMap().get(currNode.getKey());
                    nodeOperation.removeMidLink(midNode);
                    nodeOperation.setLinkNull(currNode);
                    nodeOperation.setNewTail(cache.getTail(), currNode);
                }
            } else {
                if (cache.getCacheAvailable() > 0 && cache.getCacheSize() >= cache.getCacheAvailable()) {
                    insertCache(cache, cache.getCacheMap(), currNode);
                    printCache(cache);
                } else {
                    freeCache(cache, cache.getCacheMap());
                    insertCache(cache, cache.getCacheMap(), currNode);
                }
            }
        });
    }

    private void freeCache(LRUCache cache, Map<Integer, Node> currCacheItem) {
        Node prevHead = nodeOperation.setNewHead(cache.getHead());
        currCacheItem.remove(prevHead.getKey());
        cache.incrementCacheAvailable();
    }

    private void insertCache(LRUCache cache, Map<Integer, Node> currCacheItem, Node newNode) {
        if (cache.getHead().getNext() == null) {
            cache.getHead().setNext(newNode);
        }
        nodeOperation.setNewTail(cache.getTail(), newNode);
        currCacheItem.put(newNode.getKey(), newNode);
        cache.decrementCacheAvailable();
    }

}
