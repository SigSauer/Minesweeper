package gui.general;

import entity.Minefield;
import files.SavedGame;
import gui.menu.MenuLaunch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GeneralController {

    @FXML
    private GridPane mines;

    @FXML
    private Button restartButton;

    @FXML
    private Button backButton;

    @FXML
    private Text scoreText;

    @FXML
    private Text timerText;

    @FXML
    private Button[][] mineButton = new Button[new Minefield().getSize()][new Minefield().getSize()];

    private Minefield minefield;
    private Minefield currentGame;
    private boolean changeMap = false;
    private int scores = 0;
    private int[] lastButton = new int[2];



    @FXML
    private void initialize() {
        scoreText.setText(0+"");
        if(SavedGame.contin) {
            load(SavedGame.loadGame());
        }else {
            load();
        }
    }

    @FXML
    private void load(boolean[][] array) {
        minefield = new Minefield(array);
        setButtonAction();
    }

    @FXML
    private void load() {
        minefield = new Minefield();
        setButtonAction();
    }

    @FXML
    private void setButtonAction() {
        for (int i = 0; i < mineButton.length; i++) {
            for (int j = 0; j < mineButton.length; j++) {
                mineButton[i][j] = new Button("");
                mineButton[i][j].setPrefWidth(40);
                mineButton[i][j].setPrefHeight(40);
                final int I = i;
                final int J = j;
                mineButton[i][j].setOnAction(event -> {
                    mineButton[I][J].setStyle("-fx-background-color: grey");
                    lastButton[0] = I;
                    lastButton[1] = J;
                    calculate(I,J);
                    checkForCellStatus(I,J);
                    mineButton[I][J].setDisable(true);
                });
            }
        }
        fillMinefield();
    }

    @FXML
    private void fillMinefield() {
        mines.getChildren().clear();
        for (int i = 0; i < minefield.getSize(); i++) {
            for (int j = 0; j < minefield.getSize(); j++) {
                mines.add(mineButton[i][j],i,j);
                currentGame = minefield;
            }
        }
    }


    private void getAllLocation() {
        blockButton();
        for (int i = 0; i < minefield.getSize(); i++) {
            for (int j = 0; j < minefield.getSize(); j++) {
                if (!minefield.getCellStatus(i, j)) {
                    mineButton[i][j].setStyle("-fx-background-color: maroon");
                    if(lastButton[0] == i & lastButton[1] == j) {
                        mineButton[i][j].setStyle("-fx-background-color: red");
                    }
                }
            }
        }
    }

    private void checkForCellStatus(int x, int y) {
        if(!minefield.getCellStatus(x,y)) {
            getAllLocation();
            System.out.println("Bomb");
            changeMap = true;
        }else {
            System.out.println("Not a bomb");
        }
    }

    @FXML
    private void blockButton() {
        for (int i = 0; i < mineButton.length; i++) {
            for (int j = 0; j < mineButton.length; j++) {
                mineButton[i][j].setDisable(true);
            }
        }
    }

    @FXML
    private void menuWindow() {
        System.out.println("New Window: General");
        try {
            new MenuLaunch().start(new Stage());
            System.out.println("New Window: true");
            Stage s = (Stage) backButton.getScene().getWindow();
            s.close();
        } catch (Exception e) {
            System.err.println("New Window: false");
        }
    }

    @FXML
    private void save() {
        new SavedGame().saveGame(minefield);
    }

    @FXML
    private void restart() {
        scores = 0;
        scoreText.setText(String.valueOf(scores));
        if(changeMap){
            load();
        }else {
            load(currentGame.getField());
        }
    }

    private void addScore(int mult) {
        scores = scores+10*mult;
        scoreText.setText(String.valueOf(scores));
    }

    @FXML
    private void calculate(int X, int Y) {
        int count = 0;

        int maxX = X + 1;
        int maxY = Y + 1;

        int initX = X - 1;
        int initY = Y - 1;

        if (X == 0) initX++;
        if (X == 7) maxX--;
        if (Y == 0) initY++;
        if (Y == 7) maxY--;
        System.out.println(count);
        for (int i = initX; i <= maxX; i++) {
            for (int j = initY; j <= maxY; j++) {
                if(!minefield.getCellStatus(i,j)) {
                    count++;
                }
            }
        }
        mineButton[X][Y].setStyle("-fx-text-fill: blue");
        mineButton[X][Y].setText(String.valueOf(count));
        if(count == 0) addScore(1);
        else addScore(count);
    }

}
