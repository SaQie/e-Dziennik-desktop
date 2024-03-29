package pl.edziennik.client.controller.admin.account.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.controller.column.admin.AdminTableViewControllerMaker;
import pl.edziennik.client.common.model.admin.StudentListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.TableSelectionMode;
import pl.edziennik.client.core.toast.Toast;
import pl.edziennik.client.core.toast.ToastType;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.student.StudentDto;
import pl.edziennik.client.task.student.DeleteStudentTask;
import pl.edziennik.client.task.student.LoadStudentTask;
import pl.edziennik.client.task.student.LoadStudentsTask;
import pl.edziennik.client.util.NodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class AdminAccountsTabStudentsTabController extends AbstractController {

    private static AdminAccountsTabStudentsTabController instance;

    @FXML
    private TableView<StudentListModel> studentsTableView;

    @FXML
    private Pagination pagination;

    private final Map<Integer, List<StudentDto>> paginationCacheMap = new HashMap<>();


    public AdminAccountsTabStudentsTabController() {
        instance = this;
    }

    public void fetchTabData(final Page<List<StudentDto>> page) {
        pagination.setPageCount(page.getPagesCount());
        paginationCacheMap.put(page.getActualPage() - 1, page.getContent());
        loadTableItems(page.getContent());

    }

    private void loadTableItems(List<StudentDto> dtos) {
        List<StudentListModel> studentListModels = StudentListModel.mapPojoToModel(dtos);
        ObservableList<StudentListModel> items = FXCollections.observableList(studentListModels);
        studentsTableView.setItems(items);
        studentsTableView.refresh();
    }


    public void addItem(StudentListModel studentModel) {
        ArrayList<StudentListModel> actualItems = new ArrayList<>(studentsTableView.getItems());
        actualItems.add(studentModel);
        studentsTableView.setItems(FXCollections.observableList(actualItems));
        studentsTableView.refresh();
    }

    public boolean isTableDataEmpty() {
        return studentsTableView.getItems().isEmpty();
    }

    public static AdminAccountsTabStudentsTabController getInstance() {
        return instance;
    }

    @Override
    protected void setTableColumns() {
        initializeTableColumns();
    }

    @Override
    protected void createActions() {
        initializeAddButtonAction();
        initializeDeleteButtonAction();
        initializeRefreshButtonAction();
        initializeShowButtonAction();
        initializeEditButtonAction();
        initializePaginationChangeAction();
    }


    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableSelectOption(studentsTableView, TableSelectionMode.MULTIPLE);
        NodeUtils.setColumnConfigurationShortcut(studentsTableView);
        NodeUtils.setTableViewPlaceHolder(studentsTableView);
        initializeSelectUnselectAllMenuItemAction(studentsTableView);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) studentsTableView.getScene().getWindow();
    }


    private void initializePaginationChangeAction() {
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            boolean isCacheContainsData = paginationCacheMap.containsKey(newIndex.intValue());
            if (isCacheContainsData) {
                List<StudentDto> schoolDtos = paginationCacheMap.get(newIndex.intValue());
                loadTableItems(schoolDtos);
                return;
            }
            progressFactory.createLittleProgressBar(new LoadStudentsTask(newIndex.intValue()), this::fetchTabData);
        });
    }

    private void initializeTableColumns() {
        AdminTableViewControllerMaker.StudentTableViewBuilder builder = AdminTableViewControllerMaker.studentTableViewBuilder()
                .withSelectionColumn(true)
                .withUsernameColumn(true)
                .withAddressColumn(true)
                .withCityColumn(true)
                .withFirstNameColumn(true)
                .withLastNameColumn(true)
                .withPhoneNumberColumn(true)
                .withRoleColumn(true)
                .withPeselColumn(true)
                .withParentFullNameColumn(true)
                .withPostalCodeColumn(true)
                .withSchoolColumn(true)
                .withSchoolClassColumn(true)
                .withEmailColumn(true);

        studentsTableView.getColumns().addAll(builder.build());
    }

    private void initializeAddButtonAction() {
        addButton.setOnAction(button -> {
            NodeUtils.openNewStageAbove(
                    DASHBOARD_ADMIN_ACCOUNTS_ADD_STUDENT_VIEW_ADDRESS.value(),
                    ADMIN_ACCOUNTS_ADD_STUDENT_TITLE_MESSAGE_KEY.value(),
                    1000, 500, getActualStage(), addButton);
        });
    }

    private void initializeRefreshButtonAction() {
        refreshButton.setOnAction(button -> {
            progressFactory.createLittleProgressBar(new LoadStudentsTask(), this::fetchTabData);
        });
    }

    private void initializeShowButtonAction() {
        showButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(studentsTableView, ActionType.SHOW_ACTION);
            progressFactory.createLittleProgressBar(new LoadStudentTask(selectedTableItems.get(0)), (schoolPojo) -> {
                AdminAccountsTabStudentsShowController controller = NodeUtils.openNewStageAboveWithController(
                        ResourceConst.DASHBOARD_ADMIN_ACCOUNTS_SHOW_STUDENT_VIEW_ADDRESS.value(),
                        ResourceConst.SHOW_STUDENT_VIEW_TITLE_KEY.value(),
                        1000, 500,
                        showButton, false);
                controller.loadStageFields(schoolPojo, ActionType.SHOW_ACTION);
            });
        });
    }

    private void initializeEditButtonAction() {
        editButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(studentsTableView, ActionType.EDIT_ACTION);
            progressFactory.createLittleProgressBar(new LoadStudentTask(selectedTableItems.get(0)), (schoolPojo) -> {
                AdminAccountsTabStudentsEditController controller = NodeUtils.openNewStageAboveWithController(
                        ResourceConst.DASHBOARD_ADMIN_ACCOUNTS_EDIT_STUDENT_VIEW_ADDRESS.value(),
                        ResourceConst.EDIT_STUDENT_VIEW_TITLE_KEY.value(),
                        1000, 500,
                        editButton, false);
                controller.loadStageFields(schoolPojo, ActionType.EDIT_ACTION);
            });
        });
    }

    private void initializeDeleteButtonAction() {
        deleteButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(studentsTableView, ActionType.DELETE_ACTION);
            if (dialogFactory.createQuestionInformationDialog(ARE_YOU_SURE_TO_PERFORM_DELETE_OPERATION_MESSAGE_KEY.value())) {
                progressFactory.createLittleProgressBar(new DeleteStudentTask(selectedTableItems), (action) -> {
                    refreshButton.fire();
                    Toast.show(ToastType.INFORMATION, SUCCESS_DIALOG_CONTENT_MESSAGE_KEY.value());
                });
            }
        });
    }

}
