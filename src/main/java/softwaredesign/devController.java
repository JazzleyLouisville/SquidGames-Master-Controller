package softwaredesign;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class devController {

    @FXML
    private TextField gameNameLbl;

    @FXML
    private TextField roundLbl;

    @FXML
    private TextField winPerLbl;

    @FXML
    private Button devBtn;


    @FXML
    private Button BackBtn;

    @FXML
    void devSend(ActionEvent event) {

    }

    @FXML
    void backScreenChange(ActionEvent event) throws IOException {
        Main m = new Main();
        m.screenChange("src/main/java/softwaredesign/startScreen.fxml");
    }

}
