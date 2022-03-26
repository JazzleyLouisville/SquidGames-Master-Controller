package softwaredesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;

public class masterController {

    @FXML
    private Tab InvitePlayersTab;

    @FXML
    private ListView<?> LstVwPlayers;

    @FXML
    private Button logInButton;

    @FXML
    private Label InviteAck;

    @FXML
    private Tab CurrentPlayersTab;

    @FXML
    private Label CenterLabel;

    @FXML
    private Label RightTopLabel;

    @FXML
    private Label RightBottomLabel;

    @FXML
    private Label LeftTopLabel;

    @FXML
    private Label LeftBottomLabel;

    @FXML
    private ImageView KillImg;

    @FXML
    private ImageView MsgImg;

    @FXML
    private Tab GameSequenceTab;

    @FXML
    private ListView<?> gameSeqList;

    @FXML
    private Button addSeqBtn;

    @FXML
    private Label addedLbl;

    @FXML
    private Tab CurrentGameTab;

    @FXML
    private ImageView playImg;

    @FXML
    private ImageView pauseImg;

    @FXML
    private ImageView killBtn;

    @FXML
    private Label currLbl;

    @FXML
    void masterLogIn(ActionEvent event) {

    }

}

