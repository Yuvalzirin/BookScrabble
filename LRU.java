package test;

import java.util.LinkedHashSet;

public class LRU implements CacheReplacementPolicy {

    private LinkedHashSet<String> cache;

    public LRU() {

        this.cache = new LinkedHashSet<>();
    }

    @Override
    public void add(String word) {

        if (this.cache.contains(word))
            cache.remove(word);
        cache.add(word);
    }

    @Override
    public String remove() {
        String[] cacheArray = cache.toArray(new String[cache.size()]);
        cache.remove(cacheArray[0]);
        return cacheArray[0];
    }

}
