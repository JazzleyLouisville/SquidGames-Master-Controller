package softwaredesign;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class waitingController implements Initializable{
    @FXML
    private Rectangle square;
    @FXML
    private Circle circle;
    @FXML
    private ImageView triangle;
    @FXML
    private Label label;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRotate(circle, true, 360, 60);
        setSquare(square,true, 180, 60);
        setTriangle(triangle, false, 360, 60);
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

}


