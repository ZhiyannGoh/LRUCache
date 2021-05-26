package NodeOperationUnitTest;

import factory.CacheFactory;
import model.Cache;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class cacheBaseLineTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private CacheFactory cacheFactory;
    private Cache c;

    private void print(String output) {
        System.out.println(output);
    }

    @BeforeEach
    public void init() {
        cacheFactory = new CacheFactory();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach()
    public void tearDown() {
        cacheFactory = null;
        c = null;
        System.setOut(standardOut);
    }

    @Test
    public void input_12345_Size_4() {
        c = cacheFactory.getCacheInstance("CONSTANT", 4);
        c.assignCache(Arrays.asList(1, 2, 3, 4, 5));

        print(c.printCacheInformation());
        assertEquals("2->3->4->5->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void input_123456_Size_4() {
        c = cacheFactory.getCacheInstance("CONSTANT", 4);
        c.assignCache(Arrays.asList(1, 2, 3, 4, 5, 6));

        print(c.printCacheInformation());
        assertEquals("3->4->5->6->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void input_12_Size_1() {
        c = cacheFactory.getCacheInstance("CONSTANT", 1);
        c.assignCache(Arrays.asList(1, 2));

        print(c.printCacheInformation());
        assertEquals("2->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void input_1234567_Size_2() {
        c = cacheFactory.getCacheInstance("CONSTANT", 2);
        c.assignCache(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

        print(c.printCacheInformation());
        assertEquals("6->7->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void input_1234567_Size_8() {
        c = cacheFactory.getCacheInstance("CONSTANT", 8);
        c.assignCache(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

        print(c.printCacheInformation());
        assertEquals("1->2->3->4->5->6->7->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void input_122_Size_2() {
        c = cacheFactory.getCacheInstance("CONSTANT", 2);
        c.assignCache(Arrays.asList(1, 2, 2));

        print(c.printCacheInformation());
        assertEquals("1->2->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void input_121_Size_2() {
        c = cacheFactory.getCacheInstance("CONSTANT", 2);
        c.assignCache(Arrays.asList(1, 2, 1));

        print(c.printCacheInformation());
        assertEquals("2->1->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void input_12234456_Size_4() {
        c = cacheFactory.getCacheInstance("CONSTANT", 4);
        c.assignCache(Arrays.asList(1, 2, 2, 3, 4, 4, 5, 6));

        print(c.printCacheInformation());
        assertEquals("3->4->5->6->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void input_112_Size_2() {
        c = cacheFactory.getCacheInstance("CONSTANT", 2);
        c.assignCache(Arrays.asList(1, 1, 2));

        print(c.printCacheInformation());
        assertEquals("1->2->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void input_112_Size_1() {
        c = cacheFactory.getCacheInstance("CONSTANT", 1);
        c.assignCache(Arrays.asList(1, 1, 2));

        print(c.printCacheInformation());
        assertEquals("2->null", outputStreamCaptor.toString().trim());
    }

    @Test
    public void input_1_Size_1000() {
        c = cacheFactory.getCacheInstance("CONSTANT", 1000);
        c.assignCache(Arrays.asList(1));

        print(c.printCacheInformation());
    }
}
