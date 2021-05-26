package factory;

import model.Cache;
import model.ConstantCache;
import model.LinearCache;

public class CacheFactory {

    public Cache getCacheInstance(String cachImp, int cacheSize) {
        switch (cachImp) {
            case "CONSTANT":
                return ConstantCache.builder().cacheSize(cacheSize).build();
            case "LINEAR":
                return new LinearCache();
            default:
                throw new IllegalArgumentException();
        }
    }

}
