package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.rest.dto.school.SimpleSchoolDto;

@Getter
public class SchoolComboBoxItem {

    private final SimpleLongProperty id;
    private final SimpleStringProperty name;

    public SchoolComboBoxItem(SchoolDto pojo) {
        this.id = new SimpleLongProperty(pojo.getId());
        this.name = new SimpleStringProperty(pojo.getName());
    }

    public SchoolComboBoxItem(SimpleSchoolDto pojo){
        this.id = new SimpleLongProperty(pojo.getId());
        this.name = new SimpleStringProperty(pojo.getName());
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
