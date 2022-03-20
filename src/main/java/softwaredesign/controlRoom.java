package softwaredesign;

import javafx.event.ActionEvent;
import java.io.IOException;

public class controlRoom {

    public void logOut (ActionEvent event) throws IOException {
        Main m = new Main();
        m.screenChange("src/main/java/softwaredesign/startScreen.fxml");
    }
}
