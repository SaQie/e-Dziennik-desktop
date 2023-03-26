package pl.edziennik.client.controller.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import pl.edziennik.client.controller.admin.accounts.AdminAccountsTabController;
import pl.edziennik.client.controller.admin.configuration.AdminConfigurationOptionController;
import pl.edziennik.client.controller.admin.schoolclasses.AdminSchoolClassesTabController;
import pl.edziennik.client.controller.admin.schools.AdminSchoolsTabController;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.task.config.LoadConfigurationsTask;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.task.schoolclass.LoadSchoolClassesTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class AdminController extends AbstractController {

    @FXML
    private AdminSchoolsTabController schoolsTabController;

    @FXML
    private AdminAccountsTabController accountsTabController;

    @FXML
    private AdminSchoolClassesTabController schoolClassesTabController;

    @FXML
    private Label timerLabel;

    @FXML
    private Button exitButton, logoutButton, configurationButton;

    @FXML
    private TabPane mainViewPane;

    @FXML
    private Tab schoolsTab, statisticsTab, chartsTab, accountsTab, schoolClassesTab;

    @Override
    protected void createActions() {
        NodeUtils.createExitButtonAction(exitButton);
        NodeUtils.createLogoutButton(logoutButton);
        createConfigurationButtonAction();
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.createTimer(timerLabel);
        fetchTableItemsIfNeeded();
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) mainViewPane.getScene().getWindow();
    }

    private void createConfigurationButtonAction() {
        configurationButton.setOnAction(button -> {
            progressFactory.createLittleProgressBar(new LoadConfigurationsTask(), (configurationList) -> {
                AdminConfigurationOptionController controller = NodeUtils.openNewStageAboveWithController(
                        DASHBOARD_ADMIN_CONFIGURATION_VIEW_ADDRESS.value(),
                        CONFIGURATION_LIST_ADMIN_VIEW_TITLE_MESSAGE_KEY.value(),
                        450,
                        300,
                        configurationButton);
                controller.fetchData(configurationList);
            });
        });
    }

    private void fetchTableItemsIfNeeded() {
        mainViewPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(schoolsTab) && schoolsTabController.isTableDataEmpty()) {
                fetchSchoolsTabTableItems();
            }
            if (newValue.equals(accountsTab)) {
                accountsTabController.fetchSelectedTabTableItems();
            }
            if (newValue.equals(schoolClassesTab)){
                fetchSchoolClassTabTableItems();
            }
        });
    }

    private void fetchSchoolClassTabTableItems() {
        ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new LoadSchoolClassesTask(), (response) -> {
            schoolClassesTabController.fetchTabData(response);
        }));
    }

    private void fetchSchoolsTabTableItems() {
        ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new LoadSchoolsTask(), (response) -> {
            schoolsTabController.fetchTabData(response);
        }));
    }


}
