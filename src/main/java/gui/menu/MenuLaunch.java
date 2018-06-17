package gui.menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuLaunch extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Minesweeper");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 236, 256));
        primaryStage.show();
    }

    public static void menuLaunch (String[] args) {
        launch(args);
    }

}
