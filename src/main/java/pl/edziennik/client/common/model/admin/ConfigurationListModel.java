package pl.edziennik.client.common.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.core.TableViewSelection;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;

import java.util.List;

@Getter
public class ConfigurationListModel implements TableViewSelection {

    private final SimpleLongProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty stringValue;
    private final SimpleObjectProperty<Boolean> booleanValue;
    private final SimpleObjectProperty<Long> longValue;
    private final CheckBox select;

    public ConfigurationListModel(ConfigurationDto dto) {
        this.id = new SimpleLongProperty(dto.getSettingId());
        this.name = new SimpleStringProperty(dto.getName());
        this.stringValue = new SimpleStringProperty(dto.getStringValue());
        this.booleanValue = new SimpleObjectProperty<>(dto.getBooleanValue());
        this.longValue = new SimpleObjectProperty<>(dto.getLongValue());
        this.select = Styles.tableViewSelectionCheckBox();
    }

    public static ConfigurationListModel mapToModel(ConfigurationDto dto){
        return new ConfigurationListModel(dto);
    }

    public static List<ConfigurationListModel> mapToModel(List<ConfigurationDto> dtos){
        return dtos.stream().map(ConfigurationListModel::new).toList();
    }

    @Override
    public void setSelection() {
        this.select.setSelected(!select.isSelected());
    }

    @Override
    public boolean isSelected() {
        return select.isSelected();
    }

    @Override
    public Long getId(){
        return id.getValue();
    }


}
