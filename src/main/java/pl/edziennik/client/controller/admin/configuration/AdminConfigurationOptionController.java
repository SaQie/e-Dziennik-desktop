package pl.edziennik.client.controller.admin.configuration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.controller.model.admin.ConfigurationListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.TableSelectionMode;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;
import pl.edziennik.client.utils.NodeUtils;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class AdminConfigurationOptionController extends AbstractController {


    @FXML
    private TableView<ConfigurationListModel> tableView;

    @FXML
    private Button exitButton, saveButton;


    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(exitButton);
        initializeEditButtonAction();
    }


    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableSelectOption(tableView, TableSelectionMode.SINGLE);
        NodeUtils.setTableViewPlaceHolder(tableView);
    }

    @Override
    protected void setTableColumns() {
        AdminTableViewControllerMaker.ConfigurationTableViewBuilder builder = AdminTableViewControllerMaker.configurationTableViewBuilder()
                .withSelectionColumn(true)
                .withNameColumn(true);

        tableView.getColumns().addAll(builder.build());
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }

    public void fetchData(List<ConfigurationDto> configurationList) {
        try {
            List<ConfigurationListModel> models = ConfigurationListModel.mapToModel(configurationList);
            ObservableList<ConfigurationListModel> items = FXCollections.observableList(models);
            tableView.setItems(items);
            tableView.refresh();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void initializeEditButtonAction() {
        editButton.setOnAction(button -> {
            List<Long> items = NodeUtils.getSelectedTableItems(tableView, ActionType.EDIT_ACTION);
            AdminConfigurationValueOptionController controller = NodeUtils.openNewStageAboveWithController(DASHBOARD_ADMIN_CONFIGURATION_VALUE_VIEW_ADDRESS.value()
                    ,CONFIGURATION_LIST_ADMIN_VIEW_TITLE_MESSAGE_KEY.value(),450,300,editButton);
            controller.fetchData(items.get(0));
        });
    }
}
