package pl.edziennik.client.controller.admin.accounts.teacher;

import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.rest.dto.teacher.TeacherDto;
import pl.edziennik.client.rest.dto.teacher.TeacherRequestDto;
import pl.edziennik.client.task.teacher.EditTeacherTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.validator.teacher.TeacherValidator;

public class AdminAccountsTabTeachersEditController extends AdminAccountsTabTeacherActionAbstractController {

    private Long objectId;

    @Override
    protected void setSceneValidators() {
        initializeValidators();
    }

    @Override
    protected void createActions() {
        super.createActions();
        initializeSaveButtonAction();
    }

    @Override
    protected void setSceneSettings() {
        super.setSceneSettings();
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, usernameTextField, firstNameTextField, lastNameTextField,
                addressTextField, postalCodeTextField, cityTextField,
                peselTextField, phoneNumberTextField, emailTextField, schoolComboBox);
    }

    @Override
    protected void loadStageFields(TeacherDto dto, ActionType actionType) {
        super.loadStageFields(dto, actionType);
        this.objectId = dto.getTeacherId();
    }

    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            TeacherRequestDto pojo = mapToTeacherRequestPojo();
            progressFactory.createLittleProgressBar(new EditTeacherTask(pojo, objectId), (response) -> {
                AdminAccountsTabTeachersTabController teachersController = AdminAccountsTabTeachersTabController.getInstance();
                teachersController.refreshButton.fire();
                NodeUtils.closeCurrentStage(getActualStage());
                dialogFactory.createSuccessInformationDialog(null);
            });
        });
    }

    private void initializeValidators() {
        TeacherValidator.builder()
                .withPhoneNumberValidator(phoneNumberTextField)
                .withCorrectRoleField(roleTextField)
                .withEmailValidator(emailTextField)
                .withCityValidator(cityTextField)
                .withAddressValidator(addressTextField)
                .withPeselValidator(peselTextField)
                .withFirstNameValidator(firstNameTextField)
                .withPostalCodeValidator(postalCodeTextField)
                .withUsernameValidator(usernameTextField)
                .withLastNameValidator(lastNameTextField)
                .withNotEmptySchoolComboBox(schoolComboBox)
                .build();

    }
}
