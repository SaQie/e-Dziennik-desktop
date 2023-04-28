package pl.edziennik.client.common.model.student;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import pl.edziennik.client.rest.dto.subject.SubjectGradeDto;

import java.util.List;

@Getter
public class StudentGradeModel {

    private final SimpleLongProperty subjectId;
    private final SimpleStringProperty subjectName;

    private final SimpleListProperty<StudentSpecificGradeModel> grades;
    private final SimpleListProperty<Integer> subjectGrades;


    public StudentGradeModel(SubjectGradeDto dto) {
        this.subjectId = new SimpleLongProperty(dto.getSubjectId());
        this.subjectName = new SimpleStringProperty(dto.getName());
        ObservableList<StudentSpecificGradeModel> gradeModelObservableList =
                FXCollections.observableList(StudentSpecificGradeModel.mapPojoToModel(dto.getGrades()));
        this.grades = new SimpleListProperty<>(gradeModelObservableList);
        this.subjectGrades = new SimpleListProperty<>(FXCollections.observableList(getSubjectGradesOnly()));
    }

    public static List<StudentGradeModel> mapDtoToModel(List<SubjectGradeDto> subjectGradeDtos) {
        return subjectGradeDtos.stream().map(StudentGradeModel::new).toList();
    }

    private List<Integer> getSubjectGradesOnly() {
        return this.grades.stream().map(grade -> grade.getGrade().getValue()).toList();
    }


}
