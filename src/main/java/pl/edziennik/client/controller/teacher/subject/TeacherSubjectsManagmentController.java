package pl.edziennik.client.controller.teacher.subject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.controller.column.teacher.TeacherTableViewControllerMaker;
import pl.edziennik.client.common.model.teacher.TeacherSubjectsModel;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesStringToLongConverter;
import pl.edziennik.client.controller.teacher.grades.TeacherGradesManagmentController;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.TableSelectionMode;
import pl.edziennik.client.core.contextmenu.ContextMenuAction;
import pl.edziennik.client.core.contextmenu.ContextMenuActionBuilder;
import pl.edziennik.client.rest.dto.subject.SubjectDto;
import pl.edziennik.client.task.teacher.LoadAllStudentsSpecificSubjectGradesTask;
import pl.edziennik.client.task.teacher.LoadTeacherSubjectsTask;
import pl.edziennik.client.util.NodeUtils;

import java.util.List;

public class TeacherSubjectsManagmentController extends AbstractController {

    @FXML
    private TableView<TeacherSubjectsModel> tableView;

    @FXML
    private Button manageButton;

    @Override
    protected void createActions() {
        initializeRefreshButtonAction();
        initializeManageButtonAction();
    }


    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableSelectOption(tableView, TableSelectionMode.SINGLE);
        NodeUtils.setTableViewPlaceHolder(tableView);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) showButton.getScene().getWindow();
    }

    @Override
    protected void setTableColumns() {
        initializeTableColumns();
    }

    private void initializeTableColumns() {
        TeacherTableViewControllerMaker.TeacherSubjectsColumnBuilder builder = TeacherTableViewControllerMaker.getTeacherSubjectsColumnBuilder()
                .withSelectColumn(true)
                .withSubjectIdentifierColumn(true)
                .withSubjectNameColumn(true)
                .withDescriptionColumn(true)
                .withSchoolClassNameColumn(true)
                .withTeacherNameColumn(true);

        tableView.getColumns().addAll(builder.build());
    }

    public boolean isTableDataEmpty() {
        return tableView.getItems().isEmpty();
    }

    public void fetchTabData(List<SubjectDto> dtos) {
        List<TeacherSubjectsModel> teacherSubjectsModels = TeacherSubjectsModel.mapToModel(dtos);
        ObservableList<TeacherSubjectsModel> items = FXCollections.observableList(teacherSubjectsModels);
        tableView.setItems(items);
        tableView.refresh();
    }

    private void initializeRefreshButtonAction() {
        refreshButton.setOnAction(button -> {
            Long superId = PropertiesLoader.readProperty(ResourceConst.PROPERTIES_LOADER_SUPER_ID_KEY.value(),
                    new PropertiesStringToLongConverter());

            progressFactory.createLittleProgressBar(new LoadTeacherSubjectsTask(superId),
                    this::fetchTabData);
        });
    }

    private void initializeManageButtonAction() {
        manageButton.setOnAction(button -> {
            Long subjectId = NodeUtils.getSelectedTableItemIdentifier(tableView);

            progressFactory.createLittleProgressBar(new LoadAllStudentsSpecificSubjectGradesTask(subjectId), (response) -> {
                TeacherGradesManagmentController controller = NodeUtils.openNewStageAboveWithController(
                        ResourceConst.TEACHER_GRADE_MANAGMENT_VIEW_ADDRESS.value(),
                        ResourceConst.TEACHER_GRADE_MANAGMENT_VIEW_TITLE.value(),
                        850, 650, manageButton, true);

                controller.fetchSceneData(response);

            });
        });
    }
}
