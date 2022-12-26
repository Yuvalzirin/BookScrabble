package test;

import java.util.Objects;

public class Tile {
    public final char letter;
    public final int score;
    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public char getLetter() {
        return letter;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return (letter == tile.getLetter() && score == tile.getScore());
    }
    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    public static class Bag {

        private static Bag b=null;
        char[] arrL = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        final int[] totalamount = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1}; //כמות מכל אות קבוע אפשרי
        int[] arrSumEachLetter = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1}; //כמות מכל אות
        int[] arrValue = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10}; //ערך האות
        Tile[] t;
        int sumLatters=98;
        private Bag() {
            t = new Tile[26];
            for (int i = 0; i < 26; i++)
            {
                t[i] = new Tile(arrL[i], arrValue[i]);
            }
        }
        Tile getRand() {
            int rand = (int)(Math.random()*26);
            while (arrSumEachLetter[rand] == 0) {
                rand = (int)(Math.random()*26);
            }
            arrSumEachLetter[rand]--;
            sumLatters--;
            return t[rand];
        }
        public Tile getTile(char a) {
            for (int i=0;i<26;i++) {
                if (t[i].getLetter() == a) {
                    if (arrSumEachLetter[a-'A']!=0)
                        return t[i];
                }
            }
            return null;
        }
        public void put(Tile c)
        {
            int indexTile=c.getLetter() - 'A';

            if(arrSumEachLetter[indexTile]+1<=totalamount[indexTile]) {
                arrSumEachLetter[indexTile]++;
                sumLatters++;
            }
        }
        public int getSize(){return sumLatters;}
        public int[] getQuantities()
        {
            int[]copy=new int[26];
            System.arraycopy(arrSumEachLetter,0,copy,0, arrSumEachLetter.length);
            return copy;
        }
        public static Bag getBag(){
            if (b==null)
            {
                b=new Bag();
            }
            return b;
        }
    }
}





