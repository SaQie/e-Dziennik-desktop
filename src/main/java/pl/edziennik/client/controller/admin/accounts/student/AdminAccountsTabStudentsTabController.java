package pl.edziennik.client.controller.admin.accounts.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.controller.columns.AdminTableViewControllerMaker;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.pojo.StudentPojo;
import pl.edziennik.client.utils.NodeUtils;

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
        addButton.setOnAction(button-> {
            NodeUtils.openNewStageAbove(
                    DASHBOARD_ADMIN_ACCOUNTS_ADD_STUDENT_VIEW_ADDRESS.value(),
                    ADMIN_ACCOUNTS_ADD_STUDENT_TITLE_MESSAGE_KEY.value(),
                    1000,550, getActualStage());
        });
    }
}
