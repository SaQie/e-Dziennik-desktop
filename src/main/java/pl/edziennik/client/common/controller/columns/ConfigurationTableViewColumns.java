package pl.edziennik.client.common.controller.columns;

import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.controller.model.admin.ConfigurationListModel;
import pl.edziennik.client.utils.ResourceUtil;

class ConfigurationTableViewColumns extends BasicTableViewColumns{

    private ConfigurationTableViewColumns() {
    }

    protected static TableColumn<ConfigurationListModel,String> getConfigurationNameColumn(final boolean isDefaultVisible){
        TableColumn<ConfigurationListModel, String> nameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.NAME_COLUMN_KEY.value()));
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        nameColumn.setVisible(isDefaultVisible);
        return nameColumn;
    }
}
