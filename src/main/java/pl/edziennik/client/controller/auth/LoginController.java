package pl.edziennik.client.controller.auth;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.AccountType;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.common.ProgressFactory;
import pl.edziennik.client.rest.AuthorizationRestClient;
import pl.edziennik.client.rest.pojo.LoginCredentialsPojo;
import pl.edziennik.client.utils.AuthorizationUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

     /*
        CLASSES
     */

    private final DialogFactory dialogFactory;
    private final AuthorizationRestClient authorizationRestClient;

    public LoginController() {
        this.dialogFactory = DialogFactory.getInstance();
        this.authorizationRestClient = new AuthorizationRestClient();
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
        initializeLoginButton();
        initializeCheckBox();
        setExitButtonAction();
        bindLoginButtonToFields();
    }

    private void initializeLoginButton() {
        loginButton.setOnAction(button -> {
            LoginCredentialsPojo credentialsPojo = new LoginCredentialsPojo();
            credentialsPojo.setUsername(usernameInput.getText());
            credentialsPojo.setPassword(passwordInput.getText());
            authorizationRestClient.login(credentialsPojo);
            AuthorizationUtils.showCorrectSceneAfterLogin(getStage());
        });
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
