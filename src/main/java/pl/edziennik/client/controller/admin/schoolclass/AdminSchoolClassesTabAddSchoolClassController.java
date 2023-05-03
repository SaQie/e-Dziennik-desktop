package pl.edziennik.client.controller.admin.schoolclass;

import pl.edziennik.client.common.model.admin.SchoolClassListModel;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassRequestDto;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.task.schoolclass.AddSchoolClassTask;
import pl.edziennik.client.task.teacher.LoadTeachersTask;
import pl.edziennik.client.util.NodeUtils;
import pl.edziennik.client.validator.schoolclass.SchoolClassValidator;

public class AdminSchoolClassesTabAddSchoolClassController extends AdminSchoolClassesTabActionAbstractController{

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

    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            SchoolClassRequestDto dto = mapToSchoolClassRequestDto();
            progressFactory.createLittleProgressBar(new AddSchoolClassTask(dto), (response) -> {
                AdminSchoolClassesTabController controller = AdminSchoolClassesTabController.getInstance();
                controller.addItem(SchoolClassListModel.mapToModel(response));
                NodeUtils.closeCurrentStage(getActualStage());
                NodeUtils.showSuccessOperationToast();
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
