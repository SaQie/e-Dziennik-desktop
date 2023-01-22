package pl.edziennik.client.controller.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import pl.edziennik.client.controller.admin.schools.AdminSchoolsTabController;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;

public class AdminController extends AbstractController {

    @FXML
    private AdminSchoolsTabController schoolsTabController;

    @FXML
    private Label timerLabel;

    @FXML
    private Button exitButton, logoutButton;

    @FXML
    private TabPane mainViewPane;

    @FXML
    private Tab schoolsTab, statisticsTab, chartsTab, accountsTab;

    @Override
    protected void createActions() {
        NodeUtils.createExitButtonAction(exitButton);
        NodeUtils.createLogoutButton(logoutButton);
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

    private void fetchTableItemsIfNeeded() {
        mainViewPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(schoolsTab)) {
                if (schoolsTabController.isTableDataEmpty()) {
                    ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new LoadSchoolsTask(), (response) -> {
                        schoolsTabController.fetchTabData(response);
                    }));
                }
            }
        });
    }


}
