package pl.edziennik.client.rest.dto;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public interface DictionaryItemDto{

    Long getId();

    String getName();

    default SimpleObjectProperty<Long> getIdLongProperty() {
        return new SimpleObjectProperty<>(getId());
    }

    default SimpleStringProperty getNameStringProperty() {
        return new SimpleStringProperty(getName());
    }

}
