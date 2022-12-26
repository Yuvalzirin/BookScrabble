package test;

import java.util.BitSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class BloomFilter {

    BitSet newBitSet;
    String[] args;
    int bitSize;

    public BloomFilter(int size, String... arg) {
        this.newBitSet = new BitSet(size);
        this.args = arg;
        this.bitSize = size;
    }

    public void add(String word) {
        for (String string : args) {
            try {
                MessageDigest md = MessageDigest.getInstance(string);
                byte[] bt = md.digest(word.getBytes());
                BigInteger bi = new BigInteger(bt);
                int test = bi.intValue();
                test = Math.abs(test) % bitSize;
                newBitSet.set(test);

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public boolean contains(String word) {
        for (String string : args) {
            try {
                MessageDigest md = MessageDigest.getInstance(string);
                byte[] bt = md.digest(word.getBytes());
                BigInteger bi = new BigInteger(bt);
                int test = bi.intValue();
                test = Math.abs(test) % bitSize;
                if (newBitSet.get(test))
                    return true;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder newSB = new StringBuilder();
        for (int i = 0; i < newBitSet.length(); i++) {
            newSB.append(newBitSet.get(i) ? "1" : "0");
        }
        return newSB.toString();

    }

}