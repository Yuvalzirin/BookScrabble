package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    CacheManager cacheMan1;
    CacheManager cacheMan2;
    BloomFilter bf;
    String[] filesNameArr;

    public Dictionary(String... fileNames)
    {
        cacheMan1 = new CacheManager(400, new LRU());
        cacheMan2 = new CacheManager(100, new LFU());
        bf = new BloomFilter(256, "SHA1", "MD5");
        filesNameArr = fileNames.clone();

        for (String str : fileNames) {
            try {
                BufferedReader bf1 = new BufferedReader(new FileReader(str));
                String line;
                while ((line = bf1.readLine()) != null) {
                    String[] oneWord = line.split(" ");
                    for (String string : oneWord) {
                        bf.add(string);
                    }
                }
                bf1.close();

            } catch (IOException e) {
            }

        }

    }

    public boolean query(String word) {
        if (cacheMan1.query(word))
            return true;
        else if (cacheMan2.query(word))
            return false;
        else {
            if (bf.contains(word)) {
                cacheMan1.add(word);
                return true;
            }
            cacheMan2.add(word);
            return false;
        }
    }

    public boolean challenge(String word) {
        try {
            if (IOSearcher.search(word, filesNameArr)) {
                cacheMan1.add(word);
                return true;
            } else {
                cacheMan2.add(word);
                return false;
            }

        } catch (IOException e) {
            return false;
        }
    }
}
