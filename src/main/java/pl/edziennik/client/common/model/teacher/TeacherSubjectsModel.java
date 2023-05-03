package pl.edziennik.client.common.model.teacher;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.core.TableViewSelection;
import pl.edziennik.client.rest.dto.subject.SubjectDto;

import java.util.List;

@Getter
public class TeacherSubjectsModel implements TableViewSelection {

    private final SimpleLongProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty description;
    private final SimpleLongProperty schoolClassId;
    private final SimpleStringProperty schoolClassName;
    private final SimpleLongProperty teacherId;
    private final SimpleStringProperty teacherName;
    private final CheckBox select;

    public TeacherSubjectsModel(SubjectDto subjectDto) {
        this.id = new SimpleLongProperty(subjectDto.getSubjectId());
        this.name = new SimpleStringProperty(subjectDto.getName());
        this.description = new SimpleStringProperty(subjectDto.getDescription());
        this.schoolClassId = new SimpleLongProperty(subjectDto.getSchoolClass().getSchoolClassId());
        this.schoolClassName = new SimpleStringProperty(subjectDto.getSchoolClass().getClassName());
        this.teacherId = new SimpleLongProperty(subjectDto.getTeacher().getTeacherId());
        this.teacherName = new SimpleStringProperty(subjectDto.getTeacher().getFullName());
        this.select = Styles.tableViewSelectionCheckBox();
    }

    public static List<TeacherSubjectsModel> mapToModel(List<SubjectDto> dtos) {
        return dtos.stream().map(TeacherSubjectsModel::new).toList();
    }

    @Override
    public void setSelection() {
        this.select.setSelected(!select.isSelected());
    }

    @Override
    public Long getId() {
        return this.id.getValue();
    }

    @Override
    public boolean isSelected() {
        return this.select.isSelected();
    }
}
