package NodeOperationUnitTest;

import business.NodeOperation;
import model.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class removeMidLinkTest {

    private NodeOperation nodeOperation = null;
    private Node firstNode = null;
    private Node midNode = null;
    private Node secondNode = null;

    @BeforeEach
    public void init() {
        nodeOperation = new NodeOperation();

        //Setting up linkage
        firstNode = Node.builder().key(1).build();
        midNode = Node.builder().key(2).build();
        secondNode = Node.builder().key(3).build();

        firstNode.setNextNode(midNode);
        midNode.setPrevNode(firstNode);
        midNode.setNextNode(secondNode);
        secondNode.setPrevNode(midNode);

        assertEquals(firstNode, midNode.getPrevNode());
        assertEquals(secondNode, midNode.getNextNode());
    }

    @Test
    public void remove_MidNode_Link() {
        nodeOperation.makeNewMid(midNode);
        assertEquals(firstNode, secondNode.getPrevNode());
        assertEquals(secondNode, firstNode.getNextNode());
    }

    @Test
    public void remove_FirstNode_Link() {
        assertEquals(firstNode, midNode.getPrevNode());

        assertThrows(NullPointerException.class, () -> nodeOperation.makeNewMid(firstNode));
        assertEquals(secondNode, midNode.getNextNode());
        assertEquals(midNode, secondNode.getPrevNode());
    }

    @Test
    public void remove_LastNode_Link() {
        assertEquals(secondNode, midNode.getNextNode());

        assertThrows(NullPointerException.class, () -> nodeOperation.makeNewMid(secondNode));
        assertEquals(firstNode, midNode.getPrevNode());
        assertEquals(midNode, firstNode.getNextNode());
    }
}
