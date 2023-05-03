package pl.edziennik.client.controller.teacher.grade;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.model.student.StudentSubjectModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.dto.subject.SubjectDto;

import java.util.List;

public class TeacherSubjectsManagmentController extends AbstractController {

    @FXML
    private TableView<StudentSubjectModel> tableView;

    @Override
    protected void createActions() {

    }

    @Override
    protected void setSceneSettings() {

    }

    @Override
    protected Stage getActualStage() {
        return null;
    }

    public boolean isTableDataEmpty() {
        return tableView.getItems().isEmpty();
    }

    public void fetchTabData(List<SubjectDto> dtos) {

    }
}
