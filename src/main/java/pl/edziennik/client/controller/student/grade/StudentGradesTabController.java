package pl.edziennik.client.controller.student.grade;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.controller.column.student.StudentTableViewControllerMaker;
import pl.edziennik.client.common.model.student.StudentSubjectModel;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesStringToLongConverter;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.TableSelectionMode;
import pl.edziennik.client.rest.dto.student.StudentSubjectsGradeDto;
import pl.edziennik.client.task.student.LoadAllStudentGradesTask;
import pl.edziennik.client.util.NodeUtils;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class StudentGradesTabController extends AbstractController {

    @FXML
    private TableView<StudentSubjectModel> tableView;

    @Override
    protected void createActions() {
        initializeShowGradesAction();
        initializeRefreshButtonAction();
    }



    @Override
    protected void setTableColumns() {
        initializeTableColumns();
    }

    private void initializeTableColumns() {
        StudentTableViewControllerMaker.StudentAllSubjectGradesColumnBuilder builder = StudentTableViewControllerMaker.getStudentAllSubjectGradesColumnBuilder()
                .withSelectColumn()
                .withSubjectIdentifierColumn(true)
                .withSubjectGradesColumn(true)
                .withSubjectNameColumn(true);


        tableView.getColumns().addAll(builder.build());
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableViewPlaceHolder(tableView);
        NodeUtils.setTableSelectOption(tableView, TableSelectionMode.SINGLE);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) showButton.getScene().getWindow();
    }

    public void fetchTabData(final StudentSubjectsGradeDto dto) {
        List<StudentSubjectModel> studentGradeModels = StudentSubjectModel.mapDtoToModel(dto.getSubjects());
        ObservableList<StudentSubjectModel> items = FXCollections.observableList(studentGradeModels);
        tableView.setItems(items);
        tableView.refresh();
    }

    public boolean isTableDataEmpty() {
        return tableView.getItems().isEmpty();
    }

    private void initializeShowGradesAction() {
        showButton.setOnAction(button -> {
            StudentSubjectModel selectedTableItem = NodeUtils.getSelectedTableItem(tableView);
            StudentShowGradesOptionController controller = NodeUtils.openNewStageAboveWithController(DASHBOARD_STUDENT_SPECIFIC_SUBJECT_GRADES_VIEW_ADDRESS.value(), SHOW_GRADES_TITLE_MESSAGE_KEY.value(),
                    550, 650, showButton, true);
            controller.fetchTableData(selectedTableItem.getGrades());
        });
    }

    private void initializeRefreshButtonAction() {
        refreshButton.setOnAction(button -> {
            Long superId = PropertiesLoader.readProperty(ResourceConst.PROPERTIES_LOADER_SUPER_ID_KEY.value(), new PropertiesStringToLongConverter());

            progressFactory.createLittleProgressBar(new LoadAllStudentGradesTask(superId),
                    this::fetchTabData);
        });
    }
}
