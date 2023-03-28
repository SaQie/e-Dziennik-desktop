package pl.edziennik.client.controller.admin.schoolclasses;

import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.controller.model.admin.SchoolClassListModel;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassRequestDto;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.task.schoolclass.AddSchoolClassTask;
import pl.edziennik.client.task.schoolclass.EditSchoolClassTask;
import pl.edziennik.client.task.teacher.LoadTeachersTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.validator.schoolclass.SchoolClassValidator;

public class AdminSchoolClassesTabEditSchoolClassController extends AdminSchoolClassesTabActionAbstractController{

    private volatile Long idSchoolClass;

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
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, schoolComboBox, classNameTextField);
    }

    @Override
    protected void createDictionaries() {
        NodeUtils.initializeDictionary(LoadTeachersTask.class,supervisingTeacherComboBox);
        NodeUtils.initializeDictionary(LoadSchoolsTask.class, schoolComboBox);
    }

    @Override
    protected void loadStageData(SchoolClassDto dto, ActionType actionType) {
        super.loadStageData(dto, actionType);
        this.idSchoolClass = dto.getId();
    }

    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            SchoolClassRequestDto dto = mapToSchoolClassRequestDto();
            progressFactory.createLittleProgressBar(new EditSchoolClassTask(idSchoolClass,dto), (response) -> {
                AdminSchoolClassesTabController controller = AdminSchoolClassesTabController.getInstance();
                controller.addItem(SchoolClassListModel.mapToModel(response));
                controller.refreshButton.fire();
                NodeUtils.closeCurrentStage(getActualStage());
                dialogFactory.createSuccessInformationDialog(null);
            });
        });
    }


    private void initializeValidators() {
        SchoolClassValidator.builder()
                .withNotEmptySchoolComboBox(schoolComboBox)
                .withNameValidator(classNameTextField)
                .withAlwaysCorrectSupervisingTeacherComboBox(supervisingTeacherComboBox)
                .build();
    }

}
