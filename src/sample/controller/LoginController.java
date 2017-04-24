package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import sample.Main;
import sample.ScreensController;
import sample.ScreensFramework;
import sample.model.User;
import sample.model.ServerConnector;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, ControlledScreen{

    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private CheckBox RememberMeCheckBox;
    @FXML
    private Button RegisterButton;
    @FXML
    private Button LoginButton;

    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RegisterButton.setOnAction(this::RegisterButtonAction);
        LoginButton.setOnAction(this::LoginButtonAction);
    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    private void RegisterButtonAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.print("Hello");
        myController.setScreenWithSize(Main.registerScreen, 350, 600);
//        Main.currentstage.setWidth(400);
//        Main.currentstage.setHeight(600);
    }

    private void LoginButtonAction(ActionEvent event) {
        // Button was clicked, do something...

        User user = new User(EmailTextField.getText(), PasswordTextField.getText());
        try {
            user = ServerConnector.attemptLogin(user);
            System.out.println("Login Successful " + user.toString());
            myController.setScreen(Main.dashboardScreen);
        } catch (IOException E) {
            System.out.println("Login Error");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User Registration Problem");

            alert.setHeaderText("Some Problem Happened!");

            alert.setContentText("The server returned false");
            alert.showAndWait();
        }
    }

    @FXML
    private void goToScreen2(ActionEvent event){
        myController.setScreen(ScreensFramework.screen2ID);
    }
}
