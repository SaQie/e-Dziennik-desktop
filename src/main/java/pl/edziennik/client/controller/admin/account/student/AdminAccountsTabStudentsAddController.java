package pl.edziennik.client.controller.admin.account.student;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.edziennik.client.common.model.admin.StudentListModel;
import pl.edziennik.client.rest.dto.student.StudentRequestDto;
import pl.edziennik.client.task.student.AddStudentTask;
import pl.edziennik.client.util.NodeUtils;
import pl.edziennik.client.validator.student.StudentValidator;

public class AdminAccountsTabStudentsAddController extends AdminAccountsTabStudentActionAbstractController {

    @Override
    protected void createActions() {
        super.createActions();
        initializeSaveButtonAction(saveButton);
    }

    @Override
    protected void setSceneValidators() {
        initializeValidators();
    }

    @Override
    protected void setSceneSettings() {
        super.setSceneSettings();
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, usernameTextField, firstNameTextField, lastNameTextField,
                addressTextField, postalCodeTextField, cityTextField,
                peselTextField, emailTextField, phoneNumberTextField, schoolClassComboBox, schoolComboBox);
        NodeUtils.setTextFieldAsNumbersOnly(peselTextField, phoneNumberTextField);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }

    private void initializeSaveButtonAction(Button saveButton) {
        saveButton.setOnAction(button -> {
            StudentRequestDto studentPojo = mapToStudentRequestPojo();
            progressFactory.createLittleProgressBar(new AddStudentTask(studentPojo), (response) -> {
                AdminAccountsTabStudentsTabController studentController = AdminAccountsTabStudentsTabController.getInstance();
                studentController.addItem(StudentListModel.mapPojoToModel(response));
                NodeUtils.closeCurrentStage(getActualStage());
                NodeUtils.showSuccessOperationToast();
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
                .withCorrectRoleField(roleTextField)
                .withNotEmptySchoolClassComboBox(schoolClassComboBox)
                .withNotEmptySchoolComboBox(schoolComboBox)
                .build();

    }
}
