package pl.edziennik.client.controller.admin.schools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.common.ActionType;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.task.school.DeleteSchoolTask;
import pl.edziennik.client.task.school.LoadSchoolTask;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;

import static pl.edziennik.client.common.ResourceConst.*;

public class AdminSchoolsTabController extends AbstractController {

    private static AdminSchoolsTabController instance;

    public AdminSchoolsTabController() {
        instance = this;
    }

    @FXML
    private TableView<SchoolListModel> tableView;


    @Override
    protected void createActions() {
        initializeSelectUnselectAllMenuItemAction(tableView);
        initializeAddButtonAction();
        initializeDeleteButtonAction();
        initializeShowButtonAction();
        initializeEditButtonAction();
        initializeRefreshButtonAction();
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableViewRowFactory(tableView);
        NodeUtils.setTableViewPlaceHolder(tableView);
        NodeUtils.setColumnConfigurationShortcut(tableView);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    @Override
    protected void setTableColumns() {
        initializeTableColumns();
    }


    public void fetchTabData(final List<SchoolDto> schoolList) {
        List<SchoolListModel> schoolListModels = SchoolListModel.mapPojoToModel(schoolList);
        ObservableList<SchoolListModel> items = FXCollections.observableList(schoolListModels);
        tableView.setItems(items);
        tableView.refresh();
    }

    public void addItem(final SchoolListModel school) {
        ArrayList<SchoolListModel> actualItems = new ArrayList<>(tableView.getItems());
        actualItems.add(school);
        tableView.setItems(FXCollections.observableList(actualItems));
        tableView.refresh();
    }

    public boolean isTableDataEmpty() {
        return tableView.getItems().isEmpty();
    }

    public static AdminSchoolsTabController getInstance() {
        return instance;
    }

    private void initializeTableColumns() {
        AdminTableViewControllerMaker.SchoolTableViewBuilder builder = AdminTableViewControllerMaker.schoolTableViewBuilder()
                .withSelectColumn(true)
                .withSchoolCityColumn(true)
                .withSchoolNameColumn(true)
                .withSchoolPhoneNumberColumn(true)
                .withSchoolAddressColumn(true)
                .withSchoolNipColumn(false)
                .withSchoolRegonColumn(false)
                .withSchoolPostalCodeColumn(true)
                .withHidedSchoolLevelColumn(false);

        tableView.getColumns().addAll(builder.build());
    }


    private void initializeShowButtonAction() {
        showButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(tableView, ActionType.SHOW_ACTION);
            progressFactory.createLittleProgressBar(new LoadSchoolTask(selectedTableItems.get(0)), (schoolPojo) -> {
                AdminSchoolsTabShowSchoolController controller = NodeUtils.openNewStageAboveWithController(
                        ResourceConst.DASHBOARD_ADMIN_SCHOOL_SHOW_VIEW_ADDRESS.value(),
                        ResourceConst.SHOW_SCHOOL_VIEW_TITLE_KEY.value(),
                        500, 600,
                        getActualStage());
                controller.loadStageFields(schoolPojo);
            });
        });
    }

    private void initializeEditButtonAction() {
        editButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(tableView, ActionType.EDIT_ACTION);
            progressFactory.createLittleProgressBar(new LoadSchoolTask(selectedTableItems.get(0)), (schoolPojo) -> {
                AdminSchoolsTabEditSchoolController controller = NodeUtils.openNewStageAboveWithController(
                        ResourceConst.DASHBOARD_ADMIN_SCHOOL_EDIT_VIEW_ADDRESS.value(),
                        ResourceConst.EDIT_SCHOOL_VIEW_TITLE_KEY.value(),
                        500, 600,
                        getActualStage());
                controller.loadStageFields(schoolPojo);
            });
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
            if (dialogFactory.createQuestionInformationDialog(ARE_YOU_SURE_TO_PERFORM_DELETE_OPERATION_MESSAGE_KEY.value())) {
                progressFactory.createLittleProgressBar(new DeleteSchoolTask(selectedTableItems), (action) -> {
                    refreshButton.fire();
                });
            }
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

}
