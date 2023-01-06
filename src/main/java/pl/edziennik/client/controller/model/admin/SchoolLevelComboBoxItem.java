package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import pl.edziennik.client.utils.MessageConverter;

@Getter
public class SchoolLevelComboBoxItem {

    private SimpleLongProperty id;
    private SimpleStringProperty name;

    public void setId(Long id) {
        this.id = new SimpleLongProperty(id);
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(MessageConverter.converSchoolLevelMessages(name));
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
