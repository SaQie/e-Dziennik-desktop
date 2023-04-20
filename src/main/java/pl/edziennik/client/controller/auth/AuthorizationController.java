package pl.edziennik.client.controller.auth;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import pl.edziennik.client.common.factory.ProgressFactory;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.core.StageManager;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;
import pl.edziennik.client.task.config.LoadConfigurationsTask;
import pl.edziennik.client.utils.AuthorizationUtils;
import pl.edziennik.client.utils.NodeUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import static pl.edziennik.client.common.constants.ResourceConst.REGISTER_VIEW_ADDRESS;
import static pl.edziennik.client.common.constants.ResourceConst.REGISTER_VIEW_TITLE;

public class AuthorizationController implements Initializable {

    @FXML
    private TabPane authorizationTabPane;

    @FXML
    private Tab registerTab;

    private ProgressFactory progressFactory;


    public void loadAuthorizationView(Stage stage) throws IOException {
        AuthorizationUtils.loadAuthorizationPage();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.progressFactory = ProgressFactory.getInstance();
        hideRegisterTabIfNeeded();
        initializeOnRegisterTabClick();
    }

    private void hideRegisterTabIfNeeded() {
        CompletableFuture<Boolean> clientAvailable = CompletableFuture.supplyAsync(() ->
                PropertiesLoader.isLoaded() && authorizationTabPane != null);

        // check application configuration allows to create student accounts independent
        clientAvailable.thenAccept((value) -> {
            progressFactory.createLittleProgressBar(new LoadConfigurationsTask(),(response) -> {
                Boolean isRegistrationEnabled = response.stream()
                        .filter(config -> config.getSettingId().equals(2L))
                        .map(ConfigurationDto::getBooleanValue)
                        .findFirst()
                        .orElse(Boolean.FALSE);
                if (!isRegistrationEnabled) {
                    authorizationTabPane.getTabs().remove(registerTab);
                }
            });
        });
    }

    private void initializeOnRegisterTabClick() {
        authorizationTabPane.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue.equals(registerTab)) {
                RegisterController registerController = NodeUtils.openNewStageAboveWithController(REGISTER_VIEW_ADDRESS.value(),
                        REGISTER_VIEW_TITLE.value(), 1000, 450,
                        getActualStage());
                registerController.setRegisterTab(registerTab);
            }
        }));
    }

    private Stage getActualStage() {
        return (Stage) authorizationTabPane.getScene().getWindow();
    }
}
