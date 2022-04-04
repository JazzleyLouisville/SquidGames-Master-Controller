package softwaredesign;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import softwaredesign.constants.NetworkingConstants;
import softwaredesign.responses.GeneralResponse;
import softwaredesign.responses.InvitationResponse;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class waitingController implements Initializable{
    @FXML
    private Rectangle square;
    @FXML
    private Circle circle;
    @FXML
    private ImageView triangle;
    @FXML
    private Label label;

    @FXML
    private Button BackBtn;

    @FXML
    private Label invitedLbl;

    private API api;
    final int pollingDelay = 5; //  Seconds
    public waitingController() {
        api = new API();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRotate(circle, true, 360, 60);
        setSquare(square,true, 180, 60);
        setTriangle(triangle, false, 360, 60);
        checkIfInvited();
    }
    @FXML
    void backScreenChange(ActionEvent event) throws IOException {
        Main m = new Main();
        m.screenChange("/userLogin.fxml");
    }

    private void setRotate (Circle c, boolean reverse, int angle, int duration) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration), c);

        rotateTransition.setAutoReverse(reverse);

        rotateTransition.setByAngle(angle);
        rotateTransition.setDelay(Duration.seconds(0));
        rotateTransition.setRate(15);
        rotateTransition.setCycleCount(18);
        rotateTransition.play();
    }

    private void setSquare (Rectangle c, boolean reverse, int angle, int duration) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration), c);

        rotateTransition.setAutoReverse(reverse);

        rotateTransition.setByAngle(angle);
        rotateTransition.setDelay(Duration.seconds(0));
        rotateTransition.setRate(20);
        rotateTransition.setCycleCount(18);
        rotateTransition.play();
    }

    private void setTriangle (ImageView c, boolean reverse, int angle, int duration) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration), c);

        rotateTransition.setByAngle(angle);
        rotateTransition.setRate(20);
        rotateTransition.setCycleCount(18);
        rotateTransition.play();
    }

    void checkIfInvited() {
        Timer timer = new Timer();
        waitingController _this = this;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    InvitationResponse response = _this.api.get(NetworkingConstants.GET_INVITATION_PATH + "?username=" + startController.publicUsername, new InvitationResponse());
                    if(response.invited) {
                        timer.cancel();
                        //  Other stuff;
                        onInvite();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, pollingDelay * 1000);
    }

    void onInvite() throws IOException {

            Main m = new Main();
            m.screenChange("/waitingRoomInvite.fxml");

    }
}


