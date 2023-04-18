package pl.edziennik.client.controller.admin.accounts.teacher;

import pl.edziennik.client.common.Role;
import pl.edziennik.client.controller.model.admin.TeacherListModel;
import pl.edziennik.client.rest.dto.teacher.TeacherRequestDto;
import pl.edziennik.client.task.teacher.AddTeacherTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.validator.teacher.TeacherValidator;

public class AdminAccountsTabTeachersAddController extends AdminAccountsTabTeacherActionAbstractController {


    @Override
    protected void setSceneSettings() {
        super.setSceneSettings();
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, usernameTextField, firstNameTextField, lastNameTextField,
                addressTextField, postalCodeTextField, cityTextField,
                peselTextField, phoneNumberTextField, emailTextField, schoolComboBox);
    }

    @Override
    protected void fetchStageData() {
        super.fetchStageData();
        roleTextField.setText(Role.ROLE_TEACHER.name());
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

    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            TeacherRequestDto pojo = mapToTeacherRequestPojo();
            progressFactory.createLittleProgressBar(new AddTeacherTask(pojo), (response) -> {
                AdminAccountsTabTeachersTabController teachersController = AdminAccountsTabTeachersTabController.getInstance();
                teachersController.addItem(TeacherListModel.mapPojoToModel(response));
                NodeUtils.closeCurrentStage(getActualStage());
                NodeUtils.showSuccessOperationToast();
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
