
package NodeOperationUnitTest;


import business.NodeOperation;
import model.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class printNodeListTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private NodeOperation nodeOperation = null;
    private Node headRef = null;

    private void print(String output) {
        System.out.println(output);
    }

    @BeforeEach
    public void init() {
        nodeOperation = new NodeOperation();
        headRef = Node.builder().build();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void printNodeList_Not_Empty() {
        Node n1 = Node.builder().key(1).build();
        Node n2 = Node.builder().key(2).build();
        headRef.setNextNode(n1);
        n1.setNextNode(n2);

        print(nodeOperation.printNodeList(headRef.getNextNode()));
        assertEquals("1->2->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void printNodeList_All_Element_Must_Be_Printed() {
        Node n1 = Node.builder().key(1).build();
        Node n2 = Node.builder().key(2).build();
        headRef.setNextNode(n1);
        n1.setNextNode(n2);

        print(nodeOperation.printNodeList(headRef.getNextNode()));
        assertNotEquals("1->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void printNodeList_Should_NOT_Have_Addtitional_Nodes() {
        Node n1 = Node.builder().key(1).build();
        Node n2 = Node.builder().key(2).build();
        headRef.setNextNode(n1);
        n1.setNextNode(n2);

        print(nodeOperation.printNodeList(headRef.getNextNode()));
        assertNotEquals("1->2->3->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void printNodeList_Empty() {
        print(nodeOperation.printNodeList(null));
        assertEquals("Cache is Empty", outputStreamCaptor.toString().trim());
    }
}
