package test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LFU implements CacheReplacementPolicy {
    private LinkedHashMap<String, Integer> cache;

    String minStr;

    public LFU() {
        this.cache = new LinkedHashMap<>();
        this.minStr = null;
    }

    @Override
    public void add(String word) {
        int key = 1;
        if (cache.isEmpty())
            minStr = word;
        if (!(cache.containsKey(word))) {
            cache.put(word, key);
        } else
            cache.put(word, ++key);
    }

    @Override
    public String remove() {

        int minValue = cache.get(minStr);
        for (Map.Entry<String, Integer> ent : cache.entrySet()) {
            if (minValue > ent.getValue()) {
                minValue = ent.getValue();
                minStr = ent.getKey();
            }
        }
        return minStr;
    }

}
