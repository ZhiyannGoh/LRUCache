package model;

import business.NodeOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapCache extends LRUCache {

    private Map<Integer, Node> cacheMap = new HashMap<>();
    private Node head;
    private Node tail;
    private NodeOperation nodeOperation;

    public HashMapCache(int cacheSize) {
        super(cacheSize);
        this.head = new Node();
        this.tail = new Node();
        this.nodeOperation = new NodeOperation();
    }

    public Map<Integer, Node> getCacheMap() {
        return cacheMap;
    }

    public void setCacheMap(Map<Integer, Node> cacheMap) {
        this.cacheMap = cacheMap;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    @Override
    public void printCache() {
        String s = nodeOperation.printNodeList(getHead().getNext());
        if(!s.isEmpty())
            System.out.println("Current Cache: " + s);
    }

    @Override
    public void assignCache(List<Integer> node) {
        node.forEach(i -> {
            Node currNode = Node.builder().key(i).build();
            if (getCacheMap().containsKey(currNode.getKey())) {
                if (getTail().getPrev().getKey() != currNode.getKey()) {
                    Node midNode = getCacheMap().get(currNode.getKey());
                    nodeOperation.removeMidLink(midNode);
                    nodeOperation.setLinkNull(currNode);
                    nodeOperation.setNewTail(getTail(), currNode);
                }
            } else {
                if (getCacheAvailable() == getCacheSize()) {
                    insertCache(currNode);
                    nodeOperation.setNewHead(getHead(), currNode);
                } else if (getCacheAvailable() > 0 && getCacheSize() >= getCacheAvailable()) {
                    insertCache(currNode);
                } else {
                    freeCache();
                    insertCache(currNode);
                }
            }
        });
    }

    @Override
    public void freeCache() {
        Map<Integer, Node> currCacheItem = getCacheMap();
        Node prevHead = nodeOperation.setNewHead(getHead(), null);
        currCacheItem.remove(prevHead.getKey());
        incrementCacheAvailable();
    }

    @Override
    public void insertCache(Node newNode) {
        nodeOperation.setNewTail(getTail(), newNode);
        getCacheMap().put(newNode.getKey(), newNode);
        decrementCacheAvailable();
    }
}
