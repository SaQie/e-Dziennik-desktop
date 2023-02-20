package pl.edziennik.client.controller.admin.accounts.student;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.edziennik.client.common.Role;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.rest.pojo.StudentPojo;
import pl.edziennik.client.rest.pojo.StudentRequestPojo;
import pl.edziennik.client.task.student.AddStudentTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.validator.student.AddStudentValidator;

public class AdminAccountsTabStudentsAddController extends AdminAccountsTabStudentActionAbstractController {

    @Override
    protected void createActions() {
        super.createActions();
        initializeSaveButtonAction(saveButton);
    }

    @Override
    protected void fetchStageData() {
        super.fetchStageData();
        roleTextField.setText(Role.ROLE_STUDENT.name());
    }

    @Override
    protected void setSceneSettings() {
        initializeValidators();
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, usernameTextField, firstNameTextField, lastNameTextField,
                addressTextField, postalCodeTextField, cityTextField,
                peselTextField, parentFirstNameTextField, parentLastNameTextField,
                parentPhoneNumberTextField, emailTextField,schoolClassComboBox, schoolComboBox);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }

    private void initializeSaveButtonAction(Button saveButton) {
        saveButton.setOnAction(button -> {
            StudentRequestPojo studentPojo = mapToStudentRequestPojo();
            progressFactory.createLittleProgressBar(new AddStudentTask(studentPojo), (response) -> {
                AdminAccountsTabStudentsTabController studentController = AdminAccountsTabStudentsTabController.getInstance();
                studentController.addItem(StudentListModel.mapPojoToModel(response));
                dialogFactory.createSuccessInformationDialog(null);
            });
        });
    }

    private void initializeValidators() {
        AddStudentValidator.builder()
                .withCityValidator(cityTextField)
                .withEmailValidator(emailTextField)
                .withFirstNameValidator(firstNameTextField)
                .withFirstNameValidator(parentFirstNameTextField)
                .withLastNameValidator(lastNameTextField)
                .withLastNameValidator(parentLastNameTextField)
                .withPeselValidator(peselTextField)
                .withUsernameValidator(usernameTextField)
                .withAddressValidator(addressTextField)
                .withPostalCodeValidator(postalCodeTextField)
                .withPhoneNumberValidator(parentPhoneNumberTextField)
                .withCorrectPeselField(roleTextField)
                .withNotEmptySchoolClassComboBox(schoolClassComboBox)
                .withNotEmptySchoolComboBox(schoolComboBox)
                .build();

    }
}
