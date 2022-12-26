package test;
import java.util.ArrayList;
// green=0 common square
// lightblue=1 doubles letter score
//blue=2 triples letter score
//yellow=3 doubles the word's total score
//red=4 triples the word's total score
// star=5 doubles the word's total score
public class Board {
    private static Board b = null;
    Tile[][] allBorad = new Tile[15][15];
    int[][] score = new int[15][15];
    public Board() {
        // green=0 common square
        // lightblue=1 doubles letter score
        //blue=2 triples letter score
        //yellow=3 doubles the word's total score
        //red=4 triples the word's total score
        // star=5 doubles the word's total score
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++) {
                allBorad[i][j] = null;
                score[i][j] = 0;
            }
        score[7][7] = 5;

        //Setting Red BoardSquare
        score[0][0] = (4);
        score[0][7] = (4);
        score[0][14] = (4);
        score[7][0] = (4);
        score[7][14] = (4);
        score[14][0] = (4);
        score[14][7] = (4);
        score[14][14] = (4);

        //Setting Yellow BoardSquare
        score[1][1] = (3);
        score[2][2] = (3);
        score[3][3] = (3);
        score[4][4] = (3);
        score[1][13] = (3);
        score[2][12] = (3);
        score[3][11] = (3);
        score[4][10] = (3);
        score[13][13] = (3);
        score[12][12] = (3);
        score[11][11] = (3);
        score[10][10] = (3);
        score[10][4] = (3);
        score[11][3] = (3);
        score[12][2] = (3);
        score[13][1] = (3);

        //Setting Blue BoardSquare
        score[1][5] = (2);
        score[1][9] = (2);
        score[5][1] = (2);
        score[5][5] = (2);
        score[5][9] = (2);
        score[5][13] = (2);
        score[9][1] = (2);
        score[9][5] = (2);
        score[9][9] = (2);
        score[9][13] = (2);
        score[13][5] = (2);
        score[13][9] = (2);

        //Setting Cyan BoardSquare
        score[0][3] = (1);
        score[0][11] = (1);
        score[2][6] = (1);
        score[2][8] = (1);
        score[3][0] = (1);
        score[3][7] = (1);
        score[3][14] = (1);
        score[6][2] = (1);
        score[6][6] = (1);
        score[6][8] = (1);
        score[6][12] = (1);
        score[7][3] = (1);
        score[7][11] = (1);
        score[8][2] = (1);
        score[8][6] = (1);
        score[8][8] = (1);
        score[8][12] = (1);
        score[11][0] = (1);
        score[11][7] = (1);
        score[11][14] = (1);
        score[12][6] = (1);
        score[12][8] = (1);
        score[14][3] = (1);
        score[14][11] = (1);
    }

    public static Board getBoard() {
        if (b == null)
            b = new Board();
        return b;
    }

    public Tile[][] getTile() {
        Tile[][] copy = new Tile[15][15];
        for (int i = 0; i < allBorad.length; i++) {
            System.arraycopy(allBorad, 0, copy[i], 0, allBorad.length);
//          for (int j = 0; j < 15; j++) {copy[i][j]=allBorad[i][j];}
        }
        return copy;
    }
    public boolean dictionaryLegal() {return true;}
    public boolean isOverBoard(Word w) {
        if (w.getRow() < 0 || w.getRow() > 15)
            return false;
        if (w.getCol() < 0 || w.getCol() > 15)
            return false;
        else {

            if (w.isVertical())
            {
                return (w.tileWord.length + w.getRow()) <= allBorad.length;
            } else
            {
                return (w.tileWord.length + w.getCol()) <= allBorad.length;
            }
        }
    }
    public boolean isFirstWord(Word w) {
        if (this.allBorad[7][7] == null)
        {
            int row = w.getRow();
            int col = w.getCol();
            for (int i = 0; i < w.tileWord.length; i++) {
                if (col == 7 & row == 7)
                    return true;
                if (w.isVertical())
                    row++;
                else
                    col++;
            }
            return false;
        }
        return true;
    }
    public boolean isTileNotEmpty(Word w) {return w.tileWord != null;}
    public boolean OnWord(Word w) {
        int row = w.getRow();
        int col = w.getCol();
        if (isFirstWord(w))
            return true;
        for (int i = 0; i < w.tileWord.length; i++) {
            if (w.tileWord[i] == null && allBorad[row][col] != null)
                return true;
            else {
                if (w.isVertical()) {
                    if (col - 1 >= 0) {
                        if (allBorad[row][col - 1] != null)
                            return true;
                    }
                    if (col + 1 <= 14) {
                        if (allBorad[row][col + 1] != null)
                            return true;
                    }
                    row++;
                } else {
                    if (row - 1 >= 0) {
                        if (allBorad[row - 1][col] != null)
                            return true;
                    }

                    if (row + 1 <= 14) {
                        if (allBorad[row + 1][col] != null)
                            return true;
                    }
                    col++;
                }
            }
        }
        return false;
    }
    public boolean isReplaceWord(Word w) {
        if (!OnWord(w))
            return false;
        else {
            int row = w.getRow();
            int col = w.getCol();
            for (int i = 0; i < w.tileWord.length; i++) {
                if (allBorad[row][col] != null && w.tileWord[i] != null)
                    return false;//not legal
                if (w.isVertical())
                    row++;
                else
                    col++;
            }
        }
        return true;//legal
    }

    public Word MakeWord(int row, int col, int length, boolean vertical, Tile t) {
        int col1 = col;
        int row1 = row;
        Tile[] temp = new Tile[length];
        for (int i = 0; i < length; i++) {
            if (allBorad[row1][col1] == null) {
                temp[i] = t;
            } else {
                temp[i] = allBorad[row1][col1];
            }
            if (vertical)
                row1++;
            else
                col1++;
        }
        return new Word(temp, row, col, vertical);
    }
    public boolean boardLegal(Word w) {
        if (allBorad[7][7] == null)
        {
            return  isFirstWord(w)&&isOverBoard(w) ;
        }

        return ((isOverBoard(w)) && (isFirstWord(w)) && (isTileNotEmpty(w)) && (isReplaceWord(w)));

    }
    public ArrayList<Word> getWords(Word w) {
        ArrayList<Word> wordList = new ArrayList<>();
        int row = w.getRow();
        int col = w.getCol();
        int start = 0;
        int end = 0;
        wordList.add(w);//add the first word to the list
        if (allBorad[7][7] == null && isFirstWord(w))
            return wordList;
        for (int i = 0; i < w.tileWord.length; i++) {
            if (w.isVertical()) {
                if (w.tileWord[i] == null && allBorad[row][col] != null) {
                    row++;
                    continue;
                }
                if (col != 0) {
                    if (allBorad[row][col - 1] != null) {
                        col--;
                        while (allBorad[row][col] != null) {
                            start++;
                            col--;
                        }
                    }
                    col = w.getCol();
                }
                if (col != 14) {
                    if (allBorad[row][col + 1] != null) {
                        col++;
                        while (allBorad[row][col] != null) {
                            end++;
                            col++;
                        }
                    }
                    col = w.getCol();
                }
                if (start != 0 && end == 0)
                    wordList.add(MakeWord(row, w.getCol() - start, start + 1, false, w.tileWord[i]));
                if (start == 0 && end != 0)
                    wordList.add(MakeWord(row, w.getCol(), end + 1, false, w.tileWord[i]));
                if (start != 0 && end != 0)
                    wordList.add(MakeWord(row, w.getCol() - start, start + end + 1, false, w.tileWord[i]));
                row++;
                col = w.getCol();
            }
            else {//not vertical
                if (w.tileWord[i] == null && allBorad[row][col] != null) {
                    col++;
                    continue;
                }
                if (col != 0) {
                    if (allBorad[row - 1][col] != null) {
                        row--;
                        while (allBorad[row][col] != null) {
                            row--;
                            start++;
                        }
                    }
                    row = w.getRow();
                }
                if (row != 14) {
                    if (allBorad[row + 1][col] != null) {
                        row++;
                        while (allBorad[row][col] != null) {
                            row++;
                            end++;
                        }
                    }
                    row = w.getRow();
                }
                if (start == 0 && end != 0)
                    wordList.add(MakeWord(w.getRow(), col, end + 1, true, w.tileWord[i]));
                if (start != 0 && end == 0)
                    wordList.add(MakeWord(w.getRow() - start, col, start + 1, true, w.tileWord[i]));
                if (start != 0 && end != 0)
                    wordList.add(MakeWord(w.getRow() - start, col, start + end + 1, true, w.tileWord[i]));
                row = w.getRow();
                col++;
            }
            start = 0;
            end = 0;
        }
        return wordList;
    }
    public int getScore(Word w) {
        // green=0 common square
        // lightblue=1 doubles letter score
        //blue=2 triples letter score
        //yellow=3 doubles the word's total score
        //red=4 triples the word's total score
        // star=5 doubles the word's total score
        boolean doubleTheScore = false;
        boolean trippleTheScore = false;
        int col = w.getCol();
        int row = w.getRow();
        int tileScore = 0;
        int sum = 0;
        for (int i = 0; i < w.tileWord.length; i++) {
            if (w.tileWord[i] == null && allBorad[row][col] != null) {
                tileScore = allBorad[row][col].getScore();
            }
            else {
                 if(w.tileWord[i] != null)
                    tileScore = w.tileWord[i].getScore();
            }
            switch (score[row][col]){
                case 5:{
                    if(allBorad[row][col] == null) {
                    doubleTheScore = true;
                    }
                sum += tileScore;
                    break;}
                case 4:{
                    trippleTheScore = true;
                    sum += tileScore;
                    break;}
                case 3:{
                    doubleTheScore = true;
                    sum += tileScore;
                    break;}
                case 2:{
                    sum += (3 * tileScore);
                    break;}
                case 1:{
                    sum += (2 * tileScore);
                    break;}
                default:{
                    sum += tileScore;
                    break;}
            }
           if (w.isVertical())
                row++;
            else
                col++;
        }
        if (trippleTheScore)
            return sum*3;
        else if (doubleTheScore)
            return sum*2;
        else
            return sum;
    }
    public int tryPlaceWord (Word w){
            ArrayList<Word> tempList = getWords(w);
            int Wordscore = 0;
            int Row = w.getRow();
            int Col = w.getCol();
            if (!boardLegal(w))
                return 0;
            if (getWords(w).size() == 1) {
                Wordscore = getScore(tempList.get(0));
            }
            else {
                for (Word word : tempList) Wordscore += getScore(word);
            }
            for (int i = 0; i < w.tileWord.length; i++) {
                if (w.tileWord[i] != null)
                    allBorad[Row][Col] = w.tileWord[i];
                if (w.isVertical()) Row++;
                else Col++;
            }
            return Wordscore;
        }
    }
