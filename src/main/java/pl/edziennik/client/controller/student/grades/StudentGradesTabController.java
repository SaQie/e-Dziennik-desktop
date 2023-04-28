package pl.edziennik.client.controller.student.grades;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.controller.columns.student.StudentTableViewControllerMaker;
import pl.edziennik.client.common.model.admin.SchoolListModel;
import pl.edziennik.client.common.model.student.StudentGradeModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.rest.dto.student.StudentGradeDto;

import java.util.List;

public class StudentGradesTabController extends AbstractController {

    @FXML
    private TableView<StudentGradeModel> tableView;

    @Override
    protected void createActions() {

    }

    @Override
    protected void setTableColumns() {
        initializeTableColumns();
    }

    private void initializeTableColumns() {
        StudentTableViewControllerMaker.StudentAllSubjectGradesViewBuilder builder = StudentTableViewControllerMaker.getStudentAllSubjectGradesViewBuilder()
                .withSubjectIdentifierColumn(true)
                .withSubjectGradesColumn(true)
                .withSubjectNameColumn(true);

        tableView.getColumns().addAll(builder.build());
    }

    @Override
    protected void setSceneSettings() {

    }

    @Override
    protected Stage getActualStage() {
        return null;
    }

    public void fetchTabData(final StudentGradeDto dto) {
        List<StudentGradeModel> studentGradeModels = StudentGradeModel.mapDtoToModel(dto.getSubjects());
        ObservableList<StudentGradeModel> items = FXCollections.observableList(studentGradeModels);
        tableView.setItems(items);
        tableView.refresh();
    }

    public boolean isTableDataEmpty() {
        return tableView.getItems().isEmpty();
    }
}
