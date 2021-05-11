package NodeOperationUnitTest;

import business.NodeOperation;
import model.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class setLinkNullAndRemoveMidLinkTest {

    private NodeOperation nodeOperation = null;
    private Node firstNode = null;
    private Node midNode = null;
    private Node secondNode = null;

    @BeforeEach
    public void init() {
        nodeOperation = new NodeOperation();

        /** Setting up linkage*/
        firstNode = Node.builder().key(1).build();
        midNode = Node.builder().key(2).build();
        secondNode = Node.builder().key(3).build();

        firstNode.setNext(midNode);
        midNode.setPrev(firstNode);
        midNode.setNext(secondNode);
        secondNode.setPrev(midNode);

        assertEquals(firstNode, midNode.getPrev());
        assertEquals(secondNode, midNode.getNext());
    }

    @Test
    public void set_Node_Link_NULL() {
        nodeOperation.setLinkNull(midNode);
        assertNull(midNode.getPrev());
        assertNull(midNode.getNext());
    }

    @Test
    public void remove_midNode_Link() {
        nodeOperation.removeMidLink(midNode);
        assertEquals(firstNode, secondNode.getPrev());
        assertEquals(secondNode, firstNode.getNext());
    }

    @Test
    public void remove_firstNode_Link() {
        assertEquals(firstNode, midNode.getPrev());

        assertThrows(NullPointerException.class, () -> nodeOperation.removeMidLink(firstNode));
        assertEquals(secondNode, midNode.getNext());
        assertEquals(midNode ,secondNode.getPrev());
    }

    @Test
    public void remove_lastNode_Link() {
        assertEquals(secondNode, midNode.getNext());

        assertThrows(NullPointerException.class, () -> nodeOperation.removeMidLink(secondNode));
        assertEquals(firstNode, midNode.getPrev());
        assertEquals(midNode,firstNode.getNext());
    }

}
