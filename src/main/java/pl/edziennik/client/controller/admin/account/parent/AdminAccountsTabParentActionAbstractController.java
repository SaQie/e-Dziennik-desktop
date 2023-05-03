package pl.edziennik.client.controller.admin.accounts.parent;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.common.Role;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.DictionaryItemModel;
import pl.edziennik.client.rest.dto.parent.ParentDto;
import pl.edziennik.client.rest.dto.parent.ParentRequestDto;
import pl.edziennik.client.task.student.LoadStudentsTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.UUID;

class AdminAccountsTabParentActionAbstractController extends AbstractController {


    @FXML
    protected ComboBox<DictionaryItemModel> studentComboBox;

    @FXML
    protected TextField usernameTextField, firstNameTextField, lastNameTextField, addressTextField, postalCodeTextField, cityTextField,
            peselTextField, phoneNumberTextField, emailTextField, roleTextField;


    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
    }

    @Override
    protected void fetchStageData() {
        roleTextField.setText(Role.ROLE_PARENT.name());
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.initializeDictionary(LoadStudentsTask.class, studentComboBox);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }

    protected ParentRequestDto mapToDto() {
        ParentRequestDto dto = new ParentRequestDto();
        dto.setAddress(addressTextField.getText());
        dto.setCity(cityTextField.getText());
        dto.setEmail(emailTextField.getText());
        dto.setRole(roleTextField.getText());
        dto.setPesel(peselTextField.getText());
        dto.setFirstName(firstNameTextField.getText());
        dto.setLastName(lastNameTextField.getText());
        dto.setPhoneNumber(phoneNumberTextField.getText());
        dto.setPostalCode(postalCodeTextField.getText());
        dto.setUsername(usernameTextField.getText());
        dto.setStudentId(studentComboBox.getValue().getId());
        String password = UUID.randomUUID().toString();
        // TODO, ten print bedzie do zmiany, haslo bedzie wysylane mailem
        System.out.println("Random uuid: " + password);
        dto.setPassword(password);
        return dto;
    }

    protected void loadStageFields(ParentDto dto, ActionType type) {
        usernameTextField.setText(dto.getUsername());
        firstNameTextField.setText(dto.getFirstName());
        lastNameTextField.setText(dto.getLastName());
        roleTextField.setText(dto.getRole());
        addressTextField.setText(dto.getAddress());
        cityTextField.setText(dto.getCity());
        postalCodeTextField.setText(dto.getPostalCode());
        peselTextField.setText(dto.getPesel());
        emailTextField.setText(dto.getEmail());
        phoneNumberTextField.setText(dto.getPhoneNumber() == null ? "" : dto.getPhoneNumber());
        studentComboBox.getSelectionModel().select(new DictionaryItemModel(dto.getStudent().getStudentId(), dto.getStudent().getFullName()));
        if (ActionType.SHOW_ACTION.equals(type)) {
            studentComboBox.setOnShown(show -> studentComboBox.hide());
        }
    }
}
