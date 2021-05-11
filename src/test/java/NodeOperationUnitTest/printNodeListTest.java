package NodeOperationUnitTest;

import business.NodeOperation;
import model.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class printNodeListTest {

    private NodeOperation nodeOperation = null;
    private Node headRef = null;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

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
    public void printNodeList(){
        Node n1 = Node.builder().key(1).build();
        Node n2 = Node.builder().key(2).build();
        headRef.setNext(n1);
        n1.setNext(n2);

        print(nodeOperation.printNodeList(headRef.getNext()));
        assertEquals("1 -> 2 -> null", outputStreamCaptor.toString().trim());
    }

}
