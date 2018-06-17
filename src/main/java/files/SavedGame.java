package files;

import entity.Minefield;

import java.io.*;
import java.util.Base64;
import java.util.Objects;

public class SavedGame {

    public static boolean contin = false;

    /**
     *  The source of the following code is the website "https://devcolibri.com/";
     */

    private static String fileName = "MinesweeperSavedGame.txt";
    private File fileSave = new File(fileName);
    private static File fileLoad = new File(fileName);

    public void saveGame(Minefield mf) {
        try {
            if (!fileSave.exists()) {
                System.err.println("File existence: false");
                fileSave.createNewFile();
                System.out.println("Creating a file: true");
            } else {
                System.out.println("File existence: true");
            }
        }catch (IOException e) {
            System.err.println("Creating a file: false");
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(fileSave.getAbsoluteFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ///Записываем текст у файл
        out.print(encode(mf.getField()));

        out.close();
        System.out.println("Save game successful");
    }

    public String encode(boolean[][] array) {
        String newValue = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[i][j]) {
                    newValue += "+";
                }else {
                    newValue += "-";
                }
            }
        }
        return new String(Base64.getEncoder().encode(newValue.getBytes()));
    }

    public static boolean[][] decode(String encodeStr) {
        boolean[][] array = new boolean[8][8];
        char[] decodeStr = new String(Base64.getDecoder().decode(encodeStr.getBytes())).toCharArray();
        int l = 0;
        for (int i = 0; i < Math.sqrt(decodeStr.length); i++) {
            for (int j = 0; j < Math.sqrt(decodeStr.length); j++, l++) {
                if(decodeStr[l] == '+') {
                    array[i][j] = true;
                }else array[i][j] = false;
            }
        }
        return array;
    }

    public static boolean[][] loadGame() {
        String res = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileLoad.getAbsoluteFile()));
            System.out.println("Reading file: true");
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    res = s;
                }
                System.out.println("Reading file: true");
            } finally {
                in.close();
            }
        } catch(IOException e) {
            System.err.println("Reading file: false");
        }
        return decode(res);
    }
}
