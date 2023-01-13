package pl.edziennik.client.controller.admin.schools;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.common.controller.columns.TableViewControllerMaker;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.utils.NodeUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminSchoolsTabController implements Initializable {

    private static AdminSchoolsTabController instance;

    public AdminSchoolsTabController() {
        instance = this;
    }

    @FXML
    private TableView<SchoolListModel> tableView;

    @FXML
    private Button addButton, editButton, showButton, deleteButton, refreshButton;

    @FXML
    private MenuButton menuButton;

    @FXML
    private MenuItem selectAllMenuItem, unselectAllMenuItem;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTableColumns();
        NodeUtils.enableButtonsIfSelectionModelIsNotEmpty(tableView, editButton, showButton, deleteButton);
        NodeUtils.setTableViewPlaceHolder(tableView);
        NodeUtils.setSelectionAfterClick(tableView);
        initializeSelectAllMenuItemAction();
        initializeAddButtonAction();
    }

    private void initializeSelectAllMenuItemAction() {
        selectAllMenuItem.setOnAction(item -> {
            tableView.getItems().forEach(tableItem -> tableItem.getSelect().setSelected(true));
        });
        unselectAllMenuItem.setOnAction(item -> {
            tableView.getItems().forEach(tableItem -> tableItem.getSelect().setSelected(false));
        });
    }

    private void initializeAddButtonAction() {
        addButton.setOnAction(button -> {
            NodeUtils.openNewStageAbove(
                    ResourceConst.DASHBOARD_ADMIN_SCHOOL_ADD_VIEW_ADDRESS.value(),
                    ResourceConst.ADD_SCHOOL_VIEW_TITLE_KEY.value(),
                    500, 600,
                    getActualStage());
        });
    }

    private void initializeTableColumns() {
        TableViewControllerMaker.SchoolTableViewBuilder builder = TableViewControllerMaker.builder()
                .withSelectColumn()
                .withSchoolCityColumn()
                .withSchoolNameColumn()
                .withSchoolPhoneNumberColumn()
                .withSchoolAddressColumn()
                .withSchoolNipColumn()
                .withSchoolRegonColumn()
                .withSchoolPostalCodeColumn()
                .withHidedSchoolLevelColumn();

        tableView.getColumns().addAll(builder.build());
    }


    public void fetchTabData(final List<SchoolPojo> schoolList) {
        List<SchoolListModel> schoolListModels = SchoolListModel.mapPojoToModel(schoolList);
        ObservableList<SchoolListModel> items = tableView.getItems();
        items.addAll(schoolListModels);
        tableView.setItems(items);
        tableView.refresh();

    }

    public void addItem(final SchoolListModel school) {
        ObservableList<SchoolListModel> items = tableView.getItems();
        items.add(school);
        tableView.setItems(items);
        tableView.refresh();
    }

    public boolean isTableDataEmpty() {
        return tableView.getItems().isEmpty();
    }

    private Stage getActualStage() {
        return (Stage) tableView.getScene().getWindow();
    }


    public static AdminSchoolsTabController getInstance() {
        return instance;
    }

}
