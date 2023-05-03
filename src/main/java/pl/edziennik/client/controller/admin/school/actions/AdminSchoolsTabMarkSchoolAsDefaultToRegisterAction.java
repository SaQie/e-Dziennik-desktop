package pl.edziennik.client.controller.admin.schools.actions;

import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.core.contextmenu.ActionExecutor;
import pl.edziennik.client.core.contextmenu.MenuAction;
import pl.edziennik.client.rest.dto.config.SettingsValueDto;
import pl.edziennik.client.task.config.SaveConfigurationTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.Arrays;

public class AdminSchoolsTabMarkSchoolAsDefaultToRegisterAction extends MenuAction implements ActionExecutor {

    @Override
    public void executeOnCurrentRow(Long rowId, Object... parameters) {
        Long configurationId = (Long) parameters[0];
        boolean selectedQuestionYesOption = dialogFactory.createQuestionInformationDialog(ResourceConst.QUESTION_ASSIGN_SCHOOL_AS_DEFAULT_TO_REGISTER.value());
        if (selectedQuestionYesOption) {
            SettingsValueDto settingsValueDto = new SettingsValueDto(configurationId, rowId);
            progressFactory.createLittleProgressBar(new SaveConfigurationTask(Arrays.asList(settingsValueDto)), (response) -> {
                NodeUtils.showSuccessOperationToast();
            });
        }
    }

    @Override
    public void execute(Object... parameters) {
        // nothing to do
    }
}
