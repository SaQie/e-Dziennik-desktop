package pl.edziennik.client.common.model.student;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import pl.edziennik.client.rest.dto.grade.GradeDto;

import java.util.List;

@Getter
public class StudentSpecificSubjectGradeModel {

    private final SimpleLongProperty id;
    private final SimpleLongProperty teacherId;
    private final SimpleStringProperty teacherName;
    private final SimpleIntegerProperty grade;
    private final SimpleIntegerProperty weight;
    private final SimpleStringProperty description;
    private final SimpleStringProperty createdDate;


    public StudentSpecificSubjectGradeModel(GradeDto dto) {
        this.id = new SimpleLongProperty(dto.getGradeId());
        this.grade = new SimpleIntegerProperty(dto.getGrade());
        this.description = new SimpleStringProperty(dto.getDescription());
        this.weight = new SimpleIntegerProperty(dto.getWeight());
        this.teacherId = new SimpleLongProperty(dto.getTeacherId());
        this.teacherName = new SimpleStringProperty(dto.getTeacherName());
        this.createdDate = new SimpleStringProperty(dto.getCreatedDate().toString());
    }

    public static List<StudentSpecificSubjectGradeModel> mapPojoToModel(List<GradeDto> grades) {
        return grades.stream().map(StudentSpecificSubjectGradeModel::new).toList();
    }

    @Override
    public String toString() {
        return grade.getValue().toString();
    }

    public Long getId() {
        return this.id.getValue();
    }
}
