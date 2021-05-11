package NodeOperationUnitTest;

import business.NodeOperation;
import model.Node;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class setNewHeadTest {

    private NodeOperation nodeOperation = null;
    private Node headRef = null;

    @BeforeEach
    public void init() {
        nodeOperation = new NodeOperation();
        headRef = Node.builder().build();
    }

    @Test
    public void set_Head_When_Head_ISNULL_And_CurrNode_ISNOTNULL() {
        Node startNode = Node.builder().key(2).build();
        nodeOperation.setNewHead(headRef, startNode);
        assertEquals(startNode, headRef.getNext());
        assertEquals(2, headRef.getNext().getKey());
    }

    @Test
    public void set_Head_When_Head_ISNOTNULL_And_CurrNode_ISNULL() {
        Node startNode = Node.builder().key(2).build();
        headRef.setNext(startNode);
        Node startNextNode = Node.builder().key(3).build();
        startNode.setNext(startNextNode);
        startNextNode.setPrev(startNode);
        Node prevNode = nodeOperation.setNewHead(headRef, null);

        assertEquals(startNode, prevNode);
        assertEquals(2, prevNode.getKey());
        assertEquals(startNextNode, headRef.getNext());
        assertEquals(3, headRef.getNext().getKey());
    }

    @Test
    public void set_Head_When_Head_CurrNode_ISNULL_CurrNode_ISNULL() {
        assertThrows(NullPointerException.class, () ->
                nodeOperation.setNewHead(null, null));
    }

    @Test
    public void set_Head_When_Head_CurrNode_ISNOTNULL_CurrNode_ISNOTNULL() {
        Node startNode = Node.builder().key(2).build();
        headRef.setNext(startNode);
        Node startNextNode = Node.builder().key(3).build();
        startNode.setNext(startNextNode);
        startNextNode.setPrev(startNode);
        Node randomNode = Node.builder().key(4).build();
        Node prevNode = nodeOperation.setNewHead(headRef, randomNode);

        assertEquals(startNode, prevNode);
        assertEquals(2, prevNode.getKey());
        assertEquals(startNextNode, headRef.getNext());
        assertEquals(3, headRef.getNext().getKey());
    }

}
