package pl.edziennik.client.controller.admin.schoolclasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.controller.model.admin.SchoolClassListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.TableSelectionMode;
import pl.edziennik.client.core.toast.Toast;
import pl.edziennik.client.core.toast.ToastType;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.task.schoolclass.DeleteSchoolClassTask;
import pl.edziennik.client.task.schoolclass.LoadSchoolClassTask;
import pl.edziennik.client.task.schoolclass.LoadSchoolClassesTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class AdminSchoolClassesTabController extends AbstractController {

    private static AdminSchoolClassesTabController instance;

    public AdminSchoolClassesTabController() {
        instance = this;
    }

    @FXML
    private TableView<SchoolClassListModel> tableView;

    @FXML
    private Pagination pagination;

    private final Map<Integer, List<SchoolClassDto>> paginationCacheMap = new HashMap<>();

    @Override
    protected void createActions() {
        initializeSelectUnselectAllMenuItemAction(tableView);
        initializeAddButtonAction();
        initializeEditButtonAction();
        initializeRefreshButtonAction();
        initializeShowButtonAction();
        initializeDeleteButtonAction();
        initializePaginationChangeAction();
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

    public void fetchTabData(final Page<List<SchoolClassDto>> page) {
        pagination.setPageCount(page.getPagesCount());
        paginationCacheMap.put(page.getActualPage() - 1, page.getContent());
        loadTableItems(page.getContent());
    }

    public boolean isTableDataEmpty() {
        return tableView.getItems().isEmpty();
    }

    public void addItem(SchoolClassListModel model){
        ArrayList<SchoolClassListModel> acutalItems = new ArrayList<>(tableView.getItems());
        acutalItems.add(model);
        tableView.setItems(FXCollections.observableList(acutalItems));
        tableView.refresh();
    }

    private void loadTableItems(List<SchoolClassDto> data) {
        List<SchoolClassListModel> schoolClassListModels = SchoolClassListModel.mapToModel(data);
        ObservableList<SchoolClassListModel> items = FXCollections.observableList(schoolClassListModels);
        tableView.setItems(items);
        tableView.refresh();
    }


    private void initializeTableColumns() {
        AdminTableViewControllerMaker.SchoolClassTableViewBuilder builder = AdminTableViewControllerMaker.schoolClassTableViewBuilder()
                .withSelectColumn(true)
                .withSchoolClassNameColumn(true)
                .withTeacherFullNameColumn(true)
                .withSchoolNameColumn(true);

        tableView.getColumns().addAll(builder.build());
    }

    public static AdminSchoolClassesTabController getInstance() {
        return instance;
    }

    private void initializeAddButtonAction() {
        addButton.setOnAction(button -> {
            NodeUtils.openNewStageAbove(DASHBOARD_ADMIN_SCHOOL_CLASS_ADD_ADDRESS.value(),
                    ADD_SCHOOL_CLASS_VIEW_TITLE_KEY.value(),
                    500,350, getActualStage(), addButton);
        });
    }

    private void initializeEditButtonAction() {
        editButton.setOnAction(button -> {
            List<Long> items = NodeUtils.getSelectedTableItems(tableView, ActionType.EDIT_ACTION);
            progressFactory.createLittleProgressBar(new LoadSchoolClassTask(items.get(0)), (response) -> {
                AdminSchoolClassesTabEditSchoolClassController editController = NodeUtils.openNewStageAboveWithController(DASHBOARD_ADMIN_SCHOOL_CLASS_EDIT_ADDRESS.value(),
                        EDIT_SCHOOL_CLASS_VIEW_TITLE_KEY.value(),
                        500, 350, editButton);
                editController.loadStageData(response, ActionType.EDIT_ACTION);
            });
        });
    }

    private void initializeShowButtonAction() {
        showButton.setOnAction(button -> {
            List<Long> items = NodeUtils.getSelectedTableItems(tableView, ActionType.SHOW_ACTION);
            progressFactory.createLittleProgressBar(new LoadSchoolClassTask(items.get(0)), (response) -> {
                AdminSchoolClassesTabShowSchoolClassController showController = NodeUtils.openNewStageAboveWithController(DASHBOARD_ADMIN_SCHOOL_CLASS_SHOW_ADDRESS.value(),
                        SHOW_SCHOOL_CLASS_VIEW_TITLE_KEY.value(),
                        500, 350, showButton);
                showController.loadStageData(response, ActionType.SHOW_ACTION);
            });


        });
    }

    private void initializeRefreshButtonAction() {
        refreshButton.setOnAction(button -> {
            progressFactory.createLittleProgressBar(new LoadSchoolClassesTask(), (response) -> {
                paginationCacheMap.clear();
                fetchTabData(response);
            });
        });
    }

    private void initializeDeleteButtonAction() {
        deleteButton.setOnAction(button -> {
            List<Long> items = NodeUtils.getSelectedTableItems(tableView, ActionType.DELETE_ACTION);
            if (dialogFactory.createQuestionInformationDialog(ARE_YOU_SURE_TO_PERFORM_DELETE_OPERATION_MESSAGE_KEY.value())) {
                progressFactory.createLittleProgressBar(new DeleteSchoolClassTask(items), (action) -> {
                    refreshButton.fire();
                    Toast.show(ToastType.INFORMATION, SUCCESS_DIALOG_CONTENT_MESSAGE_KEY.value());
                });
            }
        });
    }

    private void initializePaginationChangeAction() {
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            boolean isCacheContainsData = paginationCacheMap.containsKey(newIndex.intValue());
            if (isCacheContainsData) {
                List<SchoolClassDto> schoolClassDtos = paginationCacheMap.get(newIndex.intValue());
                loadTableItems(schoolClassDtos);
                return;
            }
            progressFactory.createLittleProgressBar(new LoadSchoolClassesTask(newIndex.intValue()), this::fetchTabData);
        });
    }

}
