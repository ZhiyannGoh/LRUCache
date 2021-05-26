package business;

import model.Node;

public class NodeOperation {

    public Node makeNewHead(Node oldHead) {
        if (oldHead != null) {
            Node newHead = oldHead.getNextNode();
            if (newHead != null) {
                newHead.setPrevNode(null);
                return newHead;
            } else
                return null;
        } else
            throw new NullPointerException();
    }

    public Node setNextAndPrevNull(Node n) {
        if (n != null) {
            n.setNextNode(null);
            n.setPrevNode(null);
            return n;
        } else
            throw new NullPointerException();
    }

    public void insertToTail(Node newTail, Node oldTail) {
        if (oldTail != null && newTail != null) {
            oldTail.setNextNode(newTail);
            newTail.setPrevNode(oldTail);
        }
    }

    public void makeNewMid(Node midNode) {
        if (midNode != null && (midNode.getPrevNode() != null && midNode.getNextNode() != null)) {
            Node prevNode = midNode.getPrevNode();
            Node nextNode = midNode.getNextNode();
            prevNode.setNextNode(nextNode);
            nextNode.setPrevNode(prevNode);
        }
        else
            throw new NullPointerException();
    }

    public void setNextNull(Node n) {
        n.setNextNode(null);
    }

    public String printNodeList(Node n) {
        StringBuilder sb = new StringBuilder();
        if (n != null) {
            Node looper = n;
            while (looper != null) {
                sb.append(looper.getKey() + "->");
                looper = looper.getNextNode();
            }
            sb.append("null");
        } else
            sb.append("Cache is Empty");
        return sb.toString();
    }
}
