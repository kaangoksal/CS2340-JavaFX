package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.ScreensController;
import sample.ScreensFramework;
import sample.model.ServerConnector;
import sample.model.Title;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable, ControlledScreen{

    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField UsernameTextField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private PasswordField PasswordField2;
    @FXML
    private TextArea AddressField;
    @FXML
    private Button RegisterButton;

    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RegisterButton.setOnAction(this::RegisterButtonAction);
    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }


//
//    public User(String Username, String password, String email) {
//        this.username = Username;
//        this.password = password;
//        this.email = email;
//    }


    private void RegisterButtonAction(ActionEvent event) {
        // Button was clicked, do something...

        if (PasswordField.getText().equals(PasswordField2.getText())){
            User newuser = new User(UsernameTextField.getText(),PasswordField.getText(), EmailTextField.getText());
            newuser.setTitle(Title.USER);
            try {
                if (ServerConnector.addUser(newuser)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("User Registration Complete");
                    alert.setHeaderText("You can return back to login now and log in1");
                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("User Registration Problem");

                    alert.setHeaderText("Some Problem Happened!");

                    alert.setContentText("The server returned false");
                    alert.showAndWait();
                }
            } catch (IOException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User Registration Problem");

                alert.setHeaderText("Some Problem Happened!");

                alert.setContentText(e.getClass().getName() + " -> " + e.getMessage());
                alert.showAndWait();
            }



        }
        System.out.print("Hello");

    }

//    private User LoginButtonAction(ActionEvent event) {
//        // Button was clicked, do something...
//
//        User user = new User(EmailTextField.getText(), PasswordTextField.getText());
//        try {
//            user = ServerConnector.attemptLogin(user);
//            System.out.println("Login Successful " + user.toString());
//        } catch (IOException E) {
//            System.out.println("Login Error");
//        }
//
//        return user;
//    }

    @FXML
    private void goToScreen2(ActionEvent event){
        myController.setScreen(ScreensFramework.screen2ID);
    }
}
