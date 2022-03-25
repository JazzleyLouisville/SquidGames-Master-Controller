package softwaredesign;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import softwaredesign.constants.NetworkingConstants;
import softwaredesign.responses.GameFromServer;
import softwaredesign.responses.GameResponse;
import softwaredesign.responses.GeneralResponse;
import softwaredesign.responses.UserResponse;

import java.util.HashMap;

public class controlRoom {

    @FXML
    private Tab InvitePlayersTab;

    @FXML
    private ListView<?> LstVwPlayers;


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

//    @FXML
//    void controlRoom(ActionEvent event) {
//
//    }

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

}
