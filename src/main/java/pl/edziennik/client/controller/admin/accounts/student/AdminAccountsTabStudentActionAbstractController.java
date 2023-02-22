package pl.edziennik.client.controller.admin.accounts.student;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.ActionType;
import pl.edziennik.client.common.Role;
import pl.edziennik.client.controller.model.admin.SchoolClassComboBoxItem;
import pl.edziennik.client.controller.model.admin.SchoolComboBoxItem;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.pojo.StudentPojo;
import pl.edziennik.client.rest.pojo.StudentRequestPojo;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.task.schoolclass.LoadSchoolClassesTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.List;
import java.util.UUID;

class AdminAccountsTabStudentActionAbstractController extends AbstractController {

    @FXML
    protected ComboBox<SchoolComboBoxItem> schoolComboBox;

    @FXML
    protected ComboBox<SchoolClassComboBoxItem> schoolClassComboBox;

    @FXML
    protected TextField usernameTextField, firstNameTextField, lastNameTextField, addressTextField, postalCodeTextField, cityTextField,
            peselTextField, parentFirstNameTextField, parentLastNameTextField, parentPhoneNumberTextField, emailTextField, roleTextField;


    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);

    }

    @Override
    protected void fetchStageData() {
        fetchSchoolComboBoxItems();
        fetchSchoolClassComboBoxItems();
        roleTextField.setText(Role.ROLE_STUDENT.name());
    }

    @Override
    protected void setSceneSettings() {

    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }

    protected void loadStageFields(StudentPojo studentPojo, ActionType actionType) {
        usernameTextField.setText(studentPojo.getUsername());
        firstNameTextField.setText(studentPojo.getFirstName());
        lastNameTextField.setText(studentPojo.getLastName());
        parentFirstNameTextField.setText(studentPojo.getParentFirstName());
        parentLastNameTextField.setText(studentPojo.getParentLastName());
        parentPhoneNumberTextField.setText(studentPojo.getParentPhoneNumber());
        roleTextField.setText(studentPojo.getRole());
        addressTextField.setText(studentPojo.getAddress());
        cityTextField.setText(studentPojo.getCity());
        postalCodeTextField.setText(studentPojo.getPostalCode());
        peselTextField.setText(studentPojo.getPesel());
        emailTextField.setText(studentPojo.getEmail());
        schoolComboBox.getSelectionModel().select(new SchoolComboBoxItem(studentPojo.getSchool()));
        schoolClassComboBox.getSelectionModel().select(new SchoolClassComboBoxItem(studentPojo.getSchoolClass()));
        if (ActionType.SHOW_ACTION.equals(actionType)) {
            schoolComboBox.setOnShown(show -> schoolComboBox.hide());
            schoolClassComboBox.setOnShown(show -> schoolClassComboBox.hide());
        }
    }

    private void fetchSchoolClassComboBoxItems() {
        schoolComboBox.valueProperty().addListener((value) -> {
            if (schoolComboBox.getValue() != null) {
                Long idSchool = schoolComboBox.getValue().getId().getValue();
                progressFactory.createLittleProgressBar(new LoadSchoolClassesTask(idSchool), (response) -> {
                    List<SchoolClassComboBoxItem> comboBoxItems = response.stream().map(SchoolClassComboBoxItem::new).toList();
                    if (comboBoxItems.isEmpty()) {
                        schoolClassComboBox.setDisable(true);
                        return;
                    }
                    schoolClassComboBox.setDisable(false);
                    schoolClassComboBox.setItems(FXCollections.observableList(comboBoxItems));
                    schoolClassComboBox.getSelectionModel().selectFirst();
                });
            }
            if (schoolClassComboBox.getValue() != null) {
                schoolClassComboBox.getSelectionModel().select(null);
            }
        });
    }

    private void fetchSchoolComboBoxItems() {
        progressFactory.createLittleProgressBar(new LoadSchoolsTask(), (response) -> {
            List<SchoolComboBoxItem> comboBoxItems = response.stream().map(SchoolComboBoxItem::new).toList();
            schoolComboBox.setItems(FXCollections.observableList(comboBoxItems));
        });
    }

    protected StudentRequestPojo mapToStudentRequestPojo() {
        StudentRequestPojo studentPojo = new StudentRequestPojo();
        studentPojo.setCity(cityTextField.getText());
        studentPojo.setAddress(addressTextField.getText());
        studentPojo.setEmail(emailTextField.getText());
        studentPojo.setPostalCode(postalCodeTextField.getText());
        studentPojo.setFirstName(firstNameTextField.getText());
        studentPojo.setLastName(lastNameTextField.getText());
        studentPojo.setUsername(usernameTextField.getText());
        studentPojo.setIdSchool(schoolComboBox.getValue().getId().getValue());
        studentPojo.setIdSchoolClass(schoolClassComboBox.getValue().getId().getValue());
        studentPojo.setParentFirstName(parentFirstNameTextField.getText());
        studentPojo.setParentLastName(parentLastNameTextField.getText());
        studentPojo.setParentPhoneNumber(parentPhoneNumberTextField.getText());
        studentPojo.setPesel(peselTextField.getText());
        String password = UUID.randomUUID().toString();
        // TODO, ten print bedzie do zmiany, haslo bedzie wysylane mailem
        System.out.println("Random uuid: " + password);
        studentPojo.setPassword(password);
        return studentPojo;
    }

}
