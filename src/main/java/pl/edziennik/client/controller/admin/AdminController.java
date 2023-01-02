package pl.edziennik.client.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import pl.edziennik.client.common.ProgressFactory;
import pl.edziennik.client.task.LoadSchoolsTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private ProgressFactory progressFactory;

    public AdminController() {
        this.progressFactory = ProgressFactory.getInstance();
    }

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NodeUtils.createExitButtonAction(exitButton);
        NodeUtils.createTimer(timerLabel);
        // chhyba powinienem dac tutaj ladnowanie wszystkiego z bomby
        // dopiero "refresh" bedzie ladowalo to odpowiednio
        // bo tak to co klikniecie bedzie request- a to bez sensu

        // Drugi pomysl
        // przed ladowaniem sprawdzac czy tabela byla juz zaladowana (if table.isEmpty) to wtedy laduj dane(request)
        // jesli juz ma jakies dane to zostawiamy w spokoju (nie robimy nic)
        NodeUtils.createLogoutButton(logoutButton);
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
