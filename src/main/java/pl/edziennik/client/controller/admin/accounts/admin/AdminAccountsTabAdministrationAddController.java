package pl.edziennik.client.controller.admin.accounts.admin;

import pl.edziennik.client.controller.admin.accounts.student.AdminAccountsTabStudentsTabController;
import pl.edziennik.client.controller.model.admin.AdminListModel;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.rest.dto.student.StudentRequestDto;
import pl.edziennik.client.task.admin.AddAdminTask;
import pl.edziennik.client.task.student.AddStudentTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.validator.admin.AdminValidator;

import java.util.UUID;

public class AdminAccountsTabAdministrationAddController extends AdminAccountsTabAdministrationActionAbstractController {


    @Override
    protected void setSceneSettings() {
        super.setSceneSettings();
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, passwordField, emailTextField, nameTextField, roleTextField);
    }

    @Override
    protected void createActions() {
        super.createActions();
        initializeAddButtonAction();
    }


    @Override
    protected void setSceneValidators() {
        initializeValidators();
    }

    @Override
    protected void fetchStageData() {
        super.fetchStageData();
        passwordField.setText(UUID.randomUUID().toString());
    }

    private void initializeValidators() {
        AdminValidator.builder()
                .withEmailValidator(emailTextField)
                .withPasswordValidator(passwordField)
                .withUsernameValidator(nameTextField)
                .withRoleValidator(roleTextField)
                .build();
    }


    private void initializeAddButtonAction() {
        saveButton.setOnAction(button -> {
            AdminDto dto = mapToAdminDto();
            progressFactory.createLittleProgressBar(new AddAdminTask(dto), (response) -> {
                AdminAccountsTabAdministrationTabController adminController = AdminAccountsTabAdministrationTabController.getInstance();
                adminController.addItem(AdminListModel.mapPojoToModel(response));
                NodeUtils.closeCurrentStage(getActualStage());
                NodeUtils.showSuccessOperationToast();
            });
        });
    }

}
