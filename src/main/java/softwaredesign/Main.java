package softwaredesign;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//import javafx.*;

public class Main extends Application {

    Button button;
//    Button button_user;
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Test");

        button = new Button();
        button.setText("Master");
//        button_user = new Button();
//        button_user.setText("User");


        StackPane layout = new StackPane();
        layout.getChildren().add(button);
//        layout.getChildren().add(button_user);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

//        primaryStage.setScene(scene);
//        primaryStage.show();
    }
//        CommandHandler commandHandler = new CommandHandler();
//        System.out.println("Welcome to the Squid Game Control Room");
//        while (true) {
//            System.out.print("> ");
//            Scanner scanner = new Scanner(System.in);
//            String command = scanner.nextLine();
//            if(command.contains("SET GAMESTATE")) {
//                commandHandler.setGameState(command);
//            } else if(command.contains("GET GAMESTATE")) {
//                commandHandler.getGameState(command);
//            } else if(command.contains("SET GAMESEQUENCE")) {
//                commandHandler.setGameSequence(command);
//            }else if(command.contains("GET GAMESEQUENCE")) {
//                commandHandler.getGameSequence();
//            } else if(command.contains("EXIT")) {
//                System.out.println("Exited the Squid Game Control Room");
//                break;
//            }
//        }
//    }

}