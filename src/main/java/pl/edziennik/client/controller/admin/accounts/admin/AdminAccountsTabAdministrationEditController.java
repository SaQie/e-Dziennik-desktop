package pl.edziennik.client.controller.admin.accounts.admin;

import pl.edziennik.client.common.ActionType;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.task.admin.EditAdminTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;
import pl.edziennik.client.validator.admin.AdminValidator;

public class AdminAccountsTabAdministrationEditController extends AdminAccountsTabAdministrationActionAbstractController {


    private Long objectId;

    @Override
    protected void setSceneSettings() {
        super.setSceneSettings();
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, emailTextField, nameTextField);
    }

    @Override
    protected void createActions() {
        super.createActions();
        initializeSaveButtonAction();
    }

    @Override
    protected void setSceneValidators() {
        super.setSceneValidators();
        initializeValidators();
    }

    @Override
    protected void loadStageFields(AdminDto dto, ActionType type) {
        super.loadStageFields(dto, type);
        this.objectId = dto.getId();
    }

    private void initializeValidators() {
        AdminValidator.builder()
                .withEmailValidator(emailTextField)
                .withUsernameValidator(nameTextField)
                .withRoleValidator(roleTextField)
                .build();

    }

    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            AdminDto dto = mapToAdminDto();
            ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new EditAdminTask(objectId, dto), (response) -> {
                AdminAccountsTabAdministrationTabController controller = AdminAccountsTabAdministrationTabController.getInstance();
                controller.refreshButton.fire();
                NodeUtils.closeCurrentStage(getActualStage());
                dialogFactory.createSuccessInformationDialog(null);
            }));
        });
    }
}
