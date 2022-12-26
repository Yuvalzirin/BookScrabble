package test;

import java.util.HashSet;

public class CacheManager {

    int size;
    CacheReplacementPolicy crp;
    HashSet<String> arr;

    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.size = size;
        this.crp = crp;
        this.arr = new HashSet<>();
    }

    boolean query(String word) {
        if (arr.contains(word))
            return true;
        return false;
    }

    public void add(String word) {
        crp.add(word);
        arr.add(word);
        if (arr.size() > this.size) {
            arr.remove(crp.remove());
        }
    }
}
