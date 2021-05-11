import factory.CacheFactory;
import model.LRUCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExecuteMain {

    public static void main(String[] args) {

        CacheFactory cacheFactory = new CacheFactory();

        /*
        2 3 4
        2 4 3
        2 4 3 5
        4 3 5 6
        3 5 6 7
         */

        LRUCache cache = cacheFactory.getCache("HashMapImp",4);
        List<Integer> list = new ArrayList<>(Arrays.asList(2,3,4,3,5,6,7));
        cache.assignCache(list);
        cache.printCache();

        /*
        2 4
        2 4 3
        2 4 3 7
        4 3 7 6
        3 7 6 8
         */

        LRUCache cache2 = cacheFactory.getCache("HashMapImp",4);
        List<Integer> list2 = new ArrayList<>(Arrays.asList(2,4,4,3,7,6,8));
        cache2.assignCache(list2);
        cache2.printCache();

        LRUCache cache3 = cacheFactory.getCache("HashMapImp",4);
        List<Integer> list3 = new ArrayList<>(Arrays.asList(2,2,1));
        cache3.assignCache(list3);
        cache3.printCache();

        LRUCache cache4 = cacheFactory.getCache("HashMapImp",4);
        List<Integer> list4 = new ArrayList<>(Arrays.asList(2,1,1));
        cache4.assignCache(list4);
        cache4.printCache();

    }
}
