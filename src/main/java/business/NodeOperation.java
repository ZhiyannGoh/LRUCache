package business;

import model.Node;

public class NodeOperation {

    public String printNodeList(Node n) {
        StringBuilder sb = new StringBuilder();
        if (n != null) {
            while (n != null) {
                sb.append(n.getKey() + " -> ");
                n = n.getNext();
            }
            sb.append("null");
        }
        return sb.toString();
    }

    public void setNewTail(Node tailRef, Node currNode) {
        Node tailNode;
        if (tailRef.getPrev() != null) {
            tailNode = tailRef.getPrev();
            tailNode.setNext(currNode);
            currNode.setPrev(tailNode);
            tailRef.setPrev(currNode);
        } else
            tailRef.setPrev(currNode);
    }

    public Node setNewHead(Node headRef, Node currNode) {
        Node oldHeadNode = null;
        if (headRef.getNext() != null) {
            oldHeadNode = headRef.getNext();
            headRef.setNext(oldHeadNode.getNext());
        } else if (currNode != null) {
            headRef.setNext(currNode);
        } else {
            throw new NullPointerException();
        }
        return oldHeadNode;
    }

    public void setLinkNull(Node prevHead) {
        prevHead.setNext(null);
        prevHead.setPrev(null);
    }

    public void removeMidLink(Node midNode) {
        Node prevNode = midNode.getPrev();
        Node nextNode = midNode.getNext();
        if (prevNode != null && nextNode != null) {
            nextNode.setPrev(prevNode);
            prevNode.setNext(nextNode);
        } else
            throw new NullPointerException();
    }
}
