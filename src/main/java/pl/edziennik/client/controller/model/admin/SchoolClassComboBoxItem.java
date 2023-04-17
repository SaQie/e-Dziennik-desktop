package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.rest.dto.schoolclass.SimpleSchoolClassDto;

@Getter
public class SchoolClassComboBoxItem {

    private final SimpleLongProperty id;
    private final SimpleStringProperty className;

    public SchoolClassComboBoxItem(SchoolClassDto pojo) {
        this.id = new SimpleLongProperty(pojo.getSchoolClassId());
        this.className = new SimpleStringProperty(pojo.getClassName());
    }

    public SchoolClassComboBoxItem(SimpleSchoolClassDto pojo) {
        this.id = new SimpleLongProperty(pojo.getSchoolClassId());
        this.className = new SimpleStringProperty(pojo.getClassName());
    }

    @Override
    public String toString() {
        return className.getValue();
    }
}
