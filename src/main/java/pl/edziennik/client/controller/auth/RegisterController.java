package pl.edziennik.client.controller.auth;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.common.ProgressFactory;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.AdminPojo;
import pl.edziennik.client.task.RegisterAdminTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ValidationUtil;
import pl.edziennik.client.validator.auth.AuthValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    /*
        CLASSES
     */

    private final DialogFactory dialogFactory;
    private final AuthValidator authValidator;
    private final ProgressFactory progressFactory;


    public RegisterController() {
        this.dialogFactory = DialogFactory.getInstance();
        this.authValidator = new AuthValidator();
        this.progressFactory = ProgressFactory.getInstance();
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

    @FXML
    private Tab registerTab;

    /*
        CODE
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NodeUtils.createExitButtonAction(exitButton);
        setRegisterButtonAction();
        initializeFieldValidators();
        initializeRegisterButton();
        clearFieldsAfterChangeTab();
    }

    private void clearFieldsAfterChangeTab() {
        registerTab.selectedProperty().addListener(property -> {
            if (!registerTab.isSelected()){
                ValidationUtil.clearFields(emailInput, passwordInput, repeatPasswordInput, usernameInput);
            }
        });
    }


    private void setRegisterButtonAction() {
        registerButton.setOnAction(e -> {
            AdminPojo adminPojo = new AdminPojo();
            adminPojo.setPassword(passwordInput.getText());
            adminPojo.setEmail(emailInput.getText());
            adminPojo.setUsername(usernameInput.getText());
            progressFactory.createLittleProgressBar(new RegisterAdminTask(adminPojo), (response) -> {
                dialogFactory.createSuccessInformationDialog(null);
                ValidationUtil.clearFields(usernameInput,passwordInput,emailInput,repeatPasswordInput);
            });
        });
    }


    private void initializeFieldValidators() {
        authValidator.createEmailValidatorListener(emailInput);
        authValidator.createUsernameValidatorListener(usernameInput);
        authValidator.createPasswordValidatorListener(passwordInput, repeatPasswordInput);
    }


    private void initializeRegisterButton() {
        NodeUtils.enableButtonIfFieldsHasNoErrors(registerButton, usernameInput, emailInput, passwordInput, repeatPasswordInput);
    }

    private Stage getStage() {
        return (Stage) usernameInput.getScene().getWindow();
    }

}
