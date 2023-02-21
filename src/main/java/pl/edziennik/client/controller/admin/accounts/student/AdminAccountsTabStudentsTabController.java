package pl.edziennik.client.controller.admin.accounts.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.ActionType;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.controller.admin.schools.AdminSchoolsTabEditSchoolController;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.pojo.StudentPojo;
import pl.edziennik.client.task.school.DeleteSchoolTask;
import pl.edziennik.client.task.school.LoadSchoolTask;
import pl.edziennik.client.task.student.DeleteStudentTask;
import pl.edziennik.client.task.student.LoadStudentTask;
import pl.edziennik.client.task.student.LoadStudentsTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;

import static pl.edziennik.client.common.ResourceConst.*;

public class AdminAccountsTabStudentsTabController extends AbstractController {

    private static AdminAccountsTabStudentsTabController instance;

    @FXML
    private TableView<StudentListModel> studentsTableView;


    public AdminAccountsTabStudentsTabController() {
        instance = this;
    }

    public void fetchTabData(final List<StudentPojo> studentList) {
        List<StudentListModel> studentListModels = StudentListModel.mapPojoToModel(studentList);
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
    }


    @Override
    protected void setSceneSettings() {
        NodeUtils.setTableViewRowFactory(studentsTableView);
        NodeUtils.setColumnConfigurationShortcut(studentsTableView);
        NodeUtils.setTableViewPlaceHolder(studentsTableView);
        initializeSelectUnselectAllMenuItemAction(studentsTableView);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) studentsTableView.getScene().getWindow();
    }


    private void initializeTableColumns() {
        AdminTableViewControllerMaker.StudentTableViewBuilder builder = AdminTableViewControllerMaker.studentTableViewBuilder()
                .withSelectionColumn(true)
                .withUsernameColumn(true)
                .withAddressColumn(true)
                .withCityColumn(true)
                .withFirstNameColumn(true)
                .withLastNameColumn(true)
                .withRoleColumn(true)
                .withPeselColumn(true)
                .withParentFirstNameColumn(true)
                .withParentPhoneNumberColumn(true)
                .withParentLastNameColumn(true)
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
                    1000, 550, getActualStage());
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
                        1000, 550,
                        getActualStage());
                controller.loadStageFields(schoolPojo, ActionType.SHOW_ACTION);
            });
        });
    }

    private void initializeEditButtonAction(){
        editButton.setOnAction(button -> {
            List<Long> selectedTableItems = NodeUtils.getSelectedTableItems(studentsTableView, ActionType.EDIT_ACTION);
            progressFactory.createLittleProgressBar(new LoadStudentTask(selectedTableItems.get(0)), (schoolPojo) -> {
                AdminAccountsTabStudentsEditController controller = NodeUtils.openNewStageAboveWithController(
                        ResourceConst.DASHBOARD_ADMIN_ACCOUNTS_EDIT_STUDENT_VIEW_ADDRESS.value(),
                        ResourceConst.EDIT_STUDENT_VIEW_TITLE_KEY.value(),
                        1000, 550,
                        getActualStage());
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
                });
            }
        });
    }

}
