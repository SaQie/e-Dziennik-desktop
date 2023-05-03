package pl.edziennik.client.controller.teacher.grades;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.controller.column.teacher.TeacherTableViewControllerMaker;
import pl.edziennik.client.common.model.student.StudentSpecificSubjectGradeModel;
import pl.edziennik.client.common.model.teacher.TeacherAllStudentGradesForSpecificSubjectModel;
import pl.edziennik.client.common.model.teacher.TeacherSubjectsModel;
import pl.edziennik.client.controller.teacher.grades.actions.AddGradeToStudentAction;
import pl.edziennik.client.controller.teacher.grades.actions.AddGradesToAllStudentsAction;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.TableSelectionMode;
import pl.edziennik.client.core.contextmenu.ContextMenuAction;
import pl.edziennik.client.core.contextmenu.ContextMenuActionBuilder;
import pl.edziennik.client.core.contextmenu.ContextMenuActionExecutorMode;
import pl.edziennik.client.rest.dto.student.StudentSpecificSubjectGradeDto;
import pl.edziennik.client.util.NodeUtils;

import java.util.List;

public class TeacherGradesManagmentController extends AbstractController {

    @FXML
    private Button exitButton;

    @FXML
    private TableView<TeacherAllStudentGradesForSpecificSubjectModel> tableView;

    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(exitButton);
        initializeContextMenuActions();
    }

    private void initializeContextMenuActions() {
        ContextMenuActionBuilder.builder()
                .addAction(
                        new ContextMenuAction(
                                ResourceConst.ADD_GRADE_ACTION_NAME.value(),
                                new AddGradeToStudentAction(),
                                ResourceConst.ADD_GRADE_ACTION_ICON.value(),
                                ContextMenuActionExecutorMode.CURRENT_ROW
                        ).assignToMenuButton(true)
                )
                .addAction(
                        new ContextMenuAction(
                                ResourceConst.ADD_GRADES_ACTION_NAME.value(),
                                new AddGradesToAllStudentsAction(),
                                ResourceConst.ADD_GRADES_ACTION_ICON.value(),
                                ContextMenuActionExecutorMode.NO_ROW
                        ).assignToMenuButton(true)
                ).build(tableView, menuButton);
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableViewPlaceHolder(tableView);
        NodeUtils.setTableSelectOption(tableView, TableSelectionMode.SINGLE);
        NodeUtils.setColumnConfigurationShortcut(tableView);
    }

    @Override
    protected void setTableColumns() {
        initializeTableColumns();
    }


    @Override
    protected Stage getActualStage() {
        return (Stage) refreshButton.getScene().getWindow();
    }

    public void fetchSceneData(List<StudentSpecificSubjectGradeDto> dtos) {
        List<TeacherAllStudentGradesForSpecificSubjectModel> specificSubjectModels =
                TeacherAllStudentGradesForSpecificSubjectModel.mapToModel(dtos);
        ObservableList<TeacherAllStudentGradesForSpecificSubjectModel> items =
                FXCollections.observableList(specificSubjectModels);
        tableView.setItems(items);
        tableView.refresh();
    }

    private void initializeTableColumns() {
        TeacherTableViewControllerMaker.TeacherSpecificSubjectAllStudentGradesColumnBuilder builder = TeacherTableViewControllerMaker.getTeacherSpecificSubjectAllStudentGradesColumnBuilder()
                .withSelectColumn(true)
                .withIdentifierColumn(true)
                .withSubjectIdentifierColumn(true)
                .withSubjectNameColumn(true)
                .withStudentFullNameColumn(true)
                .withStudentGradesColumn(true);

        tableView.getColumns().addAll(builder.build());
    }
}
