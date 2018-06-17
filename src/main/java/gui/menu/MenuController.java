package gui.menu;

import files.SavedGame;
import gui.general.GeneralLaunch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button loadGameButton;

    @FXML
    private Button gameSettingsButton;

    @FXML
    private Button newGameButton;

    @FXML
    private Button exitButton;

    @FXML
    private void generalWindow() {
        System.out.println("New Window: General");
        try {
            new GeneralLaunch().start(new Stage());
            System.out.println("New Window: true");
            Stage s = (Stage) newGameButton.getScene().getWindow();
            s.close();
        } catch (Exception e) {
            System.err.println("New Window: false");
            e.printStackTrace();
        }
    }

    @FXML
    private void loadGame() {
        SavedGame.contin = true;
        generalWindow();
    }

    @FXML
    private void exit() {
        System.exit(0);
    }

}
