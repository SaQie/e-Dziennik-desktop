package pl.edziennik.client.controller.auth;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.controller.admin.accounts.parent.AdminAccountsTabParentsTabController;
import pl.edziennik.client.controller.admin.accounts.student.AdminAccountsTabStudentsTabController;
import pl.edziennik.client.controller.model.admin.SchoolClassComboBoxItem;
import pl.edziennik.client.controller.model.admin.SchoolComboBoxItem;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;
import pl.edziennik.client.rest.dto.parent.ParentRequestDto;
import pl.edziennik.client.rest.dto.student.StudentRequestDto;
import pl.edziennik.client.task.config.LoadConfigurationsTask;
import pl.edziennik.client.task.parent.AddNewParentTask;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.task.schoolclass.LoadSchoolClassesTask;
import pl.edziennik.client.task.student.AddStudentTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.validator.student.StudentValidator;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

class AbstractAuthorizationController extends AbstractController {

    private Long idSchoolFromConfiguration;

    @FXML
    protected ComboBox<SchoolClassComboBoxItem> schoolClassComboBox;

    @FXML
    protected TextField usernameTextField, firstNameTextField, lastNameTextField, addressTextField, postalCodeTextField, cityTextField,
            peselTextField, phoneNumberTextField, emailTextField;

    @Override
    protected void createActions() {
        initializeSaveButtonAction();
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, usernameTextField, firstNameTextField, lastNameTextField, addressTextField, postalCodeTextField, cityTextField,
                peselTextField, phoneNumberTextField, emailTextField, schoolClassComboBox);
        NodeUtils.setTextFieldAsNumbersOnly(peselTextField, phoneNumberTextField);
    }

    @Override
    protected void setSceneValidators() {
        initializeValidators();
    }

    @Override
    protected void fetchStageData() {
        fetchSchoolClassComboBoxItems();
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }


    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            StudentRequestDto studentPojo = mapToStudentRequestPojo();
            progressFactory.createLittleProgressBar(new AddStudentTask(studentPojo), (response) -> {
                cancelButton.fire();
                dialogFactory.createSuccessInformationDialog(null);
            });
        });
    }

    private StudentRequestDto mapToStudentRequestPojo() {
        StudentRequestDto studentPojo = new StudentRequestDto();
        studentPojo.setCity(cityTextField.getText());
        studentPojo.setAddress(addressTextField.getText());
        studentPojo.setEmail(emailTextField.getText());
        studentPojo.setPostalCode(postalCodeTextField.getText());
        studentPojo.setFirstName(firstNameTextField.getText());
        studentPojo.setLastName(lastNameTextField.getText());
        studentPojo.setUsername(usernameTextField.getText());
        studentPojo.setPhoneNumber(phoneNumberTextField.getText());
        studentPojo.setIdSchool(idSchoolFromConfiguration);
        studentPojo.setIdSchoolClass(schoolClassComboBox.getValue().getId().getValue());
        studentPojo.setPesel(peselTextField.getText());
        String password = UUID.randomUUID().toString();
        // TODO, ten print bedzie do zmiany, haslo bedzie wysylane mailem
        System.out.println("Random uuid: " + password);
        studentPojo.setPassword(password);
        return studentPojo;
    }


    private void fetchSchoolClassComboBoxItems() {
        progressFactory.createLittleProgressBar(new LoadConfigurationsTask(), (configurationList) -> {
            idSchoolFromConfiguration = configurationList.stream()
                    .filter(config -> config.getId().equals(3L))
                    .map(ConfigurationDto::getLongValue)
                    .findFirst()
                    .orElse(0L);

            progressFactory.createLittleProgressBar(new LoadSchoolClassesTask(idSchoolFromConfiguration), (response) -> {
                List<SchoolClassComboBoxItem> comboBoxItems = response.stream().map(SchoolClassComboBoxItem::new).toList();
                if (comboBoxItems.isEmpty()) {
                    schoolClassComboBox.setDisable(true);
                    return;
                }
                schoolClassComboBox.setDisable(false);
                schoolClassComboBox.setItems(FXCollections.observableList(comboBoxItems));
                schoolClassComboBox.getSelectionModel().selectFirst();
            });
        });


    }


    private void initializeValidators() {
        StudentValidator.builder()
                .withCityValidator(cityTextField)
                .withEmailValidator(emailTextField)
                .withFirstNameValidator(firstNameTextField)
                .withLastNameValidator(lastNameTextField)
                .withPeselValidator(peselTextField)
                .withPhoneNumberValidator(phoneNumberTextField)
                .withUsernameValidator(usernameTextField)
                .withAddressValidator(addressTextField)
                .withPostalCodeValidator(postalCodeTextField)
                .withNotEmptySchoolClassComboBox(schoolClassComboBox)
                .build();

    }

}
