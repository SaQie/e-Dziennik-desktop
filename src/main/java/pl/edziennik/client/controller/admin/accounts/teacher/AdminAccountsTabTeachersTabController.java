package pl.edziennik.client.controller.admin.accounts.teacher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.controller.model.admin.TeacherListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.pojo.TeacherPojo;
import pl.edziennik.client.task.student.LoadStudentsTask;
import pl.edziennik.client.task.teacher.LoadTeachersTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;

import static pl.edziennik.client.common.ResourceConst.*;

public class AdminAccountsTabTeachersTabController extends AbstractController {

    private static AdminAccountsTabTeachersTabController instance;

    @FXML
    private TableView<TeacherListModel> teachersTableView;

    public AdminAccountsTabTeachersTabController() {
        instance = this;
    }

    public void fetchTabData(final List<TeacherPojo> teachersList) {
        List<TeacherListModel> teacherListModels = TeacherListModel.mapPojoToModel(teachersList);
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
                    1000, 500, getActualStage());
        });
    }
}
