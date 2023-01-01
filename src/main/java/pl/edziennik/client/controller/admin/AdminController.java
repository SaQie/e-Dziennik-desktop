package pl.edziennik.client.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import pl.edziennik.client.common.ProgressFactory;
import pl.edziennik.client.task.LoadAdminDashboardView;
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
    private Label timerLabel;

    @FXML
    private Button exitButton;

    @FXML
    private Button logoutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NodeUtils.createExitButtonAction(exitButton);
        NodeUtils.createTimer(timerLabel);
        ThreadUtils.runInNewFxThread(() -> progressFactory.createLargeProgressBar(new LoadAdminDashboardView(), (response) -> {
            System.out.println("skonczylem");
        }));
        System.out.println("asd");
        NodeUtils.createLogoutButton(logoutButton);

    }



}
