package pl.edziennik.client.controller.auth;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.ConfirmationDialogFactory;
import pl.edziennik.client.validator.auth.AuthValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    /*
        CLASSES
     */

    private final ConfirmationDialogFactory dialogFactory;
    private final AuthValidator authValidator;


    public RegisterController() {
        this.dialogFactory = ConfirmationDialogFactory.getInstance();
        this.authValidator = new AuthValidator();
    }

    /*
        FXML
     */

    @FXML
    private TextField emailInput;

    @FXML
    private Button exitButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button registerButton;

    @FXML
    private PasswordField repeatPasswordInput;

    @FXML
    private TextField usernameInput;

    /*
        CODE
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setExitButtonAction();
        initializeFieldValidators();
        initializeRegisterButton();
    }


    private void setExitButtonAction() {
        exitButton.setOnAction(button -> dialogFactory.createExitConfirmationDialog(getStage()));
    }


    private void initializeFieldValidators() {
        authValidator.createEmailValidatorListener(emailInput);
        authValidator.createUsernameValidatorListener(usernameInput);
        authValidator.createPasswordValidatorListener(passwordInput, repeatPasswordInput);
    }


    private void initializeRegisterButton() {
        authValidator.enableButtonIfNoErrors(registerButton, usernameInput, emailInput, passwordInput, repeatPasswordInput);
    }

    private Stage getStage() {
        return (Stage) usernameInput.getScene().getWindow();
    }

}
