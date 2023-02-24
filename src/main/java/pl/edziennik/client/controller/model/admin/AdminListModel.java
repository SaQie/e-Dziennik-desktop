package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.rest.dto.admin.AdminDto;

import java.util.List;

@Getter
public class AdminListModel implements TableViewSelection {

    private final SimpleLongProperty id;
    private final SimpleStringProperty username;
    private final SimpleStringProperty email;
    private final SimpleStringProperty role;
    private final CheckBox select;

    public AdminListModel(AdminDto pojo) {
        this.id = new SimpleLongProperty(pojo.getId());
        this.username = new SimpleStringProperty(pojo.getUsername());
        this.email = new SimpleStringProperty(pojo.getEmail());
        this.role = new SimpleStringProperty(pojo.getRole());
        this.select = Styles.tableViewSelectionCheckBox();
    }

    public static List<AdminListModel> mapPojoToModel(List<AdminDto> pojoList) {
        return pojoList.stream()
                .map(AdminListModel::new)
                .toList();
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
}
