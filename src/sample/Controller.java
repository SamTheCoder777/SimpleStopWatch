package sample;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private Button start;

    @FXML
    private Button stop;

    @FXML
    private Button reset;

    @FXML
    private TextArea stopwatch;


    boolean isStopPressed = false;

    public Controller()
    {

    }

    @FXML
    private void initialize()
    {

    }
    private long createdMillis = System.currentTimeMillis();
    private long nowMillis;
    private long tempNowMillis;
    private long tempCreatedMillis;
    private long tempMillis = 0;
    private boolean isFirstTime = true;
    private boolean isResetPressed = false;
    private long tempCounter = 0;
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), (ActionEvent event) -> {
        nowMillis = System.currentTimeMillis();
        if (tempCounter == tempMillis){
            tempMillis = 0;
            tempCounter = 0;
        }
        stopwatch.setText(String.valueOf(((nowMillis - createdMillis  - tempMillis) / 1000) % 60) + "." + String.valueOf(((nowMillis - createdMillis  - tempMillis) % 1000)));
        if(tempMillis > tempCounter) {
            tempCounter += 1;
        }
    }));
    Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(1), (ActionEvent event) -> {
        tempNowMillis = System.currentTimeMillis();
        tempMillis = tempNowMillis - tempCreatedMillis;
    }));
    @FXML
    private void startTimer()
    {
            if(isFirstTime || isResetPressed){
                createdMillis = System.currentTimeMillis();
                isResetPressed = false;
                isFirstTime = false;
            }
            else {
                timeline2.stop();
            }

            isStopPressed = false;

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
    }

    @FXML
    private void stopTimer()
    {
        timeline.stop();
        tempCounter = 0;
        tempCreatedMillis = System.currentTimeMillis();
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();
        isStopPressed = true;
    }

    @FXML
    private void resetTimer()
    {
        if(isStopPressed) {
            timeline.stop();
            timeline2.stop();
            isResetPressed = true;
            stopwatch.setText("0.00");
            tempMillis = 0;
            tempCounter = 0;
        }
    }

}
