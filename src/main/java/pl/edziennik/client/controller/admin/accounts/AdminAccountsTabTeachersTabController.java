package pl.edziennik.client.controller.admin.accounts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.controller.model.admin.TeacherListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.pojo.TeacherPojo;
import pl.edziennik.client.utils.NodeUtils;

import java.util.List;

public class AdminAccountsTabTeachersTabController extends AbstractController {

    private static AdminAccountsTabTeachersTabController instance;

    @FXML
    private TableView<TeacherListModel> teachersTableView;

    public AdminAccountsTabTeachersTabController() {
        instance = this;
    }

    public void fetchTabData(final List<TeacherPojo> teachersList) {
        List<TeacherListModel> teacherListModels = TeacherListModel.mapPojoToModel(teachersList);
        ObservableList<TeacherListModel> items = FXCollections.observableList(teacherListModels);
        teachersTableView.setItems(items);
        teachersTableView.refresh();
    }

    public boolean isTableDataEmpty() {
        return teachersTableView.getItems().isEmpty();
    }

    public static AdminAccountsTabTeachersTabController getInstance() {
        return instance;
    }

    @Override
    protected void setTableColumns() {
        initializeTableColumns();
    }


    @Override
    protected void createActions() {

    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableViewRowFactory(teachersTableView);
        NodeUtils.setColumnConfigurationShortcut(teachersTableView);
        NodeUtils.setTableViewPlaceHolder(teachersTableView);
        initializeSelectUnselectAllMenuItemAction(teachersTableView);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) teachersTableView.getScene().getWindow();
    }

    private void initializeTableColumns() {
        AdminTableViewControllerMaker.TeacherTableViewBuilder builder = AdminTableViewControllerMaker.teacherTableViewBuilder()
                .withSelectionColumn(true)
                .withUsernameColumn(true)
                .withAddressColumn(true)
                .withCityColumn(true)
                .withFirstNameColumn(true)
                .withLastNameColumn(true)
                .withRoleColumn(true)
                .withPeselColumn(true)
                .withPostalCodeColumn(true)
                .withSchoolColumn(true);

        teachersTableView.getColumns().addAll(builder.build());
    }
}
