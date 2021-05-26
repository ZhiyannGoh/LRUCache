package model;

import business.CacheOperation;
import business.NodeOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public abstract class Cache implements CacheOperation {

    private final Node headRef = new Node();
    private final Node tailRef = new Node();
    private final NodeOperation nodeOperation = new NodeOperation();
    private int cacheSize = 0;
    private int availableSize = 0;

    public Cache(int cacheSize) {
        this.cacheSize = cacheSize;
        this.availableSize = cacheSize;
    }

    public void incrementAvailableCache() {
        this.availableSize++;
    }

    public void decrementAvailableCache() {
        this.availableSize--;
    }
}
