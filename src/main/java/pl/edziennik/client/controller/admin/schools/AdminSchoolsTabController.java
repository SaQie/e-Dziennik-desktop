package pl.edziennik.client.controller.admin.schools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pl.edziennik.client.common.ActionType;
import pl.edziennik.client.common.ProgressFactory;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.common.controller.columns.TableViewControllerMaker;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.task.DeleteSchoolTask;
import pl.edziennik.client.task.LoadSchoolLevelsTask;
import pl.edziennik.client.task.LoadSchoolsTask;
import pl.edziennik.client.utils.NodeUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminSchoolsTabController implements Initializable {

    private static AdminSchoolsTabController instance;

    private ProgressFactory progressFactory;

    public AdminSchoolsTabController() {
        instance = this;
        this.progressFactory = ProgressFactory.getInstance();
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
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTableColumns();
        NodeUtils.setTableViewRowFactory(tableView);
        NodeUtils.setTableViewPlaceHolder(tableView);
        NodeUtils.setColumnConfigurationShortcut(tableView);
        initializeSelectAllMenuItemAction();
        initializeAddButtonAction();
        initializeDeleteButtonAction();
        initializeShowButtonAction();
        initializeEditButtonAction();
        initializeRefreshButtonAction();
    }

    private void initializeShowButtonAction() {
        showButton.setOnAction(button -> {
            NodeUtils.getSelectedTableItems(tableView, ActionType.SHOW_ACTION);
        });
    }

    private void initializeEditButtonAction() {
        editButton.setOnAction(button -> {
            NodeUtils.getSelectedTableItems(tableView, ActionType.EDIT_ACTION);
        });
    }

    private void initializeRefreshButtonAction() {
        refreshButton.setOnAction(button -> {
            progressFactory.createLittleProgressBar(new LoadSchoolsTask(), this::fetchTabData);
        });
    }

    private void initializeDeleteButtonAction() {
        deleteButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(tableView, ActionType.DELETE_ACTION);
            progressFactory.createLittleProgressBar(new DeleteSchoolTask(selectedTableItems), (action) -> {
                refreshButton.fire();
            });
        });
    }

    private void initializeSelectAllMenuItemAction() {
        selectAllMenuItem.setOnAction(item -> {
            tableView.getItems().forEach(tableItem -> {
                tableItem.getSelect().setSelected(true);
                tableView.getSelectionModel().selectLast();
            });
        });
        unselectAllMenuItem.setOnAction(item -> {
            tableView.getItems().forEach(tableItem -> tableItem.getSelect().setSelected(false));
            tableView.getSelectionModel().clearSelection();
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
        ObservableList<SchoolListModel> items = FXCollections.observableList(schoolListModels);
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
