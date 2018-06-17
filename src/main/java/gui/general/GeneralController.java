package gui.general;

import entity.Minefield;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class GeneralController {

    @FXML
    private GridPane mines;

    @FXML
    private Rectangle green = new Rectangle(40,40);

    @FXML
    private Rectangle red = new Rectangle(40,40);

    @FXML
    private void initialize() {
        setRectangle();
        fillMinefield();
    }

    @FXML
    private void fillMinefield() {
        mines.getChildren().clear();
        Minefield minefield = new Minefield();
        minefield.fill();
        for (int i = 0; i < minefield.getSize(); i++) {
            for (int j = 0; j < minefield.getSize(); j++) {
                if (minefield.getCellStatus(i, j)) {
                    mines.add(green,i,j);
                }
                else {
                    mines.add(red,i,j);
                }
            }
        }
    }

    @FXML
    private void setRectangle() {
        green.setStyle("-fx-background-color: green");
        red.setStyle("-fx-background-color: red");
    }
}
