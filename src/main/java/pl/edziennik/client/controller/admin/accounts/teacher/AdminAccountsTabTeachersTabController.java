package pl.edziennik.client.controller.admin.accounts.teacher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.controller.model.admin.TeacherListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.teacher.TeacherDto;
import pl.edziennik.client.task.teacher.DeleteTeacherTask;
import pl.edziennik.client.task.teacher.LoadTeacherTask;
import pl.edziennik.client.task.teacher.LoadTeachersTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class AdminAccountsTabTeachersTabController extends AbstractController {

    private static AdminAccountsTabTeachersTabController instance;

    @FXML
    private TableView<TeacherListModel> teachersTableView;

    @FXML
    private Pagination pagination;

    private final Map<Integer, List<TeacherDto>> paginationCacheMap = new HashMap<>();

    public AdminAccountsTabTeachersTabController() {
        instance = this;
    }

    public void fetchTabData(final Page<List<TeacherDto>> page) {
        pagination.setPageCount(page.getPagesCount());
        paginationCacheMap.put(page.getActualPage() - 1, page.getContent());
        loadTableItems(page.getContent());
    }

    private void loadTableItems(List<TeacherDto> dtos) {
        List<TeacherListModel> teacherListModels = TeacherListModel.mapPojoToModel(dtos);
        ObservableList<TeacherListModel> items = FXCollections.observableList(teacherListModels);
        teachersTableView.setItems(items);
        teachersTableView.refresh();
    }

    public void addItem(TeacherListModel teacherModel) {
        ArrayList<TeacherListModel> actualItems = new ArrayList<>(teachersTableView.getItems());
        actualItems.add(teacherModel);
        teachersTableView.setItems(FXCollections.observableList(actualItems));
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
        initializeAddButtonAction();
        initializeRefreshButtonAction();
        initializeDeleteButtonAction();
        initializeEditButtonAction();
        initializeShowButtonAction();
        initializePaginationChangeAction();
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

    private void initializePaginationChangeAction() {
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            boolean isCacheContainsData = paginationCacheMap.containsKey(newIndex.intValue());
            if (isCacheContainsData) {
                List<TeacherDto> schoolDtos = paginationCacheMap.get(newIndex.intValue());
                loadTableItems(schoolDtos);
                return;
            }
            progressFactory.createLittleProgressBar(new LoadTeachersTask(newIndex.intValue()), this::fetchTabData);
        });
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
                .withSchoolColumn(true)
                .withEmailColumn(true);


        teachersTableView.getColumns().addAll(builder.build());
    }

    private void initializeRefreshButtonAction() {
        refreshButton.setOnAction(button -> {
            progressFactory.createLittleProgressBar(new LoadTeachersTask(), this::fetchTabData);
        });
    }

    private void initializeAddButtonAction() {
        addButton.setOnAction(button -> {
            NodeUtils.openNewStageAbove(
                    DASHBOARD_ADMIN_ACCOUNTS_ADD_TEACHER_VIEW_ADDRESS.value(),
                    ADMIN_ACCOUNTS_ADD_TEACHER_TITLE_MESSAGE_KEY.value(),
                    1000, 500, getActualStage(), addButton);
        });
    }

    private void initializeDeleteButtonAction() {
        deleteButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(teachersTableView, ActionType.DELETE_ACTION);
            if (dialogFactory.createQuestionInformationDialog(ARE_YOU_SURE_TO_PERFORM_DELETE_OPERATION_MESSAGE_KEY.value())) {
                progressFactory.createLittleProgressBar(new DeleteTeacherTask(selectedTableItems), (action) -> {
                    refreshButton.fire();
                });
            }
        });
    }

    private void initializeEditButtonAction() {
        editButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(teachersTableView, ActionType.EDIT_ACTION);
            progressFactory.createLittleProgressBar(new LoadTeacherTask(selectedTableItems.get(0)), (teacherPojo) -> {
                AdminAccountsTabTeachersEditController controller = NodeUtils.openNewStageAboveWithController(
                        DASHBOARD_ADMIN_ACCOUNTS_EDIT_TEACHER_VIEW_ADDRESS.value(),
                        EDIT_TEACHER_VIEW_TITLE_KEY.value(),
                        1000, 550,
                        editButton);
                controller.loadStageFields(teacherPojo, ActionType.EDIT_ACTION);
            });
        });
    }

    private void initializeShowButtonAction() {
        showButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(teachersTableView, ActionType.SHOW_ACTION);
            progressFactory.createLittleProgressBar(new LoadTeacherTask(selectedTableItems.get(0)), (teacherPojo) -> {
                AdminAccountsTabTeachersShowController controller = NodeUtils.openNewStageAboveWithController(
                        DASHBOARD_ADMIN_ACCOUNTS_SHOW_TEACHER_VIEW_ADDRESS.value(),
                        ResourceConst.SHOW_TEACHER_VIEW_TITLE.value(),
                        1000, 550,
                        showButton);
                controller.loadStageFields(teacherPojo, ActionType.SHOW_ACTION);
            });
        });
    }
}
