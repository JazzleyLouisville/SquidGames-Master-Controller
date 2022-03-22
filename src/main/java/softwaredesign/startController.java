package softwaredesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import softwaredesign.responses.GeneralResponse;
import softwaredesign.responses.PasswordResponse;

import java.io.IOException;
import java.util.HashMap;

public class startController extends Main {
    @FXML
    private PasswordField password;
    @FXML
    private Label pLabel;
    @FXML
    private TextField username;

    static public String publicUsername;

    API api;

    public startController() {
        api = new API();
    }

    public void masterLogIn(ActionEvent event) throws Exception {
        checkMaster();
    }

    public void userLogIn(ActionEvent event) throws IOException {
        Main m = new Main();
        m.screenChange("src/main/java/softwaredesign/userLogIn.fxml");
    }

    public void waitingRoom(ActionEvent event) throws Exception {
        checkUsername();
    }

    public void switchToLogIn (ActionEvent event) throws IOException{
        Main m = new Main();
        m.screenChange("src/main/java/softwaredesign/startScreen.fxml");
    }

    private void checkMaster() throws IOException, Exception {
        Main m = new Main();

        final PasswordResponse response = api.get("get_password", new PasswordResponse());
        final String savedPassword = response.password;
        if (password.getText().equals(savedPassword)) {
            pLabel.setTextFill(Color.GREEN);
            pLabel.setText("Permission granted");
            m.screenChange("src/main/java/softwaredesign/afterLogIn.fxml");
        } else if (password.getText().isEmpty()){
            pLabel.setText("Enter the password");
        } else {
            pLabel.setText("Permission denied");
        }
    }

    private void checkUsername() throws Exception {
        Main m = new Main();
        final String receivedUsername = username.getText();
        if (receivedUsername.isBlank()) {
            pLabel.setTextFill(Color.RED);
            pLabel.setText("Enter your username");
            return;
        }
        sendUsername(receivedUsername);
        m.screenChange("src/main/java/softwaredesign/waitingRoom.fxml");

    }
    private boolean sendUsername(String username) throws Exception {
        final HashMap body = new HashMap();
        body.put("username", username);
        startController.publicUsername = username;
        final GeneralResponse response = api.post("user", body, new GeneralResponse());
        System.out.println(response.message);
        return true;
    }
}
