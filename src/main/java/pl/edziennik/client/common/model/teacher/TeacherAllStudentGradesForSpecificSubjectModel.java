package pl.edziennik.client.common.model.teacher;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.common.model.student.StudentSpecificSubjectGradeModel;
import pl.edziennik.client.core.TableViewSelection;
import pl.edziennik.client.rest.dto.student.StudentSpecificSubjectGradeDto;

import java.util.List;

@Getter
public class TeacherAllStudentGradesForSpecificSubjectModel implements TableViewSelection {

    private final SimpleLongProperty id;
    private final SimpleStringProperty fullName;
    private final SimpleLongProperty subjectId;
    private final SimpleStringProperty subjectName;
    private final SimpleListProperty<StudentSpecificSubjectGradeModel> grades;
    private final CheckBox select;

    public TeacherAllStudentGradesForSpecificSubjectModel(StudentSpecificSubjectGradeDto dto) {
        this.id = new SimpleLongProperty(dto.getStudentId());
        this.fullName = new SimpleStringProperty(dto.getFullName());
        this.subjectId = new SimpleLongProperty(dto.getSubjectData().getSubjectId());
        this.subjectName = new SimpleStringProperty(dto.getSubjectData().getName());

        ObservableList<StudentSpecificSubjectGradeModel> gradeModelObservableList =
                FXCollections.observableList(StudentSpecificSubjectGradeModel.mapPojoToModel(dto.getSubjectData().getGrades()));

        this.grades = new SimpleListProperty<>(gradeModelObservableList);
        this.select = Styles.tableViewSelectionCheckBox();
    }

    public static List<TeacherAllStudentGradesForSpecificSubjectModel> mapToModel(List<StudentSpecificSubjectGradeDto> dtos) {
        return dtos.stream().map(TeacherAllStudentGradesForSpecificSubjectModel::new).toList();
    }

    @Override
    public void setSelection() {
        this.select.setSelected(!select.isSelected());
    }

    @Override
    public Long getId() {
        return this.subjectId.getValue();
    }

    @Override
    public boolean isSelected() {
        return this.select.isSelected();
    }
}
