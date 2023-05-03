package pl.edziennik.client.controller.auth;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.AccountType;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.toast.Toast;
import pl.edziennik.client.core.toast.ToastType;
import pl.edziennik.client.rest.dto.auth.LoginCredentialsDto;
import pl.edziennik.client.task.auth.LoginUserTask;
import pl.edziennik.client.util.AuthorizationUtils;
import pl.edziennik.client.util.NodeUtils;

public class LoginController extends AbstractController {


    @FXML
    private ComboBox<String> accountTypeCheckBox;

    @FXML
    private Button exitButton, loginButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameInput;


    @Override
    protected void createActions() {
        NodeUtils.createExitButtonAction(exitButton);
        initializeLoginButton();
        initializeCheckBox();
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.enableButtonIfFieldsAreNotEmpty(loginButton, usernameInput,passwordInput);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) usernameInput.getScene().getWindow();
    }

    private void initializeLoginButton() {
        loginButton.setOnAction(button -> {
            LoginCredentialsDto credentialsPojo = new LoginCredentialsDto();
            credentialsPojo.setUsername(usernameInput.getText());
            credentialsPojo.setPassword(passwordInput.getText());
            progressFactory.createLittleProgressBar(new LoginUserTask(credentialsPojo), (response) -> {
                AuthorizationUtils.showCorrectSceneAfterLogin(getActualStage());
                Toast.show(ToastType.INFORMATION,ResourceConst.LOGIN_SUCCESSFULL.value());
            });
        });
    }


    private void initializeCheckBox() {
        accountTypeCheckBox.getItems().setAll(AccountType.STUDENT.getDescription(), AccountType.WORKER.getDescription());
        accountTypeCheckBox.getSelectionModel().selectFirst();
    }


}
