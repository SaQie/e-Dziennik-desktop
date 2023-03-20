package pl.edziennik.client.rest.dto;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public interface DictionaryItemDto{

    Long getId();

    String getName();

    default SimpleLongProperty getIdLongProperty() {
        return new SimpleLongProperty(getId());
    }

    default SimpleStringProperty getNameStringProperty() {
        return new SimpleStringProperty(getName());
    }

}
