package pl.edziennik.client.controller.admin.accounts.student;

import pl.edziennik.client.common.ActionType;
import pl.edziennik.client.rest.dto.student.StudentDto;
import pl.edziennik.client.rest.dto.student.StudentRequestDto;
import pl.edziennik.client.task.student.EditStudentTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;
import pl.edziennik.client.validator.student.StudentValidator;

public class AdminAccountsTabStudentsEditController extends AdminAccountsTabStudentActionAbstractController {

    private Long objectId;

    @Override
    protected void setSceneSettings() {
        super.setSceneSettings();
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, usernameTextField, firstNameTextField, lastNameTextField,
                addressTextField, postalCodeTextField, cityTextField,
                peselTextField, emailTextField, phoneNumberTextField, schoolClassComboBox, schoolComboBox);
        NodeUtils.setTextFieldAsNumbersOnly(peselTextField, phoneNumberTextField);
    }

    @Override
    protected void setSceneValidators() {
        initializeValidators();
    }

    @Override
    protected void createActions() {
        super.createActions();
        initializeSaveButtonAction();
    }

    private void initializeValidators() {
        StudentValidator.builder()
                .withCityValidator(cityTextField)
                .withEmailValidator(emailTextField)
                .withFirstNameValidator(firstNameTextField)
                .withLastNameValidator(lastNameTextField)
                .withPeselValidator(peselTextField)
                .withUsernameValidator(usernameTextField)
                .withAddressValidator(addressTextField)
                .withPostalCodeValidator(postalCodeTextField)
                .withCorrectRoleField(roleTextField)
                .withPhoneNumberValidator(phoneNumberTextField)
                .withNotEmptySchoolClassComboBox(schoolClassComboBox)
                .withNotEmptySchoolComboBox(schoolComboBox)
                .build();

    }

    @Override
    protected void loadStageFields(StudentDto studentDto, ActionType actionType) {
        super.loadStageFields(studentDto, actionType);
        this.objectId = studentDto.getId();
    }

    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            StudentRequestDto schoolPojo = mapToStudentRequestPojo();
            ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new EditStudentTask(schoolPojo, objectId), (response) -> {
                AdminAccountsTabStudentsTabController controller = AdminAccountsTabStudentsTabController.getInstance();
                controller.refreshButton.fire();
                NodeUtils.closeCurrentStage(getActualStage());
                dialogFactory.createSuccessInformationDialog(null);
            }));
        });
    }
}
