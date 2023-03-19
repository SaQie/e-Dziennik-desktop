package pl.edziennik.client.controller.auth;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;
import pl.edziennik.client.utils.AuthorizationUtils;
import pl.edziennik.client.utils.NodeUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import static pl.edziennik.client.common.ResourceConst.REGISTER_VIEW_ADDRESS;
import static pl.edziennik.client.common.ResourceConst.REGISTER_VIEW_TITLE;

public class AuthorizationController implements Initializable {

    @FXML
    private TabPane authorizationTabPane;

    @FXML
    private Tab registerTab;


    public void loadAuthorizationView(Stage stage) throws IOException {
        AuthorizationUtils.loadAuthorizationPage();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hideRegisterTabIfNeeded();
        initializeOnRegisterTabClick();
    }

    private void hideRegisterTabIfNeeded() {
        CompletableFuture<Boolean> clientAvailable = CompletableFuture.supplyAsync(() ->
                PropertiesLoader.isLoaded() && authorizationTabPane != null);

        // check application configuration allows to create student accounts independent
        clientAvailable.thenAccept((value) -> {
            AdminRestClient adminRestClient = new AdminRestClient();
            Boolean isRegistrationEnabled = adminRestClient.getConfigurationList()
                    .stream()
                    .filter(config -> config.getId().equals(2L))
                    .map(ConfigurationDto::isBooleanValue)
                    .findFirst()
                    .orElse(Boolean.FALSE);
            if (!isRegistrationEnabled) {
                authorizationTabPane.getTabs().remove(registerTab);
            }
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
