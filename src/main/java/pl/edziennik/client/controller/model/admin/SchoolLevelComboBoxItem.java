package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import pl.edziennik.client.rest.dto.schoollevel.SimpleSchoolLevelDto;
import pl.edziennik.client.utils.MessageConverter;

@Getter
public class SchoolLevelComboBoxItem {

    private SimpleLongProperty schoolLevelId;
    private SimpleStringProperty name;

    public SchoolLevelComboBoxItem(SimpleSchoolLevelDto schoolLevel) {
        this.name = new SimpleStringProperty(MessageConverter.converSchoolLevelMessages(schoolLevel.getName()));
    }

    public SchoolLevelComboBoxItem() {
    }

    public void setSchoolLevelId(Long id) {
        this.schoolLevelId = new SimpleLongProperty(id);
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(MessageConverter.converSchoolLevelMessages(name));
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
