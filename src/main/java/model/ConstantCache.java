package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@AllArgsConstructor
public class ConstantCache extends Cache {
    private Map<Integer, Node> cacheInfo = new HashMap<>();

    @Builder
    public ConstantCache(int cacheSize) {
        super(cacheSize);
    }

    @Override
    public String toString() {
        return "ConstantCache{" +
                "cacheInfo=" + cacheInfo +
                '}';
    }

    @Override
    public void assignCache(List<Integer> nodes) {
        nodes.forEach(i -> {
            boolean isHead = false;
            boolean isMiddle = false;
            boolean isTail = false;

            Node newNode = Node.builder().key(i).build();
            if (getCacheInfo().containsKey(newNode.getKey())) {
                Node foundNode = getCacheInfo().get(newNode.getKey());
                if (foundNode.getPrevNode() == null) {
                    isHead = true;
                } else if (foundNode.getPrevNode() != null && foundNode.getNextNode() != null) {
                    isMiddle = true;
                } else if (foundNode.getNextNode() == null) {
                    isTail = true;
                }
                newNode = foundNode;
            } else if (getAvailableSize() <= 0) {
                freeCache();
            }

            if (!isTail)
                insertNodeIntoCache(newNode, isHead, isMiddle);

            if (getHeadRef().getNextNode() == null) {
                initializeCache(newNode);
            }
        });
    }

    @Override
    public void freeCache() {
        //System.out.println("isFreed");
        Node oldHead = getHeadRef().getNextNode();
        Node newHead = getNodeOperation().makeNewHead(oldHead);
        getHeadRef().setNextNode(newHead);
        getNodeOperation().setNextAndPrevNull(oldHead);
        getCacheInfo().remove(oldHead.getKey());
        incrementAvailableCache();
    }

    @Override
    public String printCacheInformation() {
        //System.out.println("Print Cache [" + getCacheInfo().toString() + "]");
        return getNodeOperation().printNodeList(getHeadRef().getNextNode());
    }

    private void initializeCache(Node newNode) {
        getHeadRef().setNextNode(newNode);
        getCacheInfo().put(newNode.getKey(), newNode);
    }

    private void insertNodeIntoCache(Node node, boolean isHead, boolean isMiddle) {
        Node n = node;
        if (isHead) {
            //System.out.println("isHead");
            Node newHead = getNodeOperation().makeNewHead(getHeadRef().getNextNode());
            getHeadRef().setNextNode(newHead);
            n = getNodeOperation().setNextAndPrevNull(getCacheInfo().get(node.getKey()));
        } else if (isMiddle) {
            //System.out.println("isMiddle");
            getNodeOperation().makeNewMid(node);
            n = getNodeOperation().setNextAndPrevNull(getCacheInfo().get(node.getKey()));
        }
        //System.out.println("isTail");
        getNodeOperation().insertToTail(n, getTailRef().getPrevNode());
        getTailRef().setPrevNode(n);
        getNodeOperation().setNextNull(n);

        if (!isHead && !isMiddle)
            decrementAvailableCache();

        if (!getCacheInfo().containsKey(n.getKey()))
            getCacheInfo().put(n.getKey(), n);
    }
}
