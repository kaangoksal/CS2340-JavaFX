package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
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
    }

    private User LoginButtonAction(ActionEvent event) {
        // Button was clicked, do something...

        User user = new User(EmailTextField.getText(), PasswordTextField.getText());
        try {
            user = ServerConnector.attemptLogin(user);
            System.out.println("Login Successful " + user.toString());
        } catch (IOException E) {
            System.out.println("Login Error");
        }

        return user;
    }

    @FXML
    private void goToScreen2(ActionEvent event){
        myController.setScreen(ScreensFramework.screen2ID);
    }
}
