package pl.edziennik.client.controller.admin.accounts.student;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.controller.model.admin.SchoolClassComboBoxItem;
import pl.edziennik.client.controller.model.admin.SchoolComboBoxItem;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.pojo.SimpleSchoolClassPojo;
import pl.edziennik.client.rest.pojo.SimpleSchoolPojo;
import pl.edziennik.client.rest.pojo.StudentPojo;
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
    }

    @Override
    protected void setSceneSettings() {

    }

    @Override
    protected Stage getActualStage() {
        return null;
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
        if (schoolComboBox.getItems().isEmpty()) {
            progressFactory.createLittleProgressBar(new LoadSchoolsTask(), (response) -> {
                List<SchoolComboBoxItem> comboBoxItems = response.stream().map(SchoolComboBoxItem::new).toList();
                schoolComboBox.setItems(FXCollections.observableList(comboBoxItems));
            });
        }
    }

    protected StudentPojo mapToStudentPojo() {
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setCity(cityTextField.getText());
        studentPojo.setAddress(addressTextField.getText());
        studentPojo.setEmail(emailTextField.getText());
        studentPojo.setPostalCode(postalCodeTextField.getText());
        studentPojo.setFirstName(firstNameTextField.getText());
        studentPojo.setLastName(lastNameTextField.getText());
        studentPojo.setUsername(usernameTextField.getText());
        studentPojo.setSchool(new SimpleSchoolPojo(schoolComboBox.getValue().getId().getValue(), schoolComboBox.getValue().getName().getValue()));
        studentPojo.setSchoolClass(new SimpleSchoolClassPojo(schoolClassComboBox.getValue().getId().getValue(), schoolClassComboBox.getValue().getClassName().getValue()));
        studentPojo.setParentFirstName(parentFirstNameTextField.getText());
        studentPojo.setParentLastName(parentLastNameTextField.getText());
        studentPojo.setParentPhoneNumber(parentPhoneNumberTextField.getText());
        studentPojo.setPesel(peselTextField.getText());
        String password = UUID.randomUUID().toString();
        System.out.println("random uuid: " + password);
        studentPojo.setPassword(password);
        return studentPojo;
    }

}
