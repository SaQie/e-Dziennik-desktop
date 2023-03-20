package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.core.DictionaryItemModel;
import pl.edziennik.client.core.TableViewSelection;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.rest.dto.school.SimpleSchoolDto;


public class SchoolComboBoxItemModel implements TableViewSelection {

    private final SimpleLongProperty id;
    private final SimpleStringProperty name;
    private final CheckBox select;

    public SchoolComboBoxItemModel(SchoolDto pojo) {
        this.id = new SimpleLongProperty(pojo.getId());
        this.name = new SimpleStringProperty(pojo.getName());
        this.select = Styles.tableViewSelectionCheckBox();
    }

    public SchoolComboBoxItemModel(SimpleSchoolDto pojo) {
        this.id = new SimpleLongProperty(pojo.getId());
        this.name = new SimpleStringProperty(pojo.getName());
        this.select = Styles.tableViewSelectionCheckBox();
    }


    public void setId(long id) {
        this.id.set(id);
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
        return this.id.getValue();
    }

    @Override
    public boolean isSelected() {
        return this.select.isSelected();
    }

    @Override
    public CheckBox getSelect() {
        return select;
    }
}
