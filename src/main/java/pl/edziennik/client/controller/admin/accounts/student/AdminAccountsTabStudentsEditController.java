package pl.edziennik.client.controller.admin.accounts.student;

import pl.edziennik.client.common.ActionType;
import pl.edziennik.client.controller.admin.schools.AdminSchoolsTabController;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.rest.pojo.StudentPojo;
import pl.edziennik.client.rest.pojo.StudentRequestPojo;
import pl.edziennik.client.task.school.EditSchoolTask;
import pl.edziennik.client.task.student.EditStudentTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;
import pl.edziennik.client.validator.student.AddStudentValidator;

public class AdminAccountsTabStudentsEditController extends AdminAccountsTabStudentActionAbstractController {

    private Long objectId;

    @Override
    protected void setSceneSettings() {
        initializeValidators();
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, usernameTextField, firstNameTextField, lastNameTextField,
                addressTextField, postalCodeTextField, cityTextField,
                peselTextField, parentFirstNameTextField, parentLastNameTextField,
                parentPhoneNumberTextField, emailTextField, schoolClassComboBox, schoolComboBox);
        NodeUtils.setTextFieldAsNumbersOnly(peselTextField, parentPhoneNumberTextField);
    }

    @Override
    protected void createActions() {
        super.createActions();
        initializeSaveButtonAction();
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

    @Override
    protected void loadStageFields(StudentPojo studentPojo, ActionType actionType) {
        super.loadStageFields(studentPojo, actionType);
        this.objectId = studentPojo.getId();
    }

    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            StudentRequestPojo schoolPojo = mapToStudentRequestPojo();
            ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new EditStudentTask(schoolPojo, objectId), (response) -> {
                AdminAccountsTabStudentsTabController controller = AdminAccountsTabStudentsTabController.getInstance();
                controller.refreshButton.fire();
                NodeUtils.closeCurrentStage(getActualStage());
                dialogFactory.createSuccessInformationDialog(null);
            }));
        });
    }
}
