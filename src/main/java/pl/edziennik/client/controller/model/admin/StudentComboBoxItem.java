package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import pl.edziennik.client.rest.dto.student.SimpleStudentDto;

@Getter
public class StudentComboBoxItem {

    private final SimpleLongProperty id;
    private final SimpleStringProperty fullName;

    public StudentComboBoxItem(SimpleStudentDto dto) {
        this.id = new SimpleLongProperty(dto.getId());
        this.fullName = new SimpleStringProperty(dto.getFullName());
    }

    @Override
    public String toString() {
        return fullName.getValue();
    }
}
