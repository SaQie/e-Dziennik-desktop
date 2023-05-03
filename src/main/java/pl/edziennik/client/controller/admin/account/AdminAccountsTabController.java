package pl.edziennik.client.controller.admin.accounts;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import pl.edziennik.client.controller.admin.accounts.admin.AdminAccountsTabAdministrationTabController;
import pl.edziennik.client.controller.admin.accounts.parent.AdminAccountsTabParentsTabController;
import pl.edziennik.client.controller.admin.accounts.student.AdminAccountsTabStudentsTabController;
import pl.edziennik.client.controller.admin.accounts.teacher.AdminAccountsTabTeachersTabController;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.task.admin.LoadAdminsTask;
import pl.edziennik.client.task.parent.LoadParentsTask;
import pl.edziennik.client.task.student.LoadStudentsTask;
import pl.edziennik.client.task.teacher.LoadTeachersTask;
import pl.edziennik.client.utils.ThreadUtils;

public class AdminAccountsTabController extends AbstractController {

    @FXML
    private AdminAccountsTabTeachersTabController teachersTabController;

    @FXML
    private AdminAccountsTabStudentsTabController studentsTabController;

    @FXML
    private AdminAccountsTabParentsTabController parentsTabController;

    @FXML
    private AdminAccountsTabAdministrationTabController administrationsTabController;

    @FXML
    private TabPane accountsMainTabPane;

    @FXML
    private Tab studentsTab, teachersTab, administrationsTab, parentsTab;


    @Override
    protected void setSceneSettings() {

    }

    @Override
    protected void createActions() {

    }


    @Override
    protected Stage getActualStage() {
        return (Stage) accountsMainTabPane.getScene().getWindow();
    }


    @Override
    protected void fetchStageData() {
        accountsMainTabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(teachersTab)) {
                fetchTeachersTabTableItems();
            }
            if (newValue.equals(studentsTab)) {
                fetchStudentsTabTableItems();
            }
            if (newValue.equals(administrationsTab)) {
                fetchAdministrationTabTableItems();
            }
            if (newValue.equals(parentsTab)){
                fetchParentsTabTableItems();
            }
        });
    }

    private void fetchParentsTabTableItems() {
        if (parentsTabController.isTableDataEmpty()) {
            ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new LoadParentsTask(), (response) -> {
                parentsTabController.fetchTabData(response);
            }));
        }
    }

    public void fetchSelectedTabTableItems() {
        if (accountsMainTabPane.getSelectionModel().getSelectedItem().equals(studentsTab)) {
            fetchStudentsTabTableItems();
        }
        if (accountsMainTabPane.getSelectionModel().getSelectedItem().equals(teachersTab)) {
            fetchTeachersTabTableItems();
        }
        if (accountsMainTabPane.getSelectionModel().getSelectedItem().equals(administrationsTab)) {
            fetchAdministrationTabTableItems();
        }
    }

    private void fetchTeachersTabTableItems() {
        if (teachersTabController.isTableDataEmpty()) {
            ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new LoadTeachersTask(), (response) -> {
                teachersTabController.fetchTabData(response);
            }));
        }
    }

    private void fetchStudentsTabTableItems() {
        if (studentsTabController.isTableDataEmpty()) {
            ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new LoadStudentsTask(), (response) -> {
                studentsTabController.fetchTabData(response);
            }));
        }
    }

    private void fetchAdministrationTabTableItems() {
        if (administrationsTabController.isTableDataEmpty()) {
            ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new LoadAdminsTask(), (response) -> {
                administrationsTabController.fetchTabData(response);
            }));
        }
    }
}
