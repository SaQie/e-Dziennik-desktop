package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import pl.edziennik.client.rest.pojo.SchoolClassPojo;
import pl.edziennik.client.rest.pojo.SimpleSchoolClassPojo;

@Getter
public class SchoolClassComboBoxItem {

    private final SimpleLongProperty id;
    private final SimpleStringProperty className;

    public SchoolClassComboBoxItem(SchoolClassPojo pojo) {
        this.id = new SimpleLongProperty(pojo.getId());
        this.className = new SimpleStringProperty(pojo.getClassName());
    }

    public SchoolClassComboBoxItem(SimpleSchoolClassPojo pojo) {
        this.id = new SimpleLongProperty(pojo.getId());
        this.className = new SimpleStringProperty(pojo.getClassName());
    }

    @Override
    public String toString() {
        return className.getValue();
    }
}
