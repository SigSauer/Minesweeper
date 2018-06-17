package files;

import org.junit.Test;
import java.util.Random;

public class SavedGameTest {
    static SavedGame sg = new SavedGame();
    static boolean[][] array = new boolean[4][4];
    static String encodingStr;

    @Test
    public static void encodingTest() {
        fillArray();
        show();
        encodingStr = sg.encode(array);
        System.out.println(encodingStr);
        decodingTest();
    }


    private static void decodingTest() {
        System.out.println();
        System.out.println(encodingStr);
        array = sg.decode(encodingStr);
        show();
    }

    private static void fillArray() {
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = r.nextBoolean();
            }
        }
    }

    private static void show() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

}
