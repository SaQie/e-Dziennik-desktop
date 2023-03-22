package pl.edziennik.client.controller.admin.accounts.parent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.ActionType;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.controller.admin.accounts.student.AdminAccountsTabStudentsTabController;
import pl.edziennik.client.controller.model.admin.ParentListModel;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.parent.ParentDto;
import pl.edziennik.client.rest.dto.student.StudentDto;
import pl.edziennik.client.task.parent.DeleteParentTask;
import pl.edziennik.client.task.parent.LoadParentTask;
import pl.edziennik.client.task.parent.LoadParentsTask;
import pl.edziennik.client.task.student.LoadStudentsTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.edziennik.client.common.ResourceConst.*;

public class AdminAccountsTabParentsTabController extends AbstractController {

    private static AdminAccountsTabParentsTabController instance;

    @FXML
    private TableView<ParentListModel> parentsTableView;

    @FXML
    private Pagination pagination;

    public AdminAccountsTabParentsTabController() {
        instance = this;
    }

    private final Map<Integer, List<ParentDto>> paginationCacheMap = new HashMap<>();

    public void fetchTabData(final Page<List<ParentDto>> page) {
        pagination.setPageCount(page.getPagesCount());
        paginationCacheMap.put(page.getActualPage() - 1, page.getEntities());
        loadTableItems(page.getEntities());
    }

    private void loadTableItems(List<ParentDto> dtos) {
        List<ParentListModel> parentListModels = ParentListModel.mapToModel(dtos);
        ObservableList<ParentListModel> items = FXCollections.observableList(parentListModels);
        parentsTableView.setItems(items);
        parentsTableView.refresh();
    }

    public boolean isTableDataEmpty() {
        return parentsTableView.getItems().isEmpty();
    }


    private void initializePaginationChangeAction() {
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            boolean isCacheContainsData = paginationCacheMap.containsKey(newIndex.intValue());
            if (isCacheContainsData) {
                List<ParentDto> parentDtos = paginationCacheMap.get(newIndex.intValue());
                loadTableItems(parentDtos);
                return;
            }
            progressFactory.createLittleProgressBar(new LoadParentsTask(newIndex.intValue()), this::fetchTabData);
        });
    }

    @Override
    protected void createActions() {
        initializeAddButtonAction();
        initializeEditButtonAction();
        initializeShowButtonAction();
        initializeDeleteButtonAction();
        initializeRereshButtonAction();
        initializePaginationChangeAction();
    }

    private void initializeRereshButtonAction() {
        refreshButton.setOnAction(button -> {
            progressFactory.createLittleProgressBar(new LoadParentsTask(), this::fetchTabData);
        });
    }

    private void initializeDeleteButtonAction() {
        deleteButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(parentsTableView, ActionType.DELETE_ACTION);
            if (dialogFactory.createQuestionInformationDialog(ARE_YOU_SURE_TO_PERFORM_DELETE_OPERATION_MESSAGE_KEY.value())) {
                progressFactory.createLittleProgressBar(new DeleteParentTask(selectedTableItems), (action) -> {
                    refreshButton.fire();
                });
            }
        });
    }

    private void initializeShowButtonAction() {
        showButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(parentsTableView, ActionType.SHOW_ACTION);
            progressFactory.createLittleProgressBar(new LoadParentTask(selectedTableItems.get(0)), (response) -> {
                AdminAccountsTabParentsShowController controller = NodeUtils.openNewStageAboveWithController(DASHBOARD_ADMIN_ACCOUNTS_SHOW_PARENT_VIEW_ADDRESS.value(), SHOW_PARENT_TITLE_MESSAGE_KEY.value(),
                        1000, 500, showButton);
                controller.loadStageFields(response, ActionType.SHOW_ACTION);
            });
        });
    }

    private void initializeEditButtonAction() {
        editButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(parentsTableView, ActionType.EDIT_ACTION);
            progressFactory.createLittleProgressBar(new LoadParentTask(selectedTableItems.get(0)), (response) -> {
                AdminAccountsTabParentsEditController controller = NodeUtils.openNewStageAboveWithController(DASHBOARD_ADMIN_ACCOUNTS_EDIT_PARENT_VIEW_ADDRESS.value(), EDIT_PARENT_TITLE_MESSAGE_KEY.value(),
                        1000, 500, editButton);
                controller.loadStageFields(response, ActionType.EDIT_ACTION);
            });
        });
    }

    private void initializeAddButtonAction() {
        addButton.setOnAction(button -> {
            NodeUtils.openNewStageAbove(DASHBOARD_ADMIN_ACCOUNTS_ADD_PARENT_VIEW_ADDRESS.value(), ADD_PARENT_TITLE_MESSAGE_KEY.value(),
                    1000, 500, getActualStage(), addButton);
        });
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableViewPlaceHolder(parentsTableView);
        NodeUtils.setTableViewRowFactory(parentsTableView);
        NodeUtils.setColumnConfigurationShortcut(parentsTableView);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) parentsTableView.getScene().getWindow();
    }

    @Override
    protected void setTableColumns() {
        initializeTableColumns();
    }

    private void initializeTableColumns() {
        AdminTableViewControllerMaker.ParentsTableViewBuilder builder = AdminTableViewControllerMaker.parentsTableViewBuilder()
                .withSelectionColumn(true)
                .withUsernameColumn(true)
                .withFirstNameColumn(true)
                .withLastNameColumn(true)
                .withAddressColumn(true)
                .withEmailColumn(true)
                .withCityColumn(true)
                .withPeselColumn(true)
                .withRoleColumn(true)
                .withPostalCodeColumn(true)
                .withPhoneNumberColumn(true)
                .withStudentColumn(true);

        parentsTableView.getColumns().setAll(builder.build());
    }

    public static AdminAccountsTabParentsTabController getInstance() {
        return instance;
    }
}
