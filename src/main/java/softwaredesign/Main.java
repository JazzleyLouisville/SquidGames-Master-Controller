package softwaredesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private static Stage curr;

    @Override
    public void start(Stage primaryStage) throws Exception{
        curr = primaryStage;
        primaryStage.setResizable(false);
        URL url = new File("src/main/java/softwaredesign/startScreen.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Squid games");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void screenChange (String fxml) throws IOException{
        URL url = new File(fxml).toURI().toURL();
        Parent p = FXMLLoader.load(url);
        curr.getScene().setRoot(p);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}