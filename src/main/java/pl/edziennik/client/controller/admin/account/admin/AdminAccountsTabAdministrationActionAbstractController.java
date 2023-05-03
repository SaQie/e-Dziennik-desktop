package pl.edziennik.client.controller.admin.account.admin;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.common.Role;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.util.NodeUtils;

class AdminAccountsTabAdministrationActionAbstractController extends AbstractController {

    @FXML
    protected TextField nameTextField, emailTextField, roleTextField;

    @FXML
    protected PasswordField passwordField;

    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
    }

    @Override
    protected void setSceneSettings() {

    }

    protected void loadStageFields(AdminDto dto, ActionType type){
        nameTextField.setText(dto.getUsername());
        emailTextField.setText(dto.getEmail());
        passwordField.setText(dto.getPassword());
    }

    @Override
    protected void fetchStageData() {
        roleTextField.setText(Role.ROLE_ADMIN.name());
    }

    protected AdminDto mapToAdminDto(){
        AdminDto adminDto = new AdminDto();
        adminDto.setEmail(emailTextField.getText());
        adminDto.setPassword(passwordField.getText());
        adminDto.setUsername(nameTextField.getText());
        adminDto.setRole(roleTextField.getText());
        return adminDto;
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }
}
