package entity;

import java.util.Random;

public class Minefield {
    private boolean[][] field = new boolean[8][8];

    public Minefield() {
    }

    public Minefield(boolean[][] field) {
        this.field = field;
    }

    public void fill() {
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
        return new Random().nextInt(100) > 10;
    }

    public int getSize() {
        return field.length;
    }

}
