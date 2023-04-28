package pl.edziennik.client.common.model.student;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import pl.edziennik.client.rest.dto.grade.GradeDto;

import java.util.List;

@Getter
public class StudentSpecificGradeModel {

    private SimpleLongProperty gradeId;
    private SimpleLongProperty teacherId;
    private SimpleIntegerProperty grade;
    private SimpleIntegerProperty weight;
    private SimpleStringProperty description;


    public StudentSpecificGradeModel(GradeDto dto) {
        this.gradeId = new SimpleLongProperty(dto.getGradeId());
        this.grade = new SimpleIntegerProperty(dto.getGrade());
        this.description = new SimpleStringProperty(dto.getDescription());
        this.weight = new SimpleIntegerProperty(dto.getWeight());
        this.teacherId = new SimpleLongProperty(dto.getTeacherId());
    }

    public static List<StudentSpecificGradeModel> mapPojoToModel(List<GradeDto> grades) {
        return grades.stream().map(StudentSpecificGradeModel::new).toList();
    }


}
