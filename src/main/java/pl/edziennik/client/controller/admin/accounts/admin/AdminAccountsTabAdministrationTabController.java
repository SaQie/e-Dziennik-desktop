package pl.edziennik.client.controller.admin.accounts.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.ActionType;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesStringToLongConverter;
import pl.edziennik.client.controller.admin.accounts.student.AdminAccountsTabStudentsEditController;
import pl.edziennik.client.controller.model.admin.AdminListModel;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.exception.BusinessException;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.rest.dto.student.StudentDto;
import pl.edziennik.client.task.admin.DeleteAdminTask;
import pl.edziennik.client.task.admin.LoadAdminTask;
import pl.edziennik.client.task.admin.LoadAdminsTask;
import pl.edziennik.client.task.student.DeleteStudentTask;
import pl.edziennik.client.task.student.LoadStudentTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.edziennik.client.common.ResourceConst.*;

public class AdminAccountsTabAdministrationTabController extends AbstractController {

    private static AdminAccountsTabAdministrationTabController instance;

    @FXML
    private TableView<AdminListModel> administrationTableView;

    @FXML
    private Pagination pagination;

    private final Map<Integer, List<AdminDto>> paginationCacheMap = new HashMap<>();

    public AdminAccountsTabAdministrationTabController() {
        instance = this;
    }

    public void fetchTabData(final Page<List<AdminDto>> page) {
        pagination.setPageCount(page.getPagesCount());
        paginationCacheMap.put(page.getActualPage(), page.getEntities());
        loadTableItems(page);

    }

    private void loadTableItems(Page<List<AdminDto>> page) {
        List<AdminListModel> adminListModels = AdminListModel.mapPojoToModel(page.getEntities());
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
        initializeRefreshButtonAction();
        initializeAddButtonAction();
        initializeEditButtonAction();
        initializeShowButtonAction();
        initializeDeleteButtonAction();
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

    private void initializeRefreshButtonAction() {
        refreshButton.setOnAction(button -> {
            progressFactory.createLittleProgressBar(new LoadAdminsTask(), this::fetchTabData);
        });
    }

    private void initializeAddButtonAction() {
        addButton.setOnAction(button -> {
            NodeUtils.openNewStageAbove(
                    DASHBOARD_ADMIN_ACCOUNTS_ADD_ADMIN_VIEW_ADDRESS.value(),
                    ADMIN_ACCOUNTS_ADD_ADMIN_TITLE_MESSAGE_KEY.value(),
                    500, 350, getActualStage());
        });
    }

    private void initializeEditButtonAction() {
        editButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(administrationTableView, ActionType.EDIT_ACTION);
            progressFactory.createLittleProgressBar(new LoadAdminTask(selectedTableItems.get(0)), (adminDto) -> {
                AdminAccountsTabAdministrationEditController controller = NodeUtils.openNewStageAboveWithController(
                        DASHBOARD_ADMIN_ACCOUNTS_EDIT_ADMIN_VIEW_ADDRESS.value(),
                        ADMIN_ACCOUNTS_EDIT_ADMIN_TITLE_MESSAGE_KEY.value(),
                        500, 350,
                        getActualStage());
                controller.loadStageFields(adminDto, ActionType.EDIT_ACTION);
            });
        });
    }

    private void initializeDeleteButtonAction() {
        deleteButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(administrationTableView, ActionType.DELETE_ACTION);
            Long userId = PropertiesLoader.readProperty(PROPERTIES_LOADER_ID_KEY.value(), new PropertiesStringToLongConverter());
            if (selectedTableItems.contains(userId)) {
                throw new BusinessException(CANNOT_DELETE_YOUR_OWN_ACCOUNT_MESSAGE_KEY.value());
            }
            if (dialogFactory.createQuestionInformationDialog(ARE_YOU_SURE_TO_PERFORM_DELETE_OPERATION_MESSAGE_KEY.value())) {
                progressFactory.createLittleProgressBar(new DeleteAdminTask(selectedTableItems), (action) -> {
                    refreshButton.fire();
                });
            }
        });
    }

    private void initializeShowButtonAction() {
        showButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(administrationTableView, ActionType.EDIT_ACTION);
            progressFactory.createLittleProgressBar(new LoadAdminTask(selectedTableItems.get(0)), (adminDto) -> {
                AdminAccountsTabAdministrationShowController controller = NodeUtils.openNewStageAboveWithController(
                        DASHBOARD_ADMIN_ACCOUNTS_SHOW_ADMIN_VIEW_ADDRESS.value(),
                        ADMIN_ACCOUNTS_SHOW_ADMIN_TITLE_MESSAGE_KEY.value(),
                        500, 350,
                        getActualStage());
                controller.loadStageFields(adminDto, ActionType.SHOW_ACTION);
            });
        });
    }

    public void addItem(AdminListModel adminListModel) {
        ArrayList<AdminListModel> actualItems = new ArrayList<>(administrationTableView.getItems());
        actualItems.add(adminListModel);
        administrationTableView.setItems(FXCollections.observableList(actualItems));
        administrationTableView.refresh();
    }
}
