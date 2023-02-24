package pl.edziennik.client.controller.admin.accounts.student;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.rest.dto.student.StudentRequestDto;
import pl.edziennik.client.task.student.AddStudentTask;
import pl.edziennik.client.utils.NodeUtils;
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
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, usernameTextField, firstNameTextField, lastNameTextField,
                addressTextField, postalCodeTextField, cityTextField,
                peselTextField, parentFirstNameTextField, parentLastNameTextField,
                parentPhoneNumberTextField, emailTextField,schoolClassComboBox, schoolComboBox);
        NodeUtils.setTextFieldAsNumbersOnly(peselTextField, parentPhoneNumberTextField);
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
                dialogFactory.createSuccessInformationDialog(null);
            });
        });
    }

    private void initializeValidators() {
        StudentValidator.builder()
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
                .withCorrectRoleField(roleTextField)
                .withNotEmptySchoolClassComboBox(schoolClassComboBox)
                .withNotEmptySchoolComboBox(schoolComboBox)
                .build();

    }
}
