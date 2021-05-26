package business;

import model.Node;

import java.util.List;

public interface CacheOperation {

    void assignCache(List<Integer> nodes);

    void freeCache();

    String printCacheInformation();

}
