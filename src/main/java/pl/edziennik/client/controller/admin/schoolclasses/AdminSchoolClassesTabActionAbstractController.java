package pl.edziennik.client.controller.admin.schoolclasses;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.DictionaryItemModel;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassRequestDto;
import pl.edziennik.client.utils.NodeUtils;

abstract class AdminSchoolClassesTabActionAbstractController extends AbstractController {

    @FXML
    protected ComboBox<DictionaryItemModel> schoolComboBox, supervisingTeacherComboBox;

    @FXML
    protected TextField classNameTextField;


    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
    }

    @Override
    protected void setSceneSettings() {

    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }


    protected SchoolClassRequestDto mapToSchoolClassRequestDto() {
        String className = classNameTextField.getText();
        Long idSchool = schoolComboBox.getValue().getId();
        Long idSupervisingTeacher = null;
        if (supervisingTeacherComboBox.getValue() != null) {
            idSupervisingTeacher = supervisingTeacherComboBox.getValue().getId();
        }
        return new SchoolClassRequestDto(className, idSupervisingTeacher, idSchool);
    }

    protected void loadStageData(SchoolClassDto dto, ActionType actionType){
        this.classNameTextField.setText(dto.getClassName());
        this.schoolComboBox.getSelectionModel().select(
                new DictionaryItemModel(dto.getSchool().getId(), dto.getSchool().getName()));
        if (dto.getSupervisingTeacher() != null) {
            this.supervisingTeacherComboBox.getSelectionModel().select(
                    new DictionaryItemModel(dto.getSupervisingTeacher().getId(), dto.getSupervisingTeacher().getFullName()));
        }
        if (ActionType.SHOW_ACTION.equals(actionType)){
            supervisingTeacherComboBox.setOnShown(show -> supervisingTeacherComboBox.hide());
            schoolComboBox.setOnShown(show -> schoolComboBox.hide());
        }
    }
}
