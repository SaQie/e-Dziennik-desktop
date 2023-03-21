package pl.edziennik.client.controller.admin.accounts.student;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.ActionType;
import pl.edziennik.client.common.Role;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.DictionaryItemModel;
import pl.edziennik.client.rest.dto.student.StudentDto;
import pl.edziennik.client.rest.dto.student.StudentRequestDto;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.task.schoolclass.LoadSchoolClassesTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.UUID;

class AdminAccountsTabStudentActionAbstractController extends AbstractController {

    @FXML
    protected ComboBox<DictionaryItemModel> schoolComboBox;

    @FXML
    protected ComboBox<DictionaryItemModel> schoolClassComboBox;

    @FXML
    protected TextField usernameTextField, firstNameTextField, lastNameTextField, addressTextField, postalCodeTextField, cityTextField,
            peselTextField, phoneNumberTextField, emailTextField, roleTextField;


    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);

    }

    @Override
    protected void fetchStageData() {
        roleTextField.setText(Role.ROLE_STUDENT.name());
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.initializeDictionary(LoadSchoolsTask.class, schoolComboBox);
        initializeSchoolClassComboBox();
    }

    private void initializeSchoolClassComboBox() {
        schoolComboBox.valueProperty().addListener(value -> {
            if (schoolComboBox.getValue() != null) {
                DictionaryItemModel itemModel = schoolComboBox.getValue();
                schoolClassComboBox.setDisable(false);
                NodeUtils.initializeDictionary(LoadSchoolClassesTask.class, schoolClassComboBox, itemModel.getId());
            } else {
                schoolClassComboBox.setDisable(true);
            }
        });
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }

    protected void loadStageFields(StudentDto studentDto, ActionType actionType) {
        usernameTextField.setText(studentDto.getUsername());
        firstNameTextField.setText(studentDto.getFirstName());
        lastNameTextField.setText(studentDto.getLastName());
        roleTextField.setText(studentDto.getRole());
        addressTextField.setText(studentDto.getAddress());
        cityTextField.setText(studentDto.getCity());
        postalCodeTextField.setText(studentDto.getPostalCode());
        peselTextField.setText(studentDto.getPesel());
        emailTextField.setText(studentDto.getEmail());
        phoneNumberTextField.setText(studentDto.getPhoneNumber());
        schoolComboBox.getSelectionModel().select(new DictionaryItemModel(studentDto.getId(), studentDto.getFullName()));
        schoolClassComboBox.getSelectionModel().select(new DictionaryItemModel(studentDto.getSchoolClass().getId(), studentDto.getSchoolClass().getClassName()));
        if (ActionType.SHOW_ACTION.equals(actionType)) {
            schoolComboBox.setOnShown(show -> schoolComboBox.hide());
            schoolClassComboBox.setOnShown(show -> schoolClassComboBox.hide());
        }
    }

    protected StudentRequestDto mapToStudentRequestPojo() {
        StudentRequestDto studentPojo = new StudentRequestDto();
        studentPojo.setCity(cityTextField.getText());
        studentPojo.setAddress(addressTextField.getText());
        studentPojo.setEmail(emailTextField.getText());
        studentPojo.setPostalCode(postalCodeTextField.getText());
        studentPojo.setFirstName(firstNameTextField.getText());
        studentPojo.setLastName(lastNameTextField.getText());
        studentPojo.setUsername(usernameTextField.getText());
        studentPojo.setPhoneNumber(phoneNumberTextField.getText());
        studentPojo.setIdSchool(schoolComboBox.getValue().getId());
        studentPojo.setIdSchoolClass(schoolClassComboBox.getValue().getId());
        studentPojo.setPesel(peselTextField.getText());
        String password = UUID.randomUUID().toString();
        // TODO, ten print bedzie do zmiany, haslo bedzie wysylane mailem
        System.out.println("Random uuid: " + password);
        studentPojo.setPassword(password);
        return studentPojo;
    }

}
