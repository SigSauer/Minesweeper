package gui.general;

import entity.Minefield;
import org.junit.Test;

public class GeneralControllerTest {

    @Test
    public void calculateTesting() {
        int x = 5;
        int y = 5;
        copyOfCalculate(x,y);
    }

    private void copyOfCalculate(int X, int Y) {
        Minefield minefield = new Minefield();
        System.out.println(minefield.toIntString());
        int count = 0;
        int posX = X;
        int posY = Y;
        System.out.println("posX: "+posX+" posY: "+posY);

        int maxX = posX + 1;
        int maxY = posY + 1;
        System.out.println("maxX: "+maxX+" maxY: "+maxY);

        int initX = posX - 1;
        int initY = posY - 1;
        System.out.println("initX: "+initX+" initY: "+initY);

        if (posX == 0) {
            initX++;
            System.out.println("(c) initX: "+initX);
        }
        if (posX == 7) {
            maxX--;
            System.out.println("(c) initX: "+maxX);
        }
        if (posY == 0) {
            initY++;
            System.out.println("(c) initY: "+initY);
        }
        if (posY == 7) {
            maxY--;
            System.out.println("(c) maxY: "+maxY);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(i+" "+j);
            }
        }
        for (int i = initX; i <= maxX; i++) {
            for (int j = initY; j <= maxY; j++) {
                System.out.println("(i) initX: " + i + " initY: " + j);
                if (!minefield.getCellStatus(i, j)) {
                    count++;
                    System.out.println("Count: " + count);
                }
            }
        }
        System.out.println("Count: "+count);

    }
}
