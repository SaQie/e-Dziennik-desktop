package pl.edziennik.client.controller.admin.schools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.controller.admin.schools.actions.AdminSchoolsTabMarkSchoolAsDefaultToRegisterAction;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.core.TableSelectionMode;
import pl.edziennik.client.core.contextmenu.ContextMenuAction;
import pl.edziennik.client.core.contextmenu.ContextMenuActionBuilder;
import pl.edziennik.client.core.toast.Toast;
import pl.edziennik.client.core.toast.ToastType;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.task.school.DeleteSchoolTask;
import pl.edziennik.client.task.school.LoadSchoolTask;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class AdminSchoolsTabController extends AbstractController {

    private static AdminSchoolsTabController instance;

    public AdminSchoolsTabController() {
        instance = this;
    }

    @FXML
    private TableView<SchoolListModel> tableView;

    @FXML
    private Pagination pagination;

    private final Map<Integer, List<SchoolDto>> paginationCacheMap = new HashMap<>();

    @Override
    protected void createActions() {
        initializeSelectUnselectAllMenuItemAction(tableView);
        initializeAddButtonAction();
        initializeDeleteButtonAction();
        initializeShowButtonAction();
        initializeEditButtonAction();
        initializeRefreshButtonAction();
        initializePaginationChangeAction();
        initializeContextMenuActions();
    }

    private void initializeContextMenuActions() {
        ContextMenuActionBuilder.builder()
                .addAction(new ContextMenuAction("Oznacz szkołę jaką domyślną do rejestracji",
                        new AdminSchoolsTabMarkSchoolAsDefaultToRegisterAction(), MARK_ICON_ADDRESS.value()).setParameters(3L))
                .build(tableView);
    }

    private void initializePaginationChangeAction() {
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            boolean isCacheContainsData = paginationCacheMap.containsKey(newIndex.intValue());
            if (isCacheContainsData) {
                List<SchoolDto> schoolDtos = paginationCacheMap.get(newIndex.intValue());
                loadTableItems(schoolDtos);
                return;
            }
            progressFactory.createLittleProgressBar(new LoadSchoolsTask(newIndex.intValue()), this::fetchTabData);
        });
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableSelectOption(tableView, TableSelectionMode.MULTIPLE);
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


    public void fetchTabData(final Page<List<SchoolDto>> page) {
        pagination.setPageCount(page.getPagesCount());
        paginationCacheMap.put(page.getActualPage() - 1, page.getContent());
        loadTableItems(page.getContent());
    }

    private void loadTableItems(List<SchoolDto> data) {
        List<SchoolListModel> schoolListModels = SchoolListModel.mapPojoToModel(data);
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
                .withIdentifierColumn(true)
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
                        showButton);
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
                        editButton);
                controller.loadStageFields(schoolPojo);
            });
        });
    }

    private void initializeRefreshButtonAction() {
        refreshButton.setOnAction(button -> {
            progressFactory.createLittleProgressBar(new LoadSchoolsTask(), (response) -> {
                paginationCacheMap.clear();
                fetchTabData(response);
            });
        });
    }

    private void initializeDeleteButtonAction() {
        deleteButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(tableView, ActionType.DELETE_ACTION);
            if (dialogFactory.createQuestionInformationDialog(ARE_YOU_SURE_TO_PERFORM_DELETE_OPERATION_MESSAGE_KEY.value())) {
                progressFactory.createLittleProgressBar(new DeleteSchoolTask(selectedTableItems), (action) -> {
                    refreshButton.fire();
                    Toast.show(ToastType.INFORMATION, SUCCESS_DIALOG_CONTENT_MESSAGE_KEY.value());
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
                    getActualStage(), addButton);
        });
    }

}
