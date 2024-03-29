package pl.edziennik.client.controller.admin.account.teacher;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.common.Role;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.DictionaryItemModel;
import pl.edziennik.client.rest.dto.teacher.TeacherDto;
import pl.edziennik.client.rest.dto.teacher.TeacherRequestDto;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.util.NodeUtils;

import java.util.UUID;

class AdminAccountsTabTeacherActionAbstractController extends AbstractController {

    @FXML
    protected ComboBox<DictionaryItemModel> schoolComboBox;
    @FXML
    protected TextField usernameTextField, firstNameTextField, lastNameTextField, addressTextField, postalCodeTextField, cityTextField,
            peselTextField, phoneNumberTextField, emailTextField, roleTextField;

    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);

    }

    @Override
    protected void fetchStageData() {
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.setTextFieldAsNumbersOnly(phoneNumberTextField, peselTextField);
        NodeUtils.initializeDictionary(LoadSchoolsTask.class, schoolComboBox);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }


    protected void loadStageFields(TeacherDto dto, ActionType actionType) {
        usernameTextField.setText(dto.getUsername());
        firstNameTextField.setText(dto.getFirstName());
        lastNameTextField.setText(dto.getLastName());
        roleTextField.setText(Role.ROLE_TEACHER.name());
        addressTextField.setText(dto.getAddress());
        cityTextField.setText(dto.getCity());
        postalCodeTextField.setText(dto.getPostalCode());
        peselTextField.setText(dto.getPesel());
        emailTextField.setText(dto.getEmail());
        phoneNumberTextField.setText(dto.getPhoneNumber());
        schoolComboBox.getSelectionModel().select(new DictionaryItemModel(dto.getSchool().getSchoolId(), dto.getSchool().getName()));
        if (ActionType.SHOW_ACTION.equals(actionType)) {
            schoolComboBox.setOnShown(show -> schoolComboBox.hide());
        }
    }

    protected TeacherRequestDto mapToTeacherRequestPojo() {
        TeacherRequestDto pojo = new TeacherRequestDto();
        pojo.setAddress(addressTextField.getText());
        pojo.setCity(cityTextField.getText());
        pojo.setPesel(peselTextField.getText());
        pojo.setRole(roleTextField.getText());
        pojo.setEmail(emailTextField.getText());
        pojo.setFirstName(firstNameTextField.getText());
        pojo.setLastName(lastNameTextField.getText());
        pojo.setPhoneNumber(phoneNumberTextField.getText());
        pojo.setPostalCode(postalCodeTextField.getText());
        pojo.setUsername(usernameTextField.getText());
        pojo.setSchoolId(schoolComboBox.getValue().getId());
        String password = UUID.randomUUID().toString();
        // TODO, ten print bedzie do zmiany, haslo bedzie wysylane mailem
        System.out.println("Random uuid: " + password);
        pojo.setPassword(password);
        return pojo;
    }
}
