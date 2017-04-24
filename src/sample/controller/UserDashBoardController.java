package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import sample.Main;
import sample.ScreensController;
import sample.model.ServerConnector;
import sample.model.User;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*

https://github.com/acaicedo/JFX-MultiScreen/tree/master/ScreensFramework/src/screensframework


 */

public class UserDashBoardController implements ControlledScreen, Initializable {

    @FXML
    private Button LogOutButton;

    ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LogOutButton.setOnAction(this::LogOutButtonAction);
    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }


    private void LogOutButtonAction(ActionEvent event) {
        myController.setScreenWithSize(Main.mainScreen, 200, 100);
    }



}
