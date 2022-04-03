package softwaredesign;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import softwaredesign.constants.NetworkingConstants;
import softwaredesign.responses.GeneralResponse;

import java.io.IOException;
import java.util.HashMap;

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
    void devSend(ActionEvent event) throws Exception {
        Game game = new Game(gameNameLbl.getText(), Integer.parseInt(roundLbl.getText()), Float.parseFloat(winPerLbl.getText()), GameState.STOP);
        submitGame(game);
        //  Further implementation after submitting a game
    }

    private API api;

    public devController() {
        this.api = new API();
    }

    private void submitGame(Game game) throws Exception {
        HashMap body = new HashMap();
        body.put("gameName", game.gameName);
        body.put("rounds", game.rounds);
        body.put("winPercentage", game.winPercentage);
        GeneralResponse response = api.post(NetworkingConstants.POST_DEV_GAME_PATH, body, new GeneralResponse());
        System.out.println(response.message);
    }

    @FXML
    void backScreenChange(ActionEvent event) throws IOException {
        Main m = new Main();
        m.screenChange("src/main/java/softwaredesign/startScreen.fxml");
    }

}
