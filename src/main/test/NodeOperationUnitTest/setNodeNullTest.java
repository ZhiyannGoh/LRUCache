package NodeOperationUnitTest;

import business.NodeOperation;
import model.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class setNodeNullTest {

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
    public void set_Node_Link_NULL() {
        nodeOperation.setNextAndPrevNull(midNode);
        assertNull(midNode.getPrevNode());
        assertNull(midNode.getNextNode());
    }
}