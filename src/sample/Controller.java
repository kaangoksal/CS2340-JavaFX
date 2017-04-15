package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import sample.model.User;

public class Controller {

    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private CheckBox RememberMeCheckBox;
    @FXML
    private Button RegisterButton;

    public void initialize() {

        RegisterButton.setOnAction(this::RegisterButtonAction);

    }

    private void RegisterButtonAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.print("Hello");
    }

    private User LoginButtonAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.print("Hello");
        return null;
    }
}
