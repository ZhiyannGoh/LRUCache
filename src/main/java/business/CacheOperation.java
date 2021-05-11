package business;

import model.Node;

import java.util.List;

public interface CacheOperation {
    void printCache();

    void assignCache(List<Integer> elements);

    void freeCache();

    void insertCache(Node newnode);
}
