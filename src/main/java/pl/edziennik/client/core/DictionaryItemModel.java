package pl.edziennik.client.core;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import pl.edziennik.client.common.Styles;

public class DictionaryItemModel implements TableViewSelection {

    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private CheckBox select;

    public DictionaryItemModel(Long id, String name) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.select = Styles.tableViewSelectionCheckBox();
    }


    public SimpleLongProperty getIdProperty() {
        return id;
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    @Override
    public String toString() {
        return name.getValue();
    }

    @Override
    public void setSelection() {
        this.select.setSelected(!select.isSelected());
    }

    @Override
    public Long getId() {
        return id.getValue();
    }

    @Override
    public boolean isSelected() {
        return select.isSelected();
    }

    @Override
    public CheckBox getSelect() {
        return select;
    }
}
