import business.CacheOperation;
import model.LRUCache;
import model.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExecuteMain {

    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(4);
        CacheOperation cacheOperation = new CacheOperation();
        List<Integer> list = new ArrayList<>(Arrays.asList(2,3,4,3,5,6,7));
        cacheOperation.assignCache(lruCache,list);

        /**
         * 234
         * 243
         * 2435
         * 4356
         * 3567
         */
        cacheOperation.printCache(lruCache);

    }
}
