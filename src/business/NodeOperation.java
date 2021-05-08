package business;

import model.Node;

public class NodeOperation {

    public void printNodeList(Node n) {
        if (n != null) {
            while (n != null) {
                System.out.print(n.getKey() + "->");
                n = n.getNext();
            }
            System.out.print("null");
            System.out.println();
        }
    }

    public void setNewTail(Node tailRef, Node n) {
        Node tailNode;
        if (tailRef.getPrev() != null) {
            tailNode = tailRef.getPrev();
            tailNode.setNext(n);
            n.setPrev(tailNode);
            tailRef.setPrev(n);
        } else {
            tailRef.setPrev(n);
        }
    }

    public Node setNewHead(Node headRef) {
        Node headNode = null;
        if (headRef.getNext() != null) {
            headNode = headRef.getNext();
            headRef.setNext(headNode.getNext());
            headNode.setPrev(null);
        }
        return headNode;
    }

    public void setLinkNull(Node prevHead) {
        prevHead.setNext(null);
        prevHead.setPrev(null);
    }

    public void removeMidLink(Node midNode) {
        Node prevNode = midNode.getPrev();
        Node nextNode = midNode.getNext();
        nextNode.setPrev(prevNode);
        prevNode.setNext(nextNode);
    }
}
