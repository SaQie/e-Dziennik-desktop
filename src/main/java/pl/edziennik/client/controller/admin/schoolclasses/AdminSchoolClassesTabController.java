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
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.task.schoolclass.DeleteSchoolClass;
import pl.edziennik.client.task.schoolclass.LoadSchoolClassesTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.edziennik.client.common.constants.ResourceConst.ARE_YOU_SURE_TO_PERFORM_DELETE_OPERATION_MESSAGE_KEY;

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
        NodeUtils.setTableViewRowFactory(tableView);
        NodeUtils.setTableViewPlaceHolder(tableView);
        NodeUtils.setColumnConfigurationShortcut(tableView);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
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

        });
    }

    private void initializeEditButtonAction() {
        editButton.setOnAction(button -> {

        });
    }

    private void initializeShowButtonAction() {
        showButton.setOnAction(button -> {

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
                progressFactory.createLittleProgressBar(new DeleteSchoolClass(items), (action) -> {
                    refreshButton.fire();
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
