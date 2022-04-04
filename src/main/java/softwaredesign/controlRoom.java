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
    private Button clrBtn;

    @FXML
    private Tab InvitePlayersTab;

    @FXML
    private ListView<String> LstVwPlayers;

    @FXML
    private Button inviteBtn;

    @FXML
    private Label InviteAck;

    @FXML
    private Button BackBtn;

    @FXML
    private Tab CurrentPlayersTab;

    @FXML
    private Button CurMidBtn;

    @FXML
    private Label CenterLabel;

    @FXML
    private Button CurTopRightBtn;

    @FXML
    private Label RightTopLabel;

    @FXML
    private Button CurBotRightBtn;

    @FXML
    private Label RightBottomLabel;

    @FXML
    private Button CurTopLeftBtn;

    @FXML
    private Label LeftTopLabel;

    @FXML
    private Button CurBotLeftBtn;

    @FXML
    private Label LeftBottomLabel;

    @FXML
    private ImageView MsgImg;

    @FXML
    private ToggleButton KillBtnToggle;

    @FXML
    private ImageView KillImg;

    @FXML
    private Tab GameSequenceTab;

    @FXML
    private ListView<String> LstVwGames;

    @FXML
    private Button inviteBtn1;

    @FXML
    private Tab CurrentGameTab;

    @FXML
    private ListView<String> lstViewGames;

    @FXML
    private ImageView playImg;

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

    String[] getAllInvitedUsers() throws Exception {
        UserResponse res = api.get(NetworkingConstants.GET_INVITED_USERS_PATH, new UserResponse());
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

    void deletePlayer(String username) throws Exception {
        GeneralResponse res = this.api.delete(NetworkingConstants.DELETE_PLAYER_PATH + "?username=" + username, new GeneralResponse());
        if(res.message == "NO_USER_FOUND") {
            System.out.println("No user exists with username: " + username);
            return;
        }

        System.out.println(res.message);
        int idx = -1;

        for (int i = 0; i < this.selectedPlayers.length; i++) {
            if(this.selectedPlayers[i].equals(username)) {
                idx = i;
                break;
            }
        }
        //  This is removing the element in idx
        System.arraycopy(this.selectedPlayers, idx + 1, this.selectedPlayers, idx, this.selectedPlayers.length - idx - 1);
    }

    void populateList() throws Exception{
        String[] users = getAllWaitingUsers();
        Game[] games = getDeveloperGames();
        for(Game game:games){
//            System.out.println(game.gameName+"\n");
            LstVwGames.getItems().add(game.gameName);
        }
        if(users.length > 0){
            LstVwPlayers.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(users))));
        }
//        if(games.length >0){
//            LstVwGames.setItems(FXCollections.observableArrayList(new ArrayList<>(List.of(games.toString()))));
//        }

    }

    void populateButtons() throws Exception{
        String[] users = getAllInvitedUsers();
        int lengting = users.length;
        switch (lengting){
            case 1:
                LeftTopLabel.setText(users[0]);
                break;
            case 2:
                LeftTopLabel.setText(users[0]);
                LeftBottomLabel.setText(users[1]);
                break;
            case 3:
                LeftTopLabel.setText(users[0]);
                LeftBottomLabel.setText(users[1]);
                CenterLabel.setText(users[2]);
                break;
            case 4:
                LeftTopLabel.setText(users[0]);
                LeftBottomLabel.setText(users[1]);
                CenterLabel.setText(users[2]);
                RightTopLabel.setText(users[3]);
                break;
            case 5:
                LeftTopLabel.setText(users[0]);
                LeftBottomLabel.setText(users[1]);
                CenterLabel.setText(users[2]);
                RightTopLabel.setText(users[3]);
                RightBottomLabel.setText(users[4]);
                break;
            default:
                break;

        }


    }

    @FXML
    void backScreenChange(ActionEvent event) throws IOException {
    Main m = new Main();
    m.screenChange("src/main/java/softwaredesign/startScreen.fxml");
    }

    @FXML
    void removeFromList(ActionEvent event) throws Exception {
        String[] users = getAllInvitedUsers();
        int flag = 0;
        if(KillBtnToggle.isSelected()){
            if(CurTopLeftBtn.isFocused()){
                flag = 1;
            }
            if(CurBotLeftBtn.isFocused()){
                flag = 2;
            }
            if(CurMidBtn.isFocused()){
                flag = 3;
            }
            if(CurTopRightBtn.isFocused()){
                System.out.println("in curtopright flag set to 4");
                flag = 4;
            }
            if(CurBotRightBtn.isFocused()){
                flag = 5;
            }
        }
        System.out.println(users.length);
        if(KillBtnToggle.isSelected() && flag > 0 && users.length > 0){
            System.out.println("killbtn focused && flag = " + flag+"\n");
            switch (flag){
                case 1:
//                    deletePlayer(users[0]);
                    System.out.println("player curtopleft has been deleted");
                    break;
                case 2:
//                    deletePlayer(users[1]);
                    break;
                case 3:
//                    deletePlayer(users[2]);
                    break;
                case 4:
//                    deletePlayer(users[3]);
                    System.out.println("player curtopright has been deleted");
                    break;
                case 5:
//                    deletePlayer(users[4]);
                    break;
                default:
                    break;
            }
        }

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

    @FXML
    void setSequence(ActionEvent event) throws Exception{

        if(event != null){
            InviteAck.setTextFill(Color.GREEN);
            InviteAck.setText(LstVwGames.getSelectionModel().getSelectedItem());
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(f-> InviteAck.setText(null));
            pause.play();
        }
    }

    @FXML
    void clearPlayers(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if(CurrentPlayersTab.selectedProperty().getValue()){
                Main m = new Main();
                m.screenChange("src/main/java/softwaredesign/masterRoom.fxml");
            }
            CurrentPlayersTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
                try {

                    populateButtons();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            populateList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
