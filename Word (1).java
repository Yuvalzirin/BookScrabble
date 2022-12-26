package test;

import java.util.Arrays;
import java.util.Objects;

public class Word {


    Tile[] tileWord;
    int row;
    int col;
    boolean vertical;

    public Word(Tile[] tileWord, int row, int col, boolean vertical) {
        this.tileWord = tileWord;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }
    public int getRow() {return row;}
    public int getCol() {return col;}
    public Tile[] getTileWord() {return tileWord;}
    public boolean isVertical(){return vertical;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return row == word.row && col == word.col && vertical == word.vertical && Arrays.equals(tileWord, word.tileWord);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(row, col, vertical);
        result = 31 * result + Arrays.hashCode(tileWord);
        return result;
    }
}
