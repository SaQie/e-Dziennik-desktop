package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.rest.pojo.SimpleSchoolPojo;

@Getter
public class SchoolComboBoxItem {

    private final SimpleLongProperty id;
    private final SimpleStringProperty name;

    public SchoolComboBoxItem(SchoolPojo pojo) {
        this.id = new SimpleLongProperty(pojo.getId());
        this.name = new SimpleStringProperty(pojo.getName());
    }

    public SchoolComboBoxItem(SimpleSchoolPojo pojo){
        this.id = new SimpleLongProperty(pojo.getId());
        this.name = new SimpleStringProperty(pojo.getName());
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
