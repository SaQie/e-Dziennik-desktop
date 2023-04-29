package pl.edziennik.client.common.model.student;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.core.TableViewSelection;
import pl.edziennik.client.rest.dto.subject.SubjectGradeDto;

import java.util.List;

@Getter
public class StudentSubjectModel implements TableViewSelection {

    private final SimpleLongProperty subjectId;
    private final SimpleStringProperty subjectName;

    private final SimpleListProperty<StudentSpecificSubjectGradeModel> grades;
    private final SimpleListProperty<Integer> subjectGrades;
    private final CheckBox select;


    public StudentSubjectModel(SubjectGradeDto dto) {
        this.subjectId = new SimpleLongProperty(dto.getSubjectId());
        this.subjectName = new SimpleStringProperty(dto.getName());
        ObservableList<StudentSpecificSubjectGradeModel> gradeModelObservableList =
                FXCollections.observableList(StudentSpecificSubjectGradeModel.mapPojoToModel(dto.getGrades()));
        this.grades = new SimpleListProperty<>(gradeModelObservableList);
        this.subjectGrades = new SimpleListProperty<>(FXCollections.observableList(getSubjectGradesOnly()));
        this.select = Styles.tableViewSelectionCheckBox();
    }

    public static List<StudentSubjectModel> mapDtoToModel(List<SubjectGradeDto> subjectGradeDtos) {
        return subjectGradeDtos.stream().map(StudentSubjectModel::new).toList();
    }

    private List<Integer> getSubjectGradesOnly() {
        return this.grades.stream().map(grade -> grade.getGrade().getValue()).toList();
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
