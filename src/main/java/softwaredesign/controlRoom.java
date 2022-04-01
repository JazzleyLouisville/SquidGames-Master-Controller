package softwaredesign;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import softwaredesign.constants.NetworkingConstants;
import softwaredesign.responses.GameFromServer;
import softwaredesign.responses.GameResponse;
import softwaredesign.responses.GeneralResponse;
import softwaredesign.responses.UserResponse;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class controlRoom implements Initializable {


    @FXML
    private Button inviteBtn;
    @FXML
    private Button BackBtn;

    @FXML
    private Tab InvitePlayersTab;

    @FXML
    private ListView<String> LstVwPlayers; //was <?>

    private ObservableList<String> names;

    @FXML
    private ImageView BackArrow;

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
    private Tab CurrentGameTab;

    private API api;

    String[] selectedPlayers;


    public controlRoom() throws Exception {
        api = new API();

    }
    void inviteUsers(String[] users) throws Exception {
        if (users.length < 1) {
            throw new Exception("No users to invite.");
        }
        final HashMap body = new HashMap();
        body.put("usernames", users);
        final GeneralResponse response;
        try {
            response = api.post(NetworkingConstants.POST_PLAYERS_PATH, body, new GeneralResponse()); // Players are invited.
            System.out.println(response.message); //  Will print out which people are invited
            this.selectedPlayers = users;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    String[] getAllWaitingUsers() throws Exception {
        UserResponse res = api.get(NetworkingConstants.GET_USER_PATH, new UserResponse());
        return res.users;
    }

    Game[] getDeveloperGames() throws Exception {
        GameResponse response = api.get(NetworkingConstants.GET_DEV_GAMES_PATH, new GameResponse());
        Game[] gamesToReturn = new Game[response.games.length];
        for (int i = 0; i < response.games.length; i++) {
            GameFromServer currentGame = response.games[i];
            gamesToReturn[i] = new Game(currentGame.gameName, currentGame.rounds, currentGame.winPercentage, GameState.STOP);
        }
        return gamesToReturn;
    }

    void populateList() throws Exception{
        String[] users = getAllWaitingUsers();
        if(users.length > 0){
            LstVwPlayers.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(users))));
        }

    }


    @FXML
    void backScreenChange(ActionEvent event) throws IOException {
    Main m = new Main();
    m.screenChange("src/main/java/softwaredesign/startScreen.fxml");
    }
    public void sendInvite(ActionEvent event) throws Exception {
        if(event != null){
            InviteAck.setTextFill(Color.GREEN);
            InviteAck.setText("Invited");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(f-> InviteAck.setText(null));
            pause.play();
        }


        String[]user = {LstVwPlayers.getSelectionModel().getSelectedItem()};
        inviteUsers(user);

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
