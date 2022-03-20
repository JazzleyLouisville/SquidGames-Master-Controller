package softwaredesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.io.IOException;

public class startController extends Main {
    @FXML
    private PasswordField password;
    @FXML
    private Label pLabel;
    @FXML
    private TextField username;

    public void masterLogIn(ActionEvent event) throws IOException{
        checkMaster();
    }

    public void userLogIn(ActionEvent event) throws IOException{
        Main m = new Main();
        m.screenChange("src/main/java/softwaredesign/userLogIn.fxml");
    }

    public void waitingRoom(ActionEvent event) throws IOException{
        checkUsername();
    }

    public void switchToLogIn (ActionEvent event) throws IOException{
        Main m = new Main();
        m.screenChange("src/main/java/softwaredesign/startScreen.fxml");
    }

    private void checkMaster() throws IOException {
        Main m = new Main();

        if (password.getText().toString().equals("nigger")) {
            pLabel.setTextFill(Color.GREEN);
            pLabel.setText("Permission granted");
            m.screenChange("src/main/java/softwaredesign/afterLogIn.fxml");
        } else if (password.getText().isEmpty()){
            pLabel.setText("Enter the password");
        } else {
            pLabel.setText("Permission denied");
        }
    }

    private void checkUsername() throws IOException{
        Main m = new Main();

        if (username.getText().isBlank()) {
            pLabel.setTextFill(Color.RED);
            pLabel.setText("Enter your username");
        } else {
            m.screenChange("src/main/java/softwaredesign/waitingRoom.fxml");
        }
    }
}
