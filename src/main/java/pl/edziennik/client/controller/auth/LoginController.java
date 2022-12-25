package pl.edziennik.client.controller.auth;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.AccountType;
import pl.edziennik.client.common.ConfirmationDialogFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

     /*
        CLASSES
     */

    private final ConfirmationDialogFactory dialogFactory;


    public LoginController() {
        this.dialogFactory = ConfirmationDialogFactory.getInstance();
    }

     /*
        FXML
     */

    @FXML
    private ComboBox<String> accountTypeCheckBox;

    @FXML
    private Button exitButton, loginButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameInput;

    /*
        CODE
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCheckBox();
        setExitButtonAction();
        bindLoginButtonToFields();
    }


    private void initializeCheckBox() {
        accountTypeCheckBox.getItems().setAll(AccountType.STUDENT.getDescription(), AccountType.WORKER.getDescription());
        accountTypeCheckBox.getSelectionModel().selectFirst();
    }

    private void setExitButtonAction() {
        exitButton.setOnAction(button -> dialogFactory.createExitConfirmationDialog(getStage()));
    }

    private void bindLoginButtonToFields() {
        loginButton.disableProperty().bind(
                Bindings.isEmpty(usernameInput.textProperty())
                        .or(Bindings.isEmpty(passwordInput.textProperty()))
        );
    }


    private Stage getStage() {
        return (Stage) usernameInput.getScene().getWindow();
    }


}
