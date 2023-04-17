package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.core.TableViewSelection;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;

import java.util.List;

@Getter
public class SchoolClassListModel implements TableViewSelection {

    private final SimpleLongProperty id;
    private final SimpleStringProperty className;
    private final SimpleStringProperty supervisingTeacherFullName;
    private final SimpleStringProperty schoolName;
    private final CheckBox select;

    public SchoolClassListModel(SchoolClassDto dto) {
        this.id = new SimpleLongProperty(dto.getSchoolClassId());
        this.className = new SimpleStringProperty(dto.getClassName());
        String supervisingTeacherName = dto.getSupervisingTeacher() == null ? "" : dto.getSupervisingTeacher().getFullName();
        this.supervisingTeacherFullName = new SimpleStringProperty(supervisingTeacherName);
        this.schoolName = new SimpleStringProperty(dto.getSchool().getName());
        this.select = Styles.tableViewSelectionCheckBox();
    }

    public static List<SchoolClassListModel> mapToModel(List<SchoolClassDto> data) {
        return data.stream().map(SchoolClassListModel::new).toList();
    }

    public static SchoolClassListModel mapToModel(SchoolClassDto dto) {
        return new SchoolClassListModel(dto);
    }

    @Override
    public void setSelection() {
        this.select.setSelected(!select.isSelected());
    }

    @Override
    public boolean isSelected() {
        return this.select.isSelected();
    }

    @Override
    public Long getId() {
        return id.getValue();
    }
}
