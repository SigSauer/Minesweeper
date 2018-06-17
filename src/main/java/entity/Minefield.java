package entity;

import java.util.Base64;
import java.util.Random;

public class Minefield {
    private boolean[][] field = new boolean[8][8];

    public Minefield() {
        fill();
    }

    public Minefield(boolean[][] field) {
        this.field = field;
    }

    private void fill() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = setCellStatus();
            }
        }
    }

    public boolean getCellStatus(int x, int y) {
        return field[x][y];
    }

    private boolean setCellStatus() {
        return new Random().nextInt(100) > 20;
    }

    public int getSize() {
        return field.length;
    }

//    private int[][] getIntValue() {
//        int[][] intField = new int[field.length][field.length];
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field.length; j++) {
//                if(field[i][j]) {
//                    intField[i][j] = 1;
//                }else intField[i][j] = 0;
//            }
//        }
//        return intField;
//    }

    public boolean[][] getField() {
        return field;
    }

    public String toString() {
        String strValue = "";
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                strValue += field[i][j]+"\t";
            }
            strValue += "\n";
        }
        return strValue;
    }
}
