package pl.edziennik.client.controller.admin.accounts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.controller.model.admin.AdminListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.utils.NodeUtils;

import java.util.List;

public class AdminAccountsTabAdministrationTabController extends AbstractController {

    private static AdminAccountsTabAdministrationTabController instance;

    @FXML
    private TableView<AdminListModel> administrationTableView;

    public AdminAccountsTabAdministrationTabController() {
        instance = this;
    }

    public void fetchTabData(final List<AdminDto> adminsList) {
        List<AdminListModel> adminListModels = AdminListModel.mapPojoToModel(adminsList);
        ObservableList<AdminListModel> items = FXCollections.observableList(adminListModels);
        administrationTableView.setItems(items);
        administrationTableView.refresh();

    }

    public boolean isTableDataEmpty() {
        return administrationTableView.getItems().isEmpty();
    }

    public static AdminAccountsTabAdministrationTabController getInstance() {
        return instance;
    }

    @Override
    protected void createActions() {

    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableViewRowFactory(administrationTableView);
        NodeUtils.setColumnConfigurationShortcut(administrationTableView);
        NodeUtils.setTableViewPlaceHolder(administrationTableView);
        initializeSelectUnselectAllMenuItemAction(administrationTableView);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) administrationTableView.getScene().getWindow();
    }

    @Override
    protected void setTableColumns() {
        initializeTableColumns();
    }

    private void initializeTableColumns() {
        AdminTableViewControllerMaker.AdministrationTableViewBuilder builder = AdminTableViewControllerMaker.administrationTableViewBuilder()
                .withSelectionColumn(true)
                .withUsernameColumn(true)
                .withRoleColumn(true)
                .withEmailColumn(true);

        administrationTableView.getColumns().addAll(builder.build());
    }
}
