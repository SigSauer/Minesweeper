package gui.general;

import entity.Minefield;
import files.SavedGame;
import gui.menu.MenuLaunch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GeneralController {

    @FXML
    private GridPane mines;

    @FXML
    private Button restartButton;

    @FXML
    private Button backButton;

    @FXML
    private Button[][] mineButton = new Button[new Minefield().getSize()][new Minefield().getSize()];

    private Minefield minefield;
    private Minefield currentGame;
    private boolean changeMap = false;

    private int[] lastButton = new int[2];


    @FXML
    private void initialize() {
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
                mineButton[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        mineButton[I][J].setStyle("-fx-background-color: grey");
                        lastButton[0] = I;
                        lastButton[1] = J;
                        checkForCellStatus(I,J);
                        mineButton[I][J].setDisable(true);
                    }
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
        if(changeMap){
            load();
        }else {
            load(currentGame.getField());
        }
    }

}
