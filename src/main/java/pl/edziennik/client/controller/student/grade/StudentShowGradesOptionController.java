package pl.edziennik.client.controller.student.grade;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.controller.column.student.StudentTableViewControllerMaker;
import pl.edziennik.client.common.model.student.StudentSpecificSubjectGradeModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.util.NodeUtils;

import java.util.List;

public class StudentShowGradesOptionController extends AbstractController {

    @FXML
    private TableView<StudentSpecificSubjectGradeModel> tableView;

    @FXML
    private Button exitButton;

    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(exitButton);
    }

    @Override
    protected void setSceneSettings() {

    }

    @Override
    protected void setTableColumns() {
        StudentTableViewControllerMaker.StudentSpecificSubjectGradesColumnBuilder builder =
                StudentTableViewControllerMaker.getStudentSpecificSubjectGradesColumnBuilder()
                        .withIdentifierColumn(true)
                        .withGradeColumn(true)
                        .withWeightColumn(true)
                        .withDescriptionColumn(true)
                        .withTeacherNameColumn(true)
                        .withCreatedDateColumn(true);

        tableView.getColumns().addAll(builder.build());
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) exitButton.getScene().getWindow();
    }


    public void fetchTableData(List<StudentSpecificSubjectGradeModel> grades) {
        ObservableList<StudentSpecificSubjectGradeModel> items = FXCollections.observableList(grades);
        tableView.setItems(items);
        tableView.refresh();
    }
}
